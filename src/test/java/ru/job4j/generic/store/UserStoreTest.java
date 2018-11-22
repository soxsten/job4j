package ru.job4j.generic.store;

import Generic.SimpleArray;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.generic.Role;

import static org.hamcrest.Matchers.is;

public class UserStoreTest {

    @Test
    public void replace_should_set_new_role_with_6_id() {
        SimpleArray<Role> data = getTestData();
        RoleStore store = new RoleStore(data);
        SimpleArray<Role> expected = expectedData();
        store.replace("3", new Role("6"));
        SimpleArray<Role> actual = store.getData();
        Assert.assertTrue(isRolesEquals(expected.get(0), actual.get(0)));
        Assert.assertTrue(isRolesEquals(expected.get(1), actual.get(1)));
        Assert.assertTrue(isRolesEquals(expected.get(2), actual.get(2)));
        Assert.assertTrue(isRolesEquals(expected.get(3), actual.get(3)));
        Assert.assertTrue(isRolesEquals(expected.get(4), actual.get(4)));
    }

    @Test
    public void add() {
        RoleStore store = new RoleStore(new SimpleArray<>(5));
        Role expected = new Role("0");
        store.add(expected);
        SimpleArray<Role> data = store.getData();
        Role actual = data.get(0);
        Assert.assertThat(expected, is(actual));
    }

    @Test
    public void delete() {
        SimpleArray<Role> input = getTestData();
        RoleStore store = new RoleStore(input);
        String roleId = "2";
        store.delete(roleId);
        SimpleArray<Role> data = store.getData();
        boolean isContain = false;
        for (Role role : data) {
            if (role.getId().equals(roleId)) {
                isContain = true;
            }
        }
        Assert.assertFalse(isContain);
    }

    @Test
    public void findById() {
        SimpleArray<Role> data = getTestData();
        RoleStore store = new RoleStore(data);
        Role expected = data.get(3);
        Role actual = store.findById("3");
        Assert.assertThat(expected, is(actual));
    }

    private SimpleArray<Role> getTestData() {
        SimpleArray<Role> data = new SimpleArray<>(5);
        data.add(new Role("0"));
        data.add(new Role("1"));
        data.add(new Role("2"));
        data.add(new Role("3"));
        data.add(new Role("4"));
        return data;
    }

    private SimpleArray<Role> expectedData() {
        SimpleArray<Role> expected = new SimpleArray<>(5);
        expected.add(new Role("0"));
        expected.add(new Role("1"));
        expected.add(new Role("2"));
        expected.add(new Role("6"));
        expected.add(new Role("4"));
        return expected;
    }

    private boolean isRolesEquals(Role expected, Role actual) {
        return expected.getId().equals(actual.getId());
    }
}
