package ru.alfabank.homework12;

public class FlightNotFoundException extends AirportServiceException {
    public FlightNotFoundException(String message) {
        super(message);
    }
}
