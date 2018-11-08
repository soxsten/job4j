package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void should_insert_correctly_without_duplicate() {
        MyHashMap<Integer, Object> map = new MyHashMap<>();
        int expectedMapSize = 3;
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        boolean first = map.insert(1, o1);
        boolean second = map.insert(2, o2);
        boolean third = map.insert(3, o3);
        assertTrue(first);
        assertThat(map.get(1), is(o1));
        assertTrue(second);
        assertThat(map.get(2), is(o2));
        assertTrue(third);
        assertThat(map.get(3), is(o3));
        assertThat(map.getSize(), is(expectedMapSize));
    }

    @Test
    public void should_increase_data_size() {
        MyHashMap<Integer, Object> map = new MyHashMap<>();
        int expectedMapSize = 12;
        Object o0 = new Object();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        Object o4 = new Object();
        Object o5 = new Object();
        Object o6 = new Object();
        Object o7 = new Object();
        Object o8 = new Object();
        Object o9 = new Object();
        Object o10 = new Object();
        Object o11 = new Object();
        boolean zero = map.insert(0, o0);
        boolean first = map.insert(1, o1);
        boolean second = map.insert(2, o2);
        boolean third = map.insert(3, o3);
        boolean fourth = map.insert(4, o4);
        boolean fifth = map.insert(5, o5);
        boolean sixth = map.insert(6, o6);
        boolean seventh = map.insert(7, o7);
        boolean eighth = map.insert(8, o8);
        boolean ninth = map.insert(9, o9);
        boolean tenth = map.insert(10, o10);
        boolean eleventh = map.insert(11, o11);
        assertTrue(zero);
        assertThat(map.get(0), is(o0));
        assertTrue(first);
        assertThat(map.get(1), is(o1));
        assertTrue(second);
        assertThat(map.get(2), is(o2));
        assertTrue(third);
        assertThat(map.get(3), is(o3));
        assertTrue(fourth);
        assertThat(map.get(4), is(o4));
        assertTrue(fifth);
        assertThat(map.get(5), is(o5));
        assertTrue(sixth);
        assertThat(map.get(6), is(o6));
        assertTrue(seventh);
        assertThat(map.get(7), is(o7));
        assertTrue(eighth);
        assertThat(map.get(8), is(o8));
        assertTrue(ninth);
        assertThat(map.get(9), is(o9));
        assertTrue(tenth);
        assertThat(map.get(10), is(o10));
        assertTrue(eleventh);
        assertThat(map.get(11), is(o11));
        assertThat(map.getSize(), is(expectedMapSize));
    }

    @Test
    public void should_delete_correctly() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        int expectedMapSize = 1;
        map.insert("first", 1);
        map.insert("second", 2);
        boolean secondIsDeleted = map.delete("second");
        boolean secondCantBeenDeleted = map.delete("second");
        assertTrue(secondIsDeleted);
        assertFalse(secondCantBeenDeleted);
        assertThat(map.getSize(), is(expectedMapSize));
        assertNull(map.get("second"));
    }

    @Test(expected = NoSuchElementException.class)
    public void should_iterate_correctly() {
        MyHashMap<Integer, Object> map = new MyHashMap<>();
        Object o0 = new Object();
        Object o1 = new Object();
        Object o2 = new Object();
        Object o3 = new Object();
        boolean zero = map.insert(null, o0);
        boolean first = map.insert(1, o1);
        boolean second = map.insert(2, o2);
        boolean third = map.insert(3, o3);
        Iterator<Object> iterator = map.iterator();
        assertTrue(zero);
        assertTrue(first);
        assertTrue(second);
        assertTrue(third);
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o0));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(o3));
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void should_delete_null_key() {
        MyHashMap<Integer, Object> map = new MyHashMap<>();
        int expectedMapSize = 2;
        boolean firstNull = map.insert(null, 1);
        boolean second = map.insert(2, new Object());
        boolean third = map.insert(3, new Object());
        map.delete(null);
        assertTrue(firstNull);
        assertTrue(second);
        assertTrue(third);
        assertThat(map.getSize(), is(expectedMapSize));
    }
}
