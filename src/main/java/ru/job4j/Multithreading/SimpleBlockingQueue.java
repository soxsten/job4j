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
        while (count > 10) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        this.notify();
        count++;
        queue.add(value);
    }

    public synchronized T poll() {
        while (queue.peek() == null) {
            try {
                this.wait();
            } catch (InterruptedException ignored) {
            }
        }
        this.notify();
        count--;
        return queue.poll();
    }

    public boolean isEmpty() {
        return count <= 0;
    }

    private boolean isFull() {
        return count > 10;
    }
}
