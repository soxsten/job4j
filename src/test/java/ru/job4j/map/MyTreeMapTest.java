package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyTreeMapTest {

    @Test
    public void should_add_5_elements() {
        //given
        MyTreeMap<Integer> tree = new MyTreeMap<>();
        int expectedSize = 6;

        //when
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        boolean isDuplicateAdded = tree.add(4, 4);
        tree.add(5, 6);

        //then
        assertFalse(isDuplicateAdded);
        assertThat(tree.getSize(), is(expectedSize));
    }

    @Test
    public void add_should_return_false() {
        //given
        MyTreeMap<Integer> tree = new MyTreeMap<>();

        //when
        tree.add(1, 2);
        boolean isAdded = tree.add(3, 4);

        //then
        assertFalse(isAdded);
    }

    @Test(expected = NoSuchElementException.class)
    public void should_iterate_correctly() {
        //given
        MyTreeMap<Integer> tree = new MyTreeMap<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        //when
        Iterator<Integer> iterator = tree.iterator();
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(4));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(5));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(6));
        iterator.next();
    }

    @Test
    public void isBinaty_should_return_true() {
        //given
        MyTreeMap<Integer> tree = new MyTreeMap<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);

        //when then
        Assert.assertTrue(tree.isBinary());
    }

    @Test
    public void isBinaty_should_return_false() {
        //given
        MyTreeMap<Integer> tree = new MyTreeMap<>();
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);
        tree.add(5, 6);
        tree.add(5, 7);
        tree.add(5, 8);

        //when then
        Assert.assertFalse(tree.isBinary());
    }
}
