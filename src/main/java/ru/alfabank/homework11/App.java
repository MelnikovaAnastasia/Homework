package ru.alfabank.homework11;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    static void main() {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        CoffeeMachine coffeeMachine = new CoffeeMachine();

        Scanner scanner = new Scanner(System.in);

        //Проверка на InputMismatchException
        System.out.println("Введите количество воды в мл:");
        try {
            int waterInput = scanner.nextInt();
            coffeeMachine.makeCoffee(waterInput);
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: Введите число!");
        }

        System.out.println();

        //Проверка на NotEnoughWaterException
        try {
            coffeeMachine.makeCoffee(100);
        } catch (NotEnoughWaterException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Проверка кофемашины завершена!");
        }

        System.out.println();

        //Проверка на ArithmeticException
        try {
            coffeeMachine.calculateCups(1000,0);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Размер чашки не может быть 0!");
        }

        System.out.println();

        //Проверка на NullPointerException
        String nullCoffeeName = null;
        try {
            coffeeMachine.printCoffeeName(nullCoffeeName);
        } catch (NullPointerException e) {
            System.out.println("Ошибка: Отсутствует название кофе!");
        }
    }
}
