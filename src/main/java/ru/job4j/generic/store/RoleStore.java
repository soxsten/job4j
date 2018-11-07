package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Role;

public class RoleStore extends AbstractStore<Role> {
    RoleStore(SimpleArray<Role> data) {
        super(data);
    }

    public SimpleArray<Role> getData() {
        return super.getData();
    }
}
