package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Base;
import ru.job4j.generic.Role;
import ru.job4j.generic.Store;

import java.util.Iterator;

public class RoleStore extends AbstractStore implements Store {

    private SimpleArray<Role> data;

    public RoleStore(SimpleArray<Role> data) {
        this.data = data;
    }

    public void add(Base model) {
        data.add((Role) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        Iterator<Role> iterator = data.iterator();

        int index = 0;
        int idInt = super.convertToIntFor(id);

        while (iterator.hasNext()) {
            if (idInt == index) {
                data.set(idInt, (Role) model);
                return true;
            }
        }

        return false;
    }
}
