package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Base;
import ru.job4j.generic.Store;
import ru.job4j.generic.User;

import java.util.Iterator;

public class UserStore extends AbstractStore implements Store {

    private SimpleArray<User> data;

    public UserStore(SimpleArray<User> data) {
        this.data = data;
    }

    public void add(Base model) {
        data.add((User) model);
    }

    @Override
    public boolean replace(String id, Base model) {
        Iterator<User> iterator = data.iterator();

        int index = 0;
        int idInt = super.convertToIntFor(id);

        while (iterator.hasNext()) {
            if (idInt == index) {
                data.set(idInt, (User) model);
                return true;
            }
        }

        return false;
    }
}
