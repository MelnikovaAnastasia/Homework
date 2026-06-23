package ru.alfabank.homework10;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        TaskTracker tracker = new TaskTracker();

        System.out.println("*попробуем вывести пустой список*");
        System.out.println();

        tracker.printAllTasks();
        System.out.println();

        System.out.println("*добавим задачи в список задач*");
        System.out.println();

        tracker.addTask("");
        System.out.println();
        tracker.addTask("Проверить авторизацию");
        tracker.addTask("Проверить регистрацию");
        tracker.addTask("Написать тест");
        tracker.addTask("Написать письмо вредным коллегам");
        System.out.println();
        tracker.printAllTasks();
        System.out.println();

        System.out.println("*отметим задачу как выполненную*");
        System.out.println();

        tracker.markTask("Проверить регистрацию");
        tracker.printAllTasks();
        System.out.println();

        System.out.println("*упс, не ту задачу отметили, поменяем*");
        System.out.println();

        tracker.markTask("Проверить регистрацию");
        tracker.markTask("Написать тест");

        tracker.printAllTasks();
        System.out.println();

        System.out.println("*найдём задачи по названию: первая есть, а вторая и третья отсутствуют*");
        System.out.println();

        tracker.findTaskByName("Написать письмо вредным коллегам");
        tracker.findTaskByName("Проверить холодильник");
        tracker.findTaskByName("");
        System.out.println();

        System.out.println("*изменим название задачи*");
        System.out.println();

        tracker.renameTask("Написать письмо вредным коллегам", "Погладить кота");
        System.out.println();

        System.out.println("*выведем статистику по задачам*");
        System.out.println();

        tracker.printStatistics();
    }
}