package ru.alfabank.homework12;

import java.util.Arrays;

public class BaggageDropDesk {

    private final String[] availableFlights;

    public BaggageDropDesk(String[] flights) {
        this.availableFlights = Arrays.copyOf(flights, flights.length);
    }

    public BaggageTicket baggageCheckIn(String passengerName, String flightNumber, int baggageWeight)
            throws FlightNotFoundException, OverweightBaggageException, BaggageTagPrintException {
        if (passengerName == null || passengerName.isEmpty()) {
            throw new InvalidPassengerNameException("Ошибка: Имя пассажира не может быть пустым!");
        }
        if (baggageWeight < 0) {
            throw new InvalidBaggageWeightException("Ошибка: Вес багажа не может быть меньше нуля!");
        }
        boolean isFlightAvailable = false;
        for (String flight : availableFlights) {
            if (flight.equals(flightNumber)) {
                isFlightAvailable = true;
                break;
            }
        }
        if (!isFlightAvailable) {
            throw new FlightNotFoundException("Ошибка: Указанный рейс не найден в списке доступных рейсов!");
        }
        if (baggageWeight > 23) {
            throw new OverweightBaggageException("Внимание: Вес багажа превышает 23 кг, необходимо внести доплату за перевес!"
            );
        }
        if ("AE-404".equals(flightNumber)) {
            throw new BaggageTagPrintException("Ошибка: Не удалось распечатать багажную бирку!Пожалуйста, проверьте исправность принтера!");
        }
        System.out.println("Внимание: Необходимо внести доплату за слишком правильные введённые данные!");
        return new BaggageTicket(passengerName, flightNumber, baggageWeight);
    }
}
