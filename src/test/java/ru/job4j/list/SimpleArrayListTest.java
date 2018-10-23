package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(getList().get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(getList().getSize(), is(3));
    }

    @Test
    public void whenDeleteOneElementThenUseGetSizeResultTwo() {
        //when
        SimpleArrayList<Integer> list = getList();
        list.delete();
        int actualSize = list.getSize();

        //then
        assertThat(actualSize, is(2));
    }

    @Test
    public void whenDeleteFirstElementThenEqualExpectedList() {
        //given
        SimpleArrayList<Integer> expected = getList();
        expected = new SimpleArrayList<>();
        expected.add(1);
        expected.add(2);

        //when
        SimpleArrayList<Integer> actual = getList();
        Integer delete = actual.delete();

        //then
        assertThat(delete, is(3));
        assertThat(isListEquals(expected, actual), is(true));
    }

    @Test(expected = NoSuchElementException.class)
    public void iterator() {
        //given
        SimpleArrayList<Integer> list = getList();
        //when then
        Iterator<Integer> iterator = list.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertFalse(iterator.hasNext());
        iterator.next();
    }

    private boolean isListEquals(SimpleArrayList expected, SimpleArrayList actual) {
        if (expected.getSize() != actual.getSize()) {
            return false;
        }
        for (int i = 0; i < expected.getSize(); i++) {
            Object o1 = expected.get(i);
            Object o2 = actual.get(i);
            if (!o1.equals(o2)) {
                return false;
            }
        }
        return true;
    }

    private SimpleArrayList<Integer> getList() {
        SimpleArrayList<Integer> list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        return list;
    }
}
