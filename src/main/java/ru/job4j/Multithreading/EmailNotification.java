package ru.job4j.Multithreading;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService pool;

    public void emailTo(User user) {
        String subject = "Notification " + user.getUsername() + "to email " + user.getEmail();
        String body = "Add a new event to " + user.getUsername();
        send(subject, body, user.getEmail());
    }

    public void send(String suject, String body, String email) {

    }

    public void close() {
        pool.shutdown();
    }

    public void start() {
        int processors = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(processors);
        for (int i = 0; i < processors; i++) {
            Task task = new Task();
            pool.submit(task);
        }
    }

    class User {
        private String username;
        private String email;

        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }

    private class Task implements Runnable {

        private String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        private String username;
        private String email;
        private Random random;

        @Override
        public void run() {
            random = new Random();
            StringBuilder username = new StringBuilder();
            int lengthForName = getRandomLength(5);
            for (int i = 0; i < lengthForName; i++) {
                username.append(getLetter(lengthForName));
            }
            StringBuilder email = new StringBuilder();
            int lengthForEmail = getRandomLength(10);
            for (int i = 0; i < lengthForEmail; i++) {
                email.append(getLetter(lengthForEmail));
            }
            emailTo(new User(username.toString(), email.toString()));
        }

        private String getLetter(int length) {
            return String.valueOf(characters.charAt(random.nextInt(length)));
        }

        private int getRandomLength(int limit) {
            return random.nextInt(limit);
        }
    }
}
