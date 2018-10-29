package ru.job4j.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class MyHashMap<K, V> implements Iterable<V> {

    private Object[] data = new Object[10];
    private int size = 0;

    public boolean insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value, value.hashCode());
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null) {
                data[i] = newNode;
                size++;
                return true;
            }
            Node node = (Node) data[i];
            if (key == node.getKey()) {
                return false;
            }
            if (data[i] != null && i == data.length - 1) {
                increaseDataSize();
            }
        }
        return false;
    }

    V get(K key) {
        Node node = findBy(key);
        return node == null ? null : (V) node.getValue();
    }

    boolean delete(K key) {
        Node node = findBy(key);
        if (node != null) {
            node = null;
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
            Node node = (Node) o;
            if (key == node.getKey()) {
                return node;
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
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < data.length;
            }

            @Override
            public V next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node node = (Node) data[index++];
                return (V) node.getValue();
            }
        };
    }

    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;

        public Node(K key, V value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public int getHash() {
            return hash;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return hash == node.hash &&
                    Objects.equals(key, node.key) &&
                    Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(hash, key, value);
        }
    }
}
