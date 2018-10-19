package ru.job4j.list;

public class StackContainer<E> {

    private Node<E> node;
    private int size;

    public E poll() {
        if (node == null) {
            return null;
        }
        E date = node.date;
        node = node.previous;
        size--;
        return date;
    }

    public void push(E value) {
        if (node == null) {
            node = new Node<>(value);
        } else {
            node = new Node<>(value, node);
        }
        size++;
    }

    public int getSize() {
        return size;
    }

    private static class Node<E> {
        E date;
        Node<E> previous;

        Node(E date) {
            this.date = date;
        }

        Node(E date, Node<E> previous) {
            this.date = date;
            this.previous = previous;
        }
    }
}
