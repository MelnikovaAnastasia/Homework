package ru.alfabank.homework15;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование класса настольных игр")
public class BoardGameTest {

    @Test
    @DisplayName("Создание настольной игры с валидными параметрами")
    public void testCreateValidBoardGame() {
        BoardGame boardGame = new BoardGame ("Дженга", 3, 200);

        assertEquals("Дженга", boardGame.getName());
        assertEquals(3,boardGame.getMinimumAge());
        assertEquals(200, boardGame.getRentalPrice());
        assertFalse(boardGame.isRented());
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с null в качестве имени")
    public void testNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame(null, 6, 400));
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с пустотой в качестве имени")
    public void testEmptyNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame("", 12, 800));
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с пробелом в качестве имени")
    public void testNameWithWhitespace() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame(" ", 24, 1600));
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с возрастом меньше нуля")
    public void testAgeLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame("Шахматы", -1, 3200));
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с ценой аренды равной нулю")
    public void testRentalPriceEqualToZero() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame("Монополия", 8, 0));
    }

    @Test
    @DisplayName("Вызов исключения при создании игры с ценой аренды меньше нуля")
    public void testRentalPriceLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> new BoardGame("Вдрабадан", 18, -1));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, true",
            "0, 1, true",
            "1, 0, false"
    })
    @DisplayName("Проверка возможности аренды исходя из возраста арендатора")
    public void testCanBeRentedBy(int minimumAge, int customerAge, boolean expectedResult) {
        BoardGame boardGame = new BoardGame("Игра", minimumAge, 100);

        assertEquals(expectedResult, boardGame.canBeRentedBy(customerAge));
    }
}
