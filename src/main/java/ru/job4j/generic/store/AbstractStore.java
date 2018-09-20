package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Base;
import ru.job4j.generic.Store;

import java.util.Iterator;

public abstract class AbstractStore<T extends Base> implements Store {

    private SimpleArray<T> data;

    @Override
    public void add(T model) {
        data.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        Iterator<T> iterator = data.iterator();

        int index = 0;
        int idInt = convertToIntFor(id);

        while (iterator.hasNext()) {
            if (idInt == index) {
                data.set(idInt, model);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean delete(String id) {
        data.delete(convertToIntFor(id));
        return true;
    }

    @Override
    public Base findById(String id) {
        return data.get(convertToIntFor(id));
    }

    protected int convertToIntFor(String s) {
        return Integer.parseInt(s);
    }
}
