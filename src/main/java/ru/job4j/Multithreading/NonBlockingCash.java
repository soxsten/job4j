package ru.job4j.Multithreading;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {
    private ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base model) {
        data.put(model.id, model);
    }

    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public void update(Base model) {
        Base dataModel = getByModel(model);
        int currentVersion = dataModel.version;
        synchronized (dataModel) {
            if (currentVersion != dataModel.version) {
                throw new OptimisticException();
            }
            dataModel.value = model.value;
            dataModel.version++;
        }
    }

    private void updateFields() {

    }

    public void delete(Base model) {
        data.remove(model.id);
    }

    public Base getByModel(Base model) {
        return data.get(model.id);
    }

    public Base getById(int id) {
        return data.get(id);
    }

    static class Base {
        int id;
        volatile int version;
        String value;

        public Base(int id, String value) {
            this.id = id;
            this.version = 0;
            this.value = value;
        }
    }

    class OptimisticException extends RuntimeException {
        OptimisticException() {
            super("Invalid version");
        }
    }
}
