package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;

public class StackContainerTest {

    private StackContainer<Integer> container;

    @Test
    public void should_increase_container_size_to_three() {
        //given
        container = new StackContainer<>();

        //when
        container.push(1);
        container.push(2);
        container.push(3);

        //then
        Assert.assertThat(container.getSize(), is(3));
    }

    @Test(expected = NoSuchElementException.class)
    public void should_poll_correctly() {
        //given
        container = getContainer();

        //when then
        Assert.assertThat(container.poll(), is(3));
        Assert.assertThat(container.poll(), is(2));
        Assert.assertThat(container.poll(), is(1));
        container.poll();
    }

    @Test
    public void should_return_three() {
        //given
        container = new StackContainer<>();
        container.push(1);
        container.push(2);

        //when
        container.push(3);

        //then
        Assert.assertThat(container.poll(), is(3));
    }

    private StackContainer<Integer> getContainer() {
        StackContainer<Integer> container = new StackContainer<>();
        container.push(1);
        container.push(2);
        container.push(3);
        return container;
    }
}