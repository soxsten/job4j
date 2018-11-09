package ru.job4j.Multithreading;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {
    private ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base model) {
        data.put(model.id, model);
    }

    public void update(Base newModel) {
        Base oldModel = getById(newModel.id);
        int currentVersion = oldModel.version;
        synchronized (oldModel) {
            if (currentVersion != oldModel.version) {
                throw new OptimisticException();
            }
            data.computeIfPresent(newModel.id, (integer, base) -> {
                base.value = newModel.value;
                base.version++;
                return base;
            });
        }
    }

    public void delete(Base model) {
        data.remove(model.id);
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
