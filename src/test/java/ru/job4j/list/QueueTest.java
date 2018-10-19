package ru.job4j.list;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

public class QueueTest {

    private Queue<Integer> queue;

    @Test
    public void should_poll_correctly() {
        //given
        queue = getQueue();

        //when then
        Assert.assertThat(queue.poll(), is(1));
        Assert.assertThat(queue.poll(), is(2));
        Assert.assertThat(queue.poll(), is(3));
        Assert.assertThat(queue.poll(), is(4));
    }

    @Test
    public void should_poll_correctly2() {
        //given
        queue = getQueue();

        //when then
        Assert.assertThat(queue.poll(), is(1));
        queue.push(5);
        Assert.assertThat(queue.poll(), is(2));
        queue.push(6);
        Assert.assertThat(queue.poll(), is(3));
        queue.push(7);
        Assert.assertThat(queue.poll(), is(4));
        queue.push(8);
        Assert.assertThat(queue.poll(), is(5));
        Assert.assertThat(queue.poll(), is(6));
        Assert.assertThat(queue.poll(), is(7));
        Assert.assertThat(queue.poll(), is(8));
    }

    private Queue<Integer> getQueue() {
        Queue<Integer> queue = new Queue<>();
        queue.push(1);
        queue.push(2);
        queue.push(3);
        queue.push(4);
        return queue;
    }

}
