package ru.job4j.map;


import java.util.Optional;

public interface SimpleTree<E> extends Iterable<E> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     *
     * @param parent parent.
     * @param child  child.
     */
    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);
}