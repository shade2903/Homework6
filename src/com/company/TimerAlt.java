package com.company;

import java.util.ArrayList;

public class TimerAlt {
    // в этой коллекции будут храниться задачи, добавленные через метод put.
    private ArrayList<Task> tasks;

    // этот класс будет служить исключительно как обертка
    // для сохранения объекта типа Runnable и времени, через которое
    // он должен запуститься.
    private class Task {
        private Runnable r;
        private long time;
        // добавляем объект потока, в котором в последующем будет запускаться код
        // объекта Runnable r.
        private Thread taskThread;

        public Task(Runnable r, long time) {
            this.r = r;
            this.time = time;
        }

        // (необязательно) введем дополнительный метод, который будет запускать выполнение
        // объекта Runnable в отдельном потоке, чтобы упростить код метода start.
        public void execute() throws InterruptedException {
            this.taskThread = new Thread(() -> {
                try {
                    Thread.sleep(this.time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.r.run();
            });

            this.taskThread.start();
        }

        // вызов этого метода ждет выполнения потока.
        public void waitExecution() throws InterruptedException {
            this.taskThread.join();
        }
    }

    public TimerAlt() {
        this.tasks = new ArrayList<>();
    }

    public void put(Runnable r, long time) {
        // просто добавляем объект Task в коллекцию задач,
        // и нечего не запускаем! Запуск должен происходить только
        // при вызове метода start.
        this.tasks.add(new Task(r, time));
    }

    public void start() throws InterruptedException {
        // сперва просто запускаем все потоки (таски), не дожидаясь их выполнения.
        for (Task task : this.tasks) {
            task.execute();
            // если вызывать task.waitExecution(), который выполняет метод join
            // у потока, то тогда все таски будут выполняться последовательно,
            // а не параллельно, что нарушит логику работы таймера.
            // поэтому мы должны запустить еще один цикл, в котором будем ожидать
            // завершения (см. ниже).
        }

        // затем ожидаем завершения всех тасок (потоков).
        for (Task task : this.tasks) {
            task.waitExecution();
        }
    }
}