package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.list.DetectCycle.Node;

public class DetectCycleTest {

    @Test
    public void should_detect_loop_at_end() {
        //given
        Node first = new Node<>(1);
        Node two = new Node<>(2);
        Node third = new Node<>(3);
        Node four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;

        //when
        boolean actual = new DetectCycle().hasCycle(first);

        //then
        Assert.assertTrue(actual);
    }

    @Test
    public void should_detect_loop_at_middle() {
        //given
        Node first = new Node<>(1);
        Node two = new Node<>(2);
        Node third = new Node<>(3);
        Node four = new Node<>(4);

        first.next = two;
        two.next = third;
        third.next = two;
        four.next = first;

        //when
        boolean actual = new DetectCycle().hasCycle(first);

        //then
        Assert.assertTrue(actual);
    }
}