package ru.job4j.generic.finaltask;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class Store2Test {
    @Test
    public void should_get_2_new_users() {
        Store2 store2 = new Store2();
        List<Store2.User> prev = new ArrayList<>();
        prev.add(new Store2.User(1, "first"));
        prev.add(new Store2.User(2, "second"));
        List<Store2.User> current = new ArrayList<>();
        current.add(new Store2.User(1, "first"));
        current.add(new Store2.User(2, "second"));
        current.add(new Store2.User(3, "third"));
        current.add(new Store2.User(4, "fourth"));
        Store2.Info info = store2.diff(prev, current);
        assertThat(info.getNewUsers(), is(2));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(info.getChangedUsers(), is(0));
    }

    @Test
    public void should_get_1_changed_user_and_1_new_user() {
        Store2 store2 = new Store2();
        List<Store2.User> prev = new ArrayList<>();
        prev.add(new Store2.User(1, "first"));
        prev.add(new Store2.User(2, "second"));
        prev.add(new Store2.User(3, "third"));
        List<Store2.User> current = new ArrayList<>();
        current.add(new Store2.User(1, "first"));
        current.add(new Store2.User(2, "second"));
        current.add(new Store2.User(3, "new third"));
        current.add(new Store2.User(4, "fourth"));
        Store2.Info info = store2.diff(prev, current);
        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(info.getChangedUsers(), is(1));
    }

    @Test
    public void should_get_1_deleted_user_and_1_new_user() {
        Store2 store2 = new Store2();
        List<Store2.User> prev = new ArrayList<>();
        prev.add(new Store2.User(1, "first"));
        prev.add(new Store2.User(2, "second"));
        prev.add(new Store2.User(3, "third"));
        List<Store2.User> current = new ArrayList<>();
        current.add(new Store2.User(1, "first"));
        current.add(new Store2.User(2, "second"));
        current.add(new Store2.User(4, "fourth"));
        Store2.Info info = store2.diff(prev, current);
        assertThat(info.getNewUsers(), is(1));
        assertThat(info.getDeletedUsers(), is(1));
        assertThat(info.getChangedUsers(), is(0));
    }
}