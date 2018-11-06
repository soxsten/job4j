package ru.job4j.Multithreading;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void name() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        UserThread consumer = new UserThread(queue) {
            @Override
            public void run() {
                queue.poll();
            }
        };
        UserThread producer = new UserThread(queue) {
            @Override
            public void run() {
                queue.offer(1);
            }
        };
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }

    private class UserThread extends Thread {
        private SimpleBlockingQueue queue;

        UserThread(SimpleBlockingQueue queue) {
            this.queue = queue;
        }
    }
}
