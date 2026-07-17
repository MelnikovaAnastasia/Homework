package ru.alfabank.homework12;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    static void main() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        //Создадим массив доступных рейсов
        String[] flights = {"SU-123", "TK-777", "KC-909", "AE-404"};

        //Создадим стойку приёма багажа
        BaggageDropDesk baggageDropDesk = new BaggageDropDesk(flights);

        System.out.println("Протестируем сценарии:");
        System.out.println("--Успешно--");
        test(baggageDropDesk,"Ivan Petrov", "SU-123", 18);
        System.out.println();

        System.out.println("--Рейс не найден--");
        test(baggageDropDesk,"Ivan Petrov", "BL-505", 18);
        System.out.println();

        System.out.println("--Перевес багажа--");
        test(baggageDropDesk,"Ivan Petrov", "SU-123", 24);
        System.out.println();

        System.out.println("--Ошибка печати--");
        test(baggageDropDesk,"Ivan Petrov", "AE-404", 18);
        System.out.println();

        System.out.println("--Имя пассажира пустое--");
        test(baggageDropDesk,null, "SU-123", 18);
        System.out.println();

        System.out.println("--Вес багажа отрицательный--");
        test(baggageDropDesk,"Ivan Petrov", "SU-123", -1);
    }

    //Создадим вспомогательный метод, чтобы сократить код
    public static void test(BaggageDropDesk baggageDropDesk, String passengerName, String flightNumber, int baggageWeight) {
        try {
            BaggageTicket baggageTicket = baggageDropDesk.baggageCheckIn(passengerName, flightNumber, baggageWeight);
            System.out.println("Готова багажная бирка: " + baggageTicket.toString());
        } catch (AirportServiceException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
