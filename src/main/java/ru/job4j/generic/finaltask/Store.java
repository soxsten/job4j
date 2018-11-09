package ru.job4j.generic.finaltask;

import java.util.ArrayList;
import java.util.List;

public final class Store {
    private List<User> users;
    private Info info;

    public Store(List<User> users) {
        this.users = users;
        this.info = new Info(users);
    }

    public User getUserBy(int id) {
        if (validationFor(id)) {
            User user = users.get(id);
            return user == null ? null : user.getCopy();
        }
        return null;
    }

    public boolean updateUserById(User updated) {
        if (validationFor(updated) && validationFor(updated.getId())) {
            User user = users.get(updated.getId());
            if (user != null) {
                if (user.getId() == updated.getId()) {
                    if (!user.getName().equals(updated.getName())) {
                        user = updated.getCopy();
                        info.updateInfo(updated);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean addUser(User user) {
        if (validationFor(user)) {
            users.add(user.getCopy());
            info.addUser(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(User user) {
        if (validationFor(user) && validationFor(user.getId())) {
            users.set(user.getId(), null);
            return true;
        }
        return false;
    }

    private boolean validationFor(User user) {
        return user != null;
    }

    private boolean validationFor(int id) {
        return id >= 0 && id < this.users.size();
    }

    public Info getInfo() {
        return new Info(info);
    }
}

final class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User getCopy() {
        return new User(this.id, this.name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class Info {
    private List<User> previous;
    private List<User> current;
    private int newUsers = 0;
    private int deletedUsers = 0;
    private int changedUsers = 0;

    public Info(List<User> previous) {
        this.previous = getCopyOf(previous);
        this.current = getCopyOf(previous);
    }

    public Info(Info that) {
        this.previous = that.getCopyOf(that.previous);
        this.current = that.getCopyOf(that.current);
        this.newUsers = that.newUsers;
        this.deletedUsers = that.deletedUsers;
        this.changedUsers = that.changedUsers;
    }

    public void updateInfo(User updated) {
        User user = current.get(updated.getId());
        user = updated.getCopy();
        changedUsers++;
    }

    public void deleteUser(User user) {
        User dataUser = current.get(user.getId());
        dataUser = null;
        deletedUsers++;
    }

    public void addUser(User user) {
        current.add(user.getCopy());
        newUsers++;
    }

    private List<User> getCopyOf(List<User> previous) {
        List<User> users = new ArrayList<>();
        for (User user : previous) {
            users.add(user.getCopy());
        }
        return users;
    }
}

/*
Сколько добавлено новых пользователей.
Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя.
а id осталось прежним.
Сколько удалено пользователей.
*/