package ru.job4j.list;

@SuppressWarnings("WeakerAccess")
public class Queue<E> {
    private StackContainer<E> container = new StackContainer<>();
    private StackContainer<E> buffer = new StackContainer<>();

    public E poll() {
        return container.poll();
    }

    public void push(E value) {
        put(container, buffer);
        container.push(value);
        put(buffer, container);
    }

    private void put(StackContainer<E> from, StackContainer<E> to) {
        E e = from.poll();
        while (e != null) {
            to.push(e);
            e = from.poll();
        }
    }
}
