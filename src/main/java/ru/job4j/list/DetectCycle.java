package ru.job4j.list;

public class DetectCycle {

    public boolean hasCycle(Node first) {
        if (first == null) {
            return false;
        }

        Node tortoise = first;
        Node hare = first;

        while (hare.next != null && tortoise.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (hare == null) {
                return false;
            }

            if (tortoise == hare) {
                return true;
            }
        }

        return false;
    }

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
