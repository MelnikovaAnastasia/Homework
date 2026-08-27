package ru.alfabank.homework15;

public class BoardGame {

    private final String name;
    private final int minimumAge;
    private final int rentalPrice;
    private boolean isRented;

    public BoardGame(String name, int minimumAge, int rentalPrice) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название игры не может быть пустым!");
        }
        if (minimumAge < 0 ) {
            throw new IllegalArgumentException("Возраст не может быть меньше нуля!");
        }
        if (rentalPrice <= 0) {
            throw new IllegalArgumentException("Цена аренды не может быть меньше или равна нулю!");
        }
        this.name = name;
        this.minimumAge = minimumAge;
        this.rentalPrice = rentalPrice;
        this.isRented = false;
    }

    public String getName() {
        return name;
    }

    public int getMinimumAge() {
        return minimumAge;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    };

    public boolean canBeRentedBy(int age) {
        return age >= minimumAge;
    }
}
