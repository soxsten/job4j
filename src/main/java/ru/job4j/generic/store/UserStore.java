package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.User;

public class UserStore extends AbstractStore<User> {
    private SimpleArray<User> data;

    public UserStore(SimpleArray<User> data) {
        super(data);
    }
}
