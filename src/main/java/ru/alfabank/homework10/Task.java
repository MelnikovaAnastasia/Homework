package ru.alfabank.homework10;

public class Task {

    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean getTaskStatus() {
        return isDone;
    }

    public void setTaskStatus() {
        isDone = !isDone;
    }

    public void taskInfo() {
        String mark = isDone ? "[x] " : "[ ] ";
        System.out.println(mark + taskName);
    }
}