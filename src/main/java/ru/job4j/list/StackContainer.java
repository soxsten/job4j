package ru.job4j.list;

import java.util.NoSuchElementException;

@SuppressWarnings({"WeakerAccess", "unchecked"})
public class StackContainer<T> {

    private DynamicLinkContainerExtend<T> container = new DynamicLinkContainerExtend<>();

    public T poll() {
        T t = (T) container.get(0);
        container.deleteFirst();
        return t;
    }

    public void push(T value) {
        container.add(value);
    }

    public int getSize() {
        return container.getSize();
    }

    class DynamicLinkContainerExtend<E> extends DynamicLinkContainer {
        public E deleteFirst() {
            if (this.size <= 0) {
                throw new NoSuchElementException();
            }
            Node first = this.first;
            E date = (E) first.date;
            this.first = this.first.next;
            this.size--;
            return date;
        }
    }
}
