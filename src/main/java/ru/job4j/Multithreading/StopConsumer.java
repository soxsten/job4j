package ru.job4j.Multithreading;

public class StopConsumer {

    public static void main(String[] args) throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(10);
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        sleepFor(500);
                    }
                }
        );
        producer.start();
        producer.join();
        final Thread consumer = new Thread(
                () -> {
                    while (Thread.currentThread().isInterrupted()) {
                        try {
                            System.out.println(queue.poll());
                        } catch (InterruptedException ignored) {
                        }
                        sleepFor(500);
                    }
                }
        );
        consumer.start();
        consumer.interrupt();
    }

    private static void sleepFor(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
        }
    }
}
