package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Role;

public class RoleStore extends AbstractStore {

    private SimpleArray<Role> data;

    public RoleStore(SimpleArray<Role> data) {
        this.data = data;
    }
}
