package ru.job4j.map;

import java.util.*;

public class MyTreeMap<T extends Comparable<T>> implements SimpleTree {

    private Node<T> root;
    private int size = 0;

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(Object parent, Object child) {
        Optional<Node> optional = findBy(parent);
        if (optional.isPresent()) {
            Node node = optional.get();
            if (child instanceof Node) {
                node.add((Node) child);
                size++;
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Optional<Node> findBy(Object value) {
        Optional<Node> result = Optional.empty();
        if (value == null) {
            return result;
        }
        Queue<Node> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node el = data.poll();
            if (el.eqValue((T) value)) {
                result = Optional.of(el);
                break;
            }
            for (Object child : el.leaves()) {
                data.offer((Node) child);
            }
        }
        return result;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;
            private Queue<Node> level = new LinkedList<>();

            {
                level.add(root);
            }

            @Override
            public boolean hasNext() {
                return level.isEmpty();
            }

            @Override
            public Object next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }


                return null;
            }
        };
    }
}

class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
        new TreeMap<>()
    }

    public void add(Node<E> child) {
        this.children.add(child);
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that);
    }
}
