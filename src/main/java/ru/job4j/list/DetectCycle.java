package ru.job4j.list;

public class DetectCycle {
    private DynamicContainer<Integer> hashcodes = new DynamicContainer<>();

    public boolean hasCycle(Node first) {
        while (first.next != null) {
            if (isContain(first)) {
                return true;
            }
            first = first.next;
        }
        return false;
    }

    private boolean isContain(Node node) {
        int hashCode = node.hashCode();
        for (int i = 0; i < hashcodes.getSize(); i++) {
            if (hashcodes.get(i) == null) {
                continue;
            }
            if (hashcodes.get(i) == hashCode) {
                return true;
            }
        }
        hashcodes.add(hashCode);
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
