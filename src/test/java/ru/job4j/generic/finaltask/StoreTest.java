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
        boolean isUpdated = store.updateUserById(new User(0, "zero"));
        Assert.assertFalse(isUpdated);
    }

    @Test
    public void should_update_user() {
        List<User> users = getList();
        Store store = new Store(users);
        int expectedId = 0;
        String expectedName = "zero";
        boolean isUpdated = store.updateUserById(new User(1, "updated first"));
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
        Assert.assertNull(user);
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
