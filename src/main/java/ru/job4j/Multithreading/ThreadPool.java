package ru.job4j.Multithreading;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);
    private volatile boolean isRunning = true;

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            TaskWorker taskWorker = new TaskWorker();
            taskWorker.start();
            threads.add(taskWorker);
        }
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        isRunning = false;
    }

    private final class TaskWorker extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                Runnable nextTask = null;
                try {
                    nextTask = tasks.poll();
                    if (nextTask != null) {
                        nextTask.run();
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
