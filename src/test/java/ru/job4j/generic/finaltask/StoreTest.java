package ru.job4j.generic.finaltask;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class StoreTest {
    @Test
    public void should_not_delete_via_link() {
        List<User> users = getList();
        Store store = new Store(users);
        int expectedId = 0;
        String expectedName = "zero";
        User user = store.getUserBy(expectedId);
        user = null;
        User object = store.getUserBy(expectedId);
        assertNotNull(object);
        assertThat(object.getId(), is(expectedId));
        assertThat(object.getName(), is(expectedName));
    }

    @Test
    public void should_not_update_user() {
        List<User> users = getList();
        Store store = new Store(users);
        boolean isUpdated = store.updateUserBy(new User(0, "zero"));
        Assert.assertFalse(isUpdated);
    }

    @Test
    public void should_update_user() {
        List<User> users = getList();
        Store store = new Store(users);
        int expectedId = 0;
        String expectedName = "zero";
        boolean isUpdated = store.updateUserBy(new User(1, "updated first"));
        assertTrue(isUpdated);
        User updatedUser = store.getUserBy(expectedId);
        assertThat(updatedUser.getId(), is(expectedId));
        assertThat(updatedUser.getName(), is(expectedName));
    }

    @Test
    public void should_add_user() {
        List<User> users = getList();
        Store store = new Store(users);
        int expectedId = 4;
        String expectedName = "fourth";
        boolean isUserAdded = store.addUser(new User(expectedId, expectedName));
        boolean isNullAdded = store.addUser(null);
        User user = store.getUserBy(4);
        assertTrue(isUserAdded);
        assertFalse(isNullAdded);
        assertThat(user.getId(), is(expectedId));
        assertThat(user.getName(), is(expectedName));
    }

    @Test
    public void should_delete_user() {
        List<User> users = getList();
        Store store = new Store(users);
        store.deleteUser(new User(1, "first"));
        User user = store.getUserBy(1);
        assertNull(user);
    }

    @Test
    public void should_get_statistic_of_deleted_users() {
        List<User> users = getList();
        Store store = new Store(users);
        User first = new User(1, "first");
        store.deleteUser(first);
        Info info = store.getInfo();
        List<User> previous = info.getPrevious();
        List<User> current = info.getCurrent();
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getChangedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(1));
        assertThat(previous.size(), is(4));
        assertThat(previous.get(0), is(users.get(0)));
        assertThat(previous.get(1), is(first));
        assertThat(previous.get(2), is(users.get(2)));
        assertThat(previous.get(3), is(users.get(3)));
        assertThat(current.get(0), is(current.get(0)));
        assertThat(current.get(2), is(current.get(2)));
        assertThat(current.get(3), is(current.get(3)));
    }

    @Test
    public void should_get_statistics_of_new_users() {
        List<User> users = new ArrayList<>();
        Store store = new Store(users);
        User zero = new User(0, "zero");
        User first = new User(1, "first");
        User fourth = new User(4, "fourth");
        store.addUser(zero);
        store.addUser(first);
        store.addUser(fourth);
        Info info = store.getInfo();
        List<User> previous = info.getPrevious();
        List<User> current = info.getCurrent();
        assertThat(info.getNewUsers(), is(3));
        assertThat(info.getChangedUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(previous.size(), is(0));
        assertThat(current.size(), is(3));
        assertThat(current.get(0), is(zero));
        assertThat(current.get(1), is(first));
        assertThat(current.get(2), is(fourth));
    }

    @Test
    public void should_get_statistics_of_updated_users() {
        List<User> users = getList();
        Store store = new Store(users);
        User zero = new User(0, "new zero");
        User third = new User(3, "new third");
        store.updateUserBy(zero);
        store.updateUserBy(third);
        Info info = store.getInfo();
        List<User> current = info.getCurrent();
        List<User> previous = info.getPrevious();
        assertThat(info.getChangedUsers(), is(2));
        assertThat(info.getNewUsers(), is(0));
        assertThat(info.getDeletedUsers(), is(0));
        assertThat(previous.size(), is(4));
        assertThat(current.size(), is(4));
        assertThat(current.get(0), is(zero));
        assertThat(current.get(3), is(third));
    }

    private List<User> getList() {
        List<User> users = new ArrayList<>();
        users.add(new User(0, "zero"));
        users.add(new User(1, "first"));
        users.add(new User(2, "second"));
        users.add(new User(3, "third"));
        return users;
    }
}
