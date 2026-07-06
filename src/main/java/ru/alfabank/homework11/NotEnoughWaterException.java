package ru.alfabank.homework11;

public class NotEnoughWaterException extends RuntimeException {
    public NotEnoughWaterException(String message) {
        super(message);
    }
}