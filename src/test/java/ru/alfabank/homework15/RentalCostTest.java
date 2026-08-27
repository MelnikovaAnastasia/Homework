package ru.alfabank.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование расчёта стоимости аренды")
public class RentalCostTest {

    private GameRental gameRental;

    @BeforeEach
    public void setUp() {
        gameRental = new GameRental();
        gameRental.addGame(new BoardGame("Шахматы", 6, 500));
        gameRental.addGame(new BoardGame("Шашки", 6, 400));
    }

    @ParameterizedTest
    @MethodSource("calculateCostDataSource")
    @DisplayName("Успешный расчёт стоимости аренды")
    void testCalculateCost(String name, int days, int expectedResult) {
        int actualResult = gameRental.calculateCost(name, days);

        assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("Вызов исключения при расчёте стоимости, если указана несуществующая игра")
    void testCalculateCostWhenNonExistentGame() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.calculateCost("Несуществующая игра", 3));
    }

    @Test
    @DisplayName("Вызов исключения при расчёте стоимости, если указано 0 дней")
    void testCalculateCostWhenDaysEqualsZero() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.calculateCost("Chess", 0));
    }

    @Test
    @DisplayName("Вызов исключения при расчёте стоимости, если указано дней меньше нуля")
    void testCalculateCostWhenDaysLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.calculateCost("Chess", -1));
    }

    static Stream<Object[]> calculateCostDataSource() {
        return Stream.of(
                new Object[]{"Шахматы", 1, 500},
                new Object[]{"Шахматы", 5, 2500},
                new Object[]{"Шашки", 2, 800},
                new Object[]{"Шашки", 4, 1600}
        );
    }
}
