package ru.job4j.list;

/**
 * ����� SimpleArrayList.
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * ����� ��������� � ������ ������ ������.
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * ����������� ����� �������� ������� ������� � ������.
     */
    public E delete() {
        return null;
    }

    /**
     * ����� ��������� �������� �� �������.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * ����� ��������� ������� ���������.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * ����� ������������ ��� �������� ������.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}
