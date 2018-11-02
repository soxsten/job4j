package ru.job4j.generic.store;

import Generic.SimpleArray;
import ru.job4j.generic.Base;
import ru.job4j.generic.Store;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractStore<T extends Base> implements Store<T> {
    private SimpleArray<T> data;

    public AbstractStore(SimpleArray<T> data) {
        this.data = data;
    }

    @Override
    public void add(T model) {
        data.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        List<Object> element = findElement(id);
        if (isNotNull(element)) {
            int index = (int) element.get(0);
            data.set(index, model);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean delete(String id) {
        List<Object> indexAndElement = findElement(id);
        if (isNotNull(indexAndElement)) {
            int index = (int) indexAndElement.get(0);
            this.data.delete(index);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T findById(String id) {
        List<Object> element = findElement(id);
        if (isNotNull(element)) {
            return (T) element.get(1);
        }
        return null;
    }

    private List<Object> findElement(String id) {
        Iterator<T> iterator = data.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            if (currentElement.getId().equals(id)) {
                List<Object> list = new ArrayList<>();
                list.add(index);
                list.add(currentElement);
                return list;
            }
            index++;
        }
        return null;
    }

    private boolean isNotNull(Object o) {
        return o != null;
    }

    protected SimpleArray<T> getData() {
        return data;
    }
}
