package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;


public class DynamicContainerTest {

    private DynamicContainer<Integer> container;

    @Test
    public void should_add_values_and_increase_container_size_to_seven() {
        //given
        int containerSize = 5;
        container = new DynamicContainer<>(containerSize);

        //when
        container.add(1);
        Assert.assertThat(container.get(0), is(1));
        container.add(2);
        Assert.assertThat(container.get(1), is(2));
        container.add(3);
        Assert.assertThat(container.get(2), is(3));
        container.add(4);
        Assert.assertThat(container.get(3), is(4));
        container.add(5);
        Assert.assertThat(container.get(4), is(5));
        container.add(6);
        Assert.assertThat(container.getSize(), is(7));
        Assert.assertThat(container.get(5), is(6));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void should_iterate_correctly_and_throw_if_container_modyfied() {
        //given
        container = getContainer();
        Iterator<Integer> iterator = container.iterator();

        //when
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
        container.add(4);
        iterator.next();
    }

    private DynamicContainer<Integer> getContainer() {
        DynamicContainer<Integer> integers = new DynamicContainer<>(3);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        return integers;
    }
}