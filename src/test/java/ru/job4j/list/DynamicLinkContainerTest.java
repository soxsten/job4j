package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;

public class DynamicLinkContainerTest {

    private DynamicLinkContainer<Integer> container;

    @Test
    public void should_added_correctly() {
        //given
        DynamicLinkContainer<Integer> expected = getContainer();
        container = new DynamicLinkContainer<>();

        //when
        container.add(1);
        container.add(2);
        container.add(3);

        //then
        Assert.assertThat(container.getSize(), is(3));
    }

    @Test
    public void should_get_correctly() {
        //given
        DynamicLinkContainer<Integer> container = getContainer();

        //when then
        Assert.assertNull(container.get(-1));
        Assert.assertThat(container.get(0), is(3));
        Assert.assertThat(container.get(1), is(2));
        Assert.assertThat(container.get(2), is(1));
        Assert.assertNull(container.get(3));
    }

    @Test
    public void should_iterate_correctly() {
        //given
        Iterator<Integer> iterator = getContainer().iterator();

        //when
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
        Assert.assertFalse(iterator.hasNext());
        Assert.assertNull(iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void should_throw() {
        //given
        DynamicLinkContainer<Integer> container = getContainer();
        Iterator<Integer> iterator = container.iterator();

        //when
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(3));
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(2));
        container.add(6);
        Assert.assertTrue(iterator.hasNext());
        Assert.assertThat(iterator.next(), is(1));
    }

    private DynamicLinkContainer<Integer> getContainer() {
        DynamicLinkContainer<Integer> container = new DynamicLinkContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        return container;
    }
}