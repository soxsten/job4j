package ru.job4j.Multithreading;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCash {
    private ConcurrentHashMap<Integer, Base> data = new ConcurrentHashMap<>();

    public void add(Base model) {
        data.put(model.getId(), model);
    }

    public void update(Base newModel) {
        Base oldModel = getById(newModel.getId());
        int currentVersion = oldModel.getVersion();
        data.computeIfPresent(newModel.getId(), (integer, base) -> {
            if (currentVersion != oldModel.getVersion()) {
                throw new OptimisticException();
            }
            base.setValue(newModel.getValue());
            base.increaseVersion();
            return base;
        });
    }

    public void delete(Base model) {
        data.remove(model.getId());
    }

    public Base getById(int id) {
        return data.get(id);
    }

    class OptimisticException extends RuntimeException {
        OptimisticException() {
            super("Invalid version");
        }
    }
}
