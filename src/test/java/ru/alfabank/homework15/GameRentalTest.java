package ru.alfabank.homework15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Тестирование аренды")
public class GameRentalTest {

    private GameRental gameRental;

    @BeforeEach
    public void setUp() {
        gameRental = new GameRental();
        gameRental.addGame(new BoardGame ("Вдрабадан", 18, 600));
        gameRental.addGame(new BoardGame("Шахматы", 6, 500));
        gameRental.addGame(new BoardGame("Монополия", 8, 900));
    }

    @Test
    @DisplayName("Успешная аренда игры и смена её статуса аренды")
    public void testSuccessfulGameRental() {
        boolean isRentedGame = gameRental.rentGame("Вдрабадан", 18);
        boolean isPossibleToRentGameNow = gameRental.rentGame("Вдрабадан", 18);

        assertTrue(isRentedGame);
        assertFalse(isPossibleToRentGameNow);
    }

    @Test
    @DisplayName("Вызов исключения, если названия игры не существует")
    public void testRentNullGame() {
        assertThrows(IllegalArgumentException.class, () -> gameRental.rentGame("Несуществующая игра", 18));
    }

    @Test
    @DisplayName("Невозможность аренды игры, если возраст арендатора не подходит")
    public void testWhenAgeLessThanNecessary() {
        boolean result = gameRental.rentGame("Вдрабадан", 17);

        assertFalse(result);
    }

    @Test
    @DisplayName("Невозможность аренды игры, если она уже арендована")
    public void testInabilityToRentRentedGame() {
        gameRental.rentGame("Вдрабадан", 19);
        boolean result = gameRental.rentGame("Вдрабадан", 20);

        assertFalse(result);
    }

    @Test
    @DisplayName("Успешная сдача игры обратно и смена её статуса аренды")
    public void testSuccessfulGameReturn() {
        gameRental.rentGame("Вдрабадан", 18);
        boolean isReturnedGame = gameRental.returnGame("Вдрабадан");
        boolean isPossibleToReturnGameNow = gameRental.returnGame("Вдрабадан");

        assertTrue(isReturnedGame);
        assertFalse(isPossibleToReturnGameNow);
    }


    @Test
    @DisplayName("Невозможность сдать несуществующую в каталоге игру обратно")
    public void testInabilityToReturnNonExistentGame() {
        boolean isPossibleToReturnGame = gameRental.returnGame("Несуществующая игра");

        assertFalse(isPossibleToReturnGame);
    }

    @Test
    @DisplayName("Невозможность сдать неарендованную игру обратно")
    public void testInabilityToReturnNotRentedGame() {
        boolean isPossibleToReturnGame = gameRental.returnGame("Вдрабадан");

        assertFalse(isPossibleToReturnGame);
    }

    @Test
    @DisplayName("Успешный сброс состояния аренды для всех игр")
    public void testSuccessfulResetRentalStatus() {
        gameRental.rentGame("Вдрабадан", 21);
        gameRental.rentGame("Монополия", 12);

        gameRental.reset();

        boolean isPossibleToRentGameNowOne = gameRental.rentGame("Вдрабадан", 21);
        boolean isPossibleToRentGameNowTwo = gameRental.rentGame("Монополия", 12);

        assertTrue(isPossibleToRentGameNowOne);
        assertTrue(isPossibleToRentGameNowTwo);
    }
}
