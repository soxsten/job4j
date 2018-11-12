package ru.job4j.generic.finaltask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public boolean updateUserBy(User updated) {
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
            info.deleteUser(user);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

class Info {
    private List<User> previous;
    private List<User> current;
    private int newUsers = 0;
    private int deletedUsers = 0;
    private int changedUsers = 0;

    Info(List<User> previous) {
        this.previous = getCopyOf(previous);
        this.current = getCopyOf(previous);
    }

    Info(Info that) {
        this.previous = that.getCopyOf(that.previous);
        this.current = that.getCopyOf(that.current);
        this.newUsers = that.newUsers;
        this.deletedUsers = that.deletedUsers;
        this.changedUsers = that.changedUsers;
    }

    void updateInfo(User updated) {
        current.set(updated.getId(), updated);
        changedUsers++;
    }

    void deleteUser(User user) {
        current.set(user.getId(), null);
        deletedUsers++;
    }

    void addUser(User user) {
        current.add(user.getCopy());
        newUsers++;
    }

    public List<User> getPrevious() {
        return getCopyOf(previous);
    }

    public List<User> getCurrent() {
        return getCopyOf(current);
    }

    public int getNewUsers() {
        return newUsers;
    }

    public int getDeletedUsers() {
        return deletedUsers;
    }

    public int getChangedUsers() {
        return changedUsers;
    }

    private List<User> getCopyOf(List<User> previous) {
        List<User> users = new ArrayList<>();
        for (User user : previous) {
            if (user != null) {
                users.add(user.getCopy());
            } else {
                users.add(null);
            }
        }
        return users;
    }
}
