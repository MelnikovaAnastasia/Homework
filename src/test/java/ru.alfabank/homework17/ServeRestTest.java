package ru.alfabank.homework17;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import ru.alfabank.models.Produto;
import ru.alfabank.models.Usuario;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServeRestTest {

    private static String userId;
    private static String userEmail;
    private static String token;

    @BeforeAll
    static void setUp() {
        baseURI = "https://serverest.dev";
        enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @Order(1)
    @DisplayName("Кто здесь уже покупал?")
    public void shouldGetAllUsers() {
        given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("quantidade", greaterThan(0))
                .body("usuarios", not(emptyArray()));
    }

    @Test
    @Order(2)
    @DisplayName("Досье на клиента")
    public void shouldFindUserByEmail() {
        String email = given()
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .jsonPath()
                .getString("usuarios[0].email");

        given()
                .queryParam("email", email)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("quantidade", equalTo(1))
                .body("usuarios[0].email", equalTo(email));
    }

    @Test
    @Order(3)
    @DisplayName("Открываем новый аккаунт")
    public void shouldCreateNewUser() {
        long timestamp = System.currentTimeMillis();
        userEmail = String.format("spy_%d@qa.com", timestamp);
        String jsonBody = String.format("""
                {
                  "nome": "Тайный Покупатель",
                  "email": "%s",
                  "password": "secret123",
                  "administrador": "true"
                }
                """, userEmail);

        userId = given()
                .contentType(ContentType.JSON)
                .body(jsonBody)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .path("_id");
    }

    @Test
    @Order(4)
    @DisplayName("★ Пополняем витрину - создание товара через DTO")
    public void shouldCreateProductFromDTO() {
        String loginBody = String.format("""
                {
                  "email": "%s",
                  "password": "secret123"
                }
                """, userEmail);

        String productToken = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .extract()
                .path("authorization");

        Produto produto = new Produto(
                "Tablet Galactico",
                799,
                "Tablet com tela de 10 polegadas",
                15);

        String productId = given()
                .header("Authorization", productToken)
                .contentType(ContentType.JSON)
                .body(produto)
                .when()
                .post("/produtos")
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .path("_id");

        given()
                .header("Authorization", productToken)
                .pathParam("id", productId)
                .when()
                .delete("/produtos/{id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }

    @Test
    @Order(5)
    @DisplayName("Смена данных клиента")
    public void shouldUpdateUser() {
        String updateBody = String.format("""
                {
                  "nome": "Обновлённый Покупатель",
                  "email": "%s",
                  "password": "secret123",
                  "administrador": "false"
                }
                """, userEmail);

        given()
                .contentType(ContentType.JSON)
                .pathParam("id", userId)
                .body(updateBody)
                .when()
                .put("/usuarios/{id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    @Order(6)
    @DisplayName("Ключ от служебного входа - авторизация")
    public void shouldLogin() {
        String loginBody = String.format("""
                {
                  "email": "%s",
                  "password": "secret123"
                }
                """, userEmail);

        token = given()
                .contentType(ContentType.JSON)
                .body(loginBody)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .body("message", equalTo("Login realizado com sucesso"))
                .body("authorization", notNullValue())
                .extract()
                .path("authorization");
    }

    @Test
    @Order(7)
    @DisplayName("Ключ от служебного входа - удаление пользователя")
    public void shouldDeleteUser() {
        given()
                .header("Authorization", token)
                .pathParam("id", userId)
                .when()
                .delete("/usuarios/{id}")
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));

        given()
                .when()
                .pathParam("id", userId)
                .get("/usuarios/{id}")
                .then()
                .statusCode(400)
                .body("message", equalTo("Usuário não encontrado"));
    }

    @Test
    @Order(8)
    @DisplayName("Каталог товаров")
    public void shouldGetAllProducts() {
        given()
                .when()
                .get("/produtos")
                .then()
                .statusCode(200)
                .body("quantidade", greaterThan(0))
                .body("produtos.preco", everyItem(greaterThan(0)))
                .body("produtos.nome", everyItem(not(emptyString())))
                .body("produtos.nome", hasItem("Logitech MX Vertical"));
    }

    @Test
    @Order(9)
    @DisplayName("★ Создание пользователя через DTO (сериализация)")
    public void shouldCreateUserFromDTO() {
        Usuario usuario = new Usuario(
                "Тайный Покупатель",
                "spy_" + System.currentTimeMillis() + "@qa.com",
                "secret123",
                "true");

        given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue())
                .extract()
                .path("_id");
    }
}

