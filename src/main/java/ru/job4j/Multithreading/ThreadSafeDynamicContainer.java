package ru.job4j.Multithreading;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

@ThreadSafe
public class ThreadSafeDynamicContainer<E> extends DynamicContainer<E> {
    @GuardedBy("this")
    private final DynamicContainer<E> container = new DynamicContainer<>();

    @Override
    public synchronized Iterator<E> iterator() {
        return getSnapshot().iterator();
    }

    private synchronized DynamicContainer<E> getSnapshot() {
        DynamicContainer<E> newContainer = new DynamicContainer<>();
        container.forEach(newContainer::add);
        return newContainer;
    }

    @Override
    public synchronized void add(E value) {
        container.add(value);
    }

    @Override
    public synchronized E get(int index) {
        return super.get(index);
    }

    @Override
    public synchronized int getSize() {
        return super.getSize();
    }
}
