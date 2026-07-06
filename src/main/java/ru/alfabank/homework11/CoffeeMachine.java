package ru.alfabank.homework11;

public class CoffeeMachine {

    public void makeCoffee(int millilitersOfWater) {
        if (millilitersOfWater <= 200) {
            throw new NotEnoughWaterException(
                    "Ошибка: Для приготовления кофе необходимо больше 200 мл воды!" +
                            "Сейчас " + millilitersOfWater + " мл воды."
            );
        }
        System.out.println("Кофе готов!");
    }

    public int calculateCups(int millilitersOfWater, int millilitersOfCup) {
        int cupsOfCoffee = millilitersOfWater / millilitersOfCup;
        System.out.println("Чашек кофе получится: " + cupsOfCoffee);
        return cupsOfCoffee;
    }

    public void printCoffeeName(String coffeeName) {
        System.out.println(coffeeName.toUpperCase());
    }
}