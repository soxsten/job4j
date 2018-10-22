package ru.job4j.list;

public class DetectCycle {

    public boolean hasCycle(Node first) {
        if (first == null) {
            return false;
        }

        Node tortoise = first;
        Node hare = first;

        while (true) {
            tortoise = tortoise.next;

            if (hare.next != null) {
                hare = hare.next.next;
            } else {
                return false;
            }

            if ((tortoise == null) || (hare == null)) {
                return false;
            }

            if (tortoise == hare) {
                return true;
            }
        }
    }

    static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
