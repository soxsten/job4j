package ru.job4j.map;

import java.util.*;

public class MyTreeMap<T extends Comparable<T>> implements SimpleTree<T> {

    private Node<T> root;
    private int size = 0;

    @Override
    public boolean add(T parent, T child) {
        if (root == null) {
            root = new Node<>(parent);
            size++;
        }
        Optional<Node<T>> optional = findBy(parent);
        if (optional.isPresent()) {
            Node<T> node = optional.get();
            node.add(new Node<>(child));
            size++;
            return true;
        }
        return false;
    }

    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> result = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> el = data.poll();
            if (el.eqValue(value)) {
                result = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Queue<Node<T>> nodes = new LinkedList<Node<T>>() {
                {
                    if (root != null) {
                        add(root);
                    }
                }
            };

            @Override
            public boolean hasNext() {
                return !nodes.isEmpty();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> next = nodes.poll();
                if (next != null) {
                    List<Node<T>> leaves = next.leaves();
                    nodes.addAll(leaves);
                    return next.getValue();
                }
                return null;
            }
        };
    }

    public int getSize() {
        return size;
    }
}

class Node<T> {
    private final List<Node<T>> children = new ArrayList<>();
    private final T value;

    public Node(final T value) {
        this.value = value;
    }

    public void add(Node<T> child) {
        this.children.add(child);
    }

    public List<Node<T>> leaves() {
        return this.children;
    }

    public boolean eqValue(T that) {
        return this.value.equals(that);
    }

    public T getValue() {
        return value;
    }
}
