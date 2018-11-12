package ru.job4j.Multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;


@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int count = 0;

    public synchronized void offer(T value) {
        while (queue.size() > 2) {
            System.out.println("producer wait");
            try {
                wait();
            } catch (InterruptedException ignored) {
            }
        }
        notify();
        count++;
        System.out.println("add value " + value);
        queue.add(value);
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.peek() == null) {
            System.out.println("consumer wait");
            wait();
        }
        notify();
        count--;
        T poll = queue.poll();
        System.out.println("poll value " + poll);
        return poll;
    }

    public boolean isEmpty() {
        return count <= 0;
    }

    private boolean isFull() {
        return count > 10;
    }
}
