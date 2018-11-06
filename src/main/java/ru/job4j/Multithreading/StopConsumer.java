package ru.job4j.Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

public class StopConsumer {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>();
        AtomicInteger offerTimes = new AtomicInteger(0);
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        offerTimes.getAndIncrement();
                        sleepFor(500);
                    }
                }
        );
        AtomicInteger pollTimes = new AtomicInteger(0);
        final Thread consumer = new Thread(
                () -> {
                    while (true) {
                        if (producer.getState().equals(Thread.State.TERMINATED)) {
                            if (pollTimes.get() == offerTimes.get()) {
                                break;
                            }
                        }
                        System.out.println(queue.poll());
                        pollTimes.getAndIncrement();
                        sleepFor(1000);
                    }
                }
        );
        producer.start();
        consumer.start();
    }

    private static void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }
}
