package ru.job4j.map;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MyHashMapTest {

    @Test
    public void should_insert_correctly_without_duplicate() {
        //given
        MyHashMap<String, Integer> map = new MyHashMap<>();
        int expectedMapSize = 3;

        //when
        boolean first = map.insert("first", 1);
        boolean second = map.insert("second", 2);
        boolean third = map.insert("third", 3);
        boolean fourth = map.insert("third", 4);

        //then
        assertTrue(first);
        assertThat(map.get("first"), is(1));
        assertTrue(second);
        assertThat(map.get("second"), is(2));
        assertTrue(third);
        assertThat(map.get("third"), is(3));
        assertFalse(fourth);
        assertThat(map.getSize(), is(expectedMapSize));
    }

    @Test
    public void should_increase_data_size() {
        //given
        MyHashMap<String, Integer> map = new MyHashMap<>();
        int expectedMapSize = 12;

        //when
        boolean zero = map.insert("zero", 0);
        boolean first = map.insert("first", 1);
        boolean second = map.insert("second", 2);
        boolean third = map.insert("third", 3);
        boolean fourth = map.insert("fourth", 4);
        boolean fifth = map.insert("fifth", 5);
        boolean sixth = map.insert("sixth", 6);
        boolean seventh = map.insert("seventh", 7);
        boolean eighth = map.insert("eighth", 8);
        boolean ninth = map.insert("ninth", 9);
        boolean tenth = map.insert("tenth", 10);
        boolean eleventh = map.insert("eleventh", 11);

        //then
        assertTrue(zero);
        assertThat(map.get("zero"), is(0));
        assertTrue(first);
        assertThat(map.get("first"), is(1));
        assertTrue(second);
        assertThat(map.get("second"), is(2));
        assertTrue(third);
        assertThat(map.get("third"), is(3));
        assertTrue(fourth);
        assertThat(map.get("fourth"), is(4));
        assertTrue(fifth);
        assertThat(map.get("fifth"), is(5));
        assertTrue(sixth);
        assertThat(map.get("sixth"), is(6));
        assertTrue(seventh);
        assertThat(map.get("seventh"), is(7));
        assertTrue(eighth);
        assertThat(map.get("eighth"), is(8));
        assertTrue(ninth);
        assertThat(map.get("ninth"), is(9));
        assertTrue(tenth);
        assertThat(map.get("tenth"), is(10));
        assertTrue(eleventh);
        assertThat(map.get("eleventh"), is(11));
        assertThat(map.getSize(), is(expectedMapSize));
    }

    @Test
    public void should_delete_correctly() {
        //given
        MyHashMap<String, Integer> map = new MyHashMap<>();
        int expectedMapSize = 2;
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);

        //when
        boolean secondIsDeleted = map.delete("second");
        boolean secondCantBeenDeleted = map.delete("second");

        //then
        assertTrue(secondIsDeleted);
        assertFalse(secondCantBeenDeleted);
        assertThat(map.getSize(), is(expectedMapSize));
        assertNull(map.get("second"));
    }

    @Test(expected = NoSuchElementException.class)
    public void should_iterate_correctly() {
        //given
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert("first", 1);
        map.insert("second", 2);
        map.insert("third", 3);

        //when
        Iterator<Integer> iterator = map.iterator();

        //then
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(2));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(1));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is(3));
        assertFalse(iterator.hasNext());
        iterator.next();
    }

    @Test
    public void should_delete_null_key() {
        //given
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.insert(null, 1);
        map.insert("second", 2);
        map.insert("third", 3);
        int expectedMapSize = 2;

        //when
        map.delete(null);

        //then
        assertThat(map.getSize(), is(expectedMapSize));
    }
}
