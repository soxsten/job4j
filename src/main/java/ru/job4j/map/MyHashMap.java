package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class MyHashMap<K, V> implements Iterable<V> {

    private Object[] data;
    private int size = 0;
    private double loadFactor;
    private int threshold;

    public MyHashMap(int capacity, double loadFactor) {
        this.data = new Object[capacity];
        this.loadFactor = loadFactor;
        this.threshold = (int) (capacity * loadFactor);
    }

    public MyHashMap() {
        this.data = new Object[10];
        this.loadFactor = 0.75;
        this.threshold = (int) (10 * loadFactor);
    }

    private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    public boolean insert(K key, V value) {
        if (key != null) {
            if (shouldIncreaseDataSize()) {
                increaseDataSize();
            }
            int hash = hash(key.hashCode());
            int i = indexFor(hash, data.length);
            if (data[i] == null) {
                data[i] = new Node<>(value.hashCode(), key, value, i);
                size++;
                return true;
            } else {
                return false;
            }
        } else {
            putForNullKey(value);
            size++;
        }
        return false;
    }

    private boolean shouldIncreaseDataSize() {
        int currentThreshold = (int) (data.length * loadFactor);
        return currentThreshold >= threshold;
    }

    private void putForNullKey(V value) {
        data[0] = new Node<>(value);
    }

    V get(K key) {
        Node node = findBy(key);
        return node == null ? null : (V) node.value;
    }

    boolean delete(K key) {
        if (key == null) {
            data[0] = null;
            size--;
            return true;
        }
        Node node = findBy(key);
        if (node != null) {
            data[node.id] = null;
            size--;
            return true;
        }
        return false;
    }

    public int getSize() {
        return size;
    }

    private Node findBy(K key) {
        for (Object o : data) {
            if (o != null) {
                Node node = (Node) o;
                if (key == node.key) {
                    return node;
                }
            }
        }
        return null;
    }

    private void increaseDataSize() {
        Object[] newData = new Object[(int) (data.length * 1.5)];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int numberOfElements = 0;
            private int index = 0;

            @Override
            public boolean hasNext() {
                return numberOfElements < getSize();
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node;
                do {
                    node = (Node) data[index++];
                } while (node == null);
                numberOfElements++;
                return (V) node.value;
            }
        };
    }

    private static class Node<K, V> {
        private Integer hash;
        private K key;
        private V value;
        private Integer id;

        public Node(int hash, K key, V value, int id) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.id = id;
        }

        public Node(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return Objects.equals(hash, node.hash) &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value) &&
                    Objects.equals(id, node.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value, id);
        }
    }
}
