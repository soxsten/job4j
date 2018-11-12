package ru.job4j.Multithreading;

import java.util.concurrent.atomic.AtomicInteger;

class Base {
    private int id;
    private volatile AtomicInteger version;
    private String value;

    public Base(int id, String value) {
        this.id = id;
        this.version = new AtomicInteger(0);
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version.get();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void increaseVersion() {
        version.getAndIncrement();
    }
}
