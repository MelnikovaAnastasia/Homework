package ru.alfabank.homework10;

import java.util.ArrayList;

public class TaskTracker {

    public ArrayList<Task> tasks = new ArrayList<>();


    public void addTask(String taskName) {
        if (taskName != null && !taskName.trim().isEmpty()) {
            Task task = new Task(taskName);
            tasks.add(task);
            System.out.println("Добавлена задача: ");
            task.taskInfo();
        } else {
            System.out.println("Задача без названия?Ну же, добавьте хотя бы один символ!");
        }
    }

    public void printAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список задач пуст, кажется, пора создать задачу!");
        } else {
            System.out.println("Список задач:");
            for (Task task : tasks) task.taskInfo();
        }
    }

    public void findTaskByName(String taskName) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                System.out.println("Найдена задача:");
                task.taskInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Не найдена задача, попробуйте ещё раз или измените запрос.");
        }
    }

    public void renameTask(String taskName, String newTaskName) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                System.out.println("Задача:");
                task.taskInfo();
                found = true;
                System.out.println("Изменено:");
                task.setTaskName(newTaskName);
                task.taskInfo();
            }
        }
        if (!found) {
            System.out.println("Не найдена задача, попробуйте ещё раз или измените запрос.");
        }
    }

    public void markTask(String taskName) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(taskName)) {
                task.setTaskStatus();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Не найдена задача, попробуйте ещё раз или измените запрос.");
        }
    }

    public void printStatistics() {
        int done = 0;
        int open = 0;
        for (Task task : tasks) {
            if (task.getTaskStatus()) {
                done++;
            } else {
                open++;
            }
        }
        System.out.println("Всего задач: " + tasks.size());
        System.out.println("Выполнено: " + done);
        System.out.println("Открыто: " + open);
    }
}
