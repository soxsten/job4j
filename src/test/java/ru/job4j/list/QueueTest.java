package ru.job4j.list;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class QueueTest {

    @Test
    public void should_poll_correctly() {
        Queue<Integer> queue = getQueue();
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        assertThat(queue.poll(), is(4));
    }

    @Test
    public void should_poll_correctly2() {
        Queue<Integer> queue = getQueue();
        assertThat(queue.poll(), is(1));
        queue.push(5);
        assertThat(queue.poll(), is(2));
        queue.push(6);
        assertThat(queue.poll(), is(3));
        queue.push(7);
        assertThat(queue.poll(), is(4));
        queue.push(8);
        assertThat(queue.poll(), is(5));
        assertThat(queue.poll(), is(6));
        assertThat(queue.poll(), is(7));
        assertThat(queue.poll(), is(8));
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
