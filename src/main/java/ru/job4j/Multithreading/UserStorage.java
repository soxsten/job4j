package ru.job4j.Multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashSet;
import java.util.Set;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Set<User> users = new HashSet<>();

    synchronized boolean add(User user) {
        return users.add(user);
    }

    synchronized boolean update(User user) {
        return users.add(user);
    }

    synchronized boolean delete(User user) {
        return users.remove(user);
    }

    synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = null;
        User to = null;
        for (User user : users) {
            if (user.id == fromId) {
                from = user;
            }
            if (user.id == toId) {
                to = user;
            }
        }
        if (from != null && to != null) {
            if (from.amount - amount >= 0) {
                from.amount -= amount;
                to.amount += amount;
                return true;
            }
        }
        return false;
    }

    User getUserBy(int id) {
        for (User user : users) {
            if (user.id == id) {
                return user;
            }
        }
        return null;
    }

    static class User {
        private int id;
        private int amount;

        User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public int getAmount() {
            return amount;
        }
    }
}
