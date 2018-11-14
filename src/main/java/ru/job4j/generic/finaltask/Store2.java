package ru.job4j.generic.finaltask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store2 {
    Info diff(List<User> previous, List<User> current) {
        return new Info(previous, current).compare();
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public class Info {
        private Map<Integer, User> previous;
        private Map<Integer, User> current;
        private int newUsers = 0;
        private int changedUsers = 0;
        private int deletedUsers = 0;

        Info(List<User> previous, List<User> current) {
            this.previous = fill(previous);
            this.current = fill(current);
        }

        private Map<Integer, User> fill(List<User> list) {
            Map<Integer, User> map = new HashMap<>();
            for (User user : list) {
                map.put(user.id, user);
            }
            return map;
        }

        private Info compare() {
            for (User user : previous.values()) {
                User dataUser = current.get(user.id);
                if (dataUser == null) {
                    deletedUsers++;
                } else {
                    if (!isNameEquals(user, dataUser)) {
                        changedUsers++;
                    }
                }
            }
            for (User user : current.values()) {
                User prevUser = previous.get(user.id);
                if (prevUser == null) {
                    newUsers++;
                }
            }
            return this;
        }

        private boolean isNameEquals(User user, User dataUser) {
            return user.name.equals(dataUser.name);
        }

        public int getNewUsers() {
            return newUsers;
        }

        public int getChangedUsers() {
            return changedUsers;
        }

        public int getDeletedUsers() {
            return deletedUsers;
        }
    }
}
