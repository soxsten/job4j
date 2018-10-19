package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

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
        Assert.assertThat(container.get(0), is(3));
        Assert.assertThat(container.get(1), is(2));
        Assert.assertThat(container.get(2), is(1));
        Assert.assertNull(container.get(3));
    }

    private DynamicLinkContainer<Integer> getContainer() {
        DynamicLinkContainer<Integer> container = new DynamicLinkContainer<>();
        container.add(1);
        container.add(2);
        container.add(3);
        return container;
    }
}