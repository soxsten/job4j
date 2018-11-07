package ru.job4j.list;

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class StackContainer<T> {

    private DynamicLinkContainer<T> container = new DynamicLinkContainer<>();

    public T poll() {
        if (container.getSize() > 0) {
            T t = (T) container.get(0);
            container.deleteFirst();
            return t;
        }
        return null;
    }

    public void push(T value) {
        container.add(value);
    }

    public int getSize() {
        return container.getSize();
    }
}
