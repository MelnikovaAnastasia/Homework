package ru.alfabank.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование игрового каталога")
public class GameCatalogTest {

    private GameRental gameRental;

    @BeforeEach
    public void setUp() {
        gameRental = new GameRental();
    }

    @Test
    @DisplayName("Добавление валидный игры в каталог и проверка её существования после добавления")
    public void testAddValidGameToCatalog() {
        BoardGame addedGame = new BoardGame("Шашки", 3, 300);
        gameRental.addGame(addedGame);
        BoardGame foundGame = gameRental.findGame("Шашки");

        assertNotNull(foundGame);
        assertEquals(addedGame, foundGame);
    }

    @Test
    @DisplayName("Вызов исключения при добавлении null в каталог")
    public void testAddNullGameToCatalog() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.addGame(null));
    }

    @Test
    @DisplayName("Вызов исключения при добавление дубля игры в каталог")
    public void testAddDuplicateOfGameToCatalog() {
        BoardGame originalGame = new BoardGame("Маджонг", 6, 500);
        BoardGame duplicateGame = new BoardGame("Маджонг", 7, 700);
        gameRental.addGame(originalGame);

        assertThrows(IllegalArgumentException.class, () -> gameRental.addGame(duplicateGame));
    }

    @Test
    @DisplayName("Возвращение null при поиске несуществующей в каталоге игры")
    public void testFindNonExistentGame() {
        assertNull(gameRental.findGame("Несуществующая игра"));
    }
}
