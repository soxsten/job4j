package ru.job4j.generic.store;

import Generic.SimpleArray;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.generic.Role;

public class UserStoreTest {

    private RoleStore roleStore;
    private SimpleArray<Role> data = new SimpleArray<>(5);

    @Before
    public void setUp() throws Exception {
        fillData();
    }

    @Test
    public void replace_should_set_new_role_with_6_id() {
        //given
        roleStore = new RoleStore(data);
        SimpleArray<Role> expectedData = expectedData();

        //when
        roleStore.replace("3", new Role("6"));
        SimpleArray<Role> actual = roleStore.getData();

        //then
        Assert.assertTrue(isRolesEquals(expectedData.get(0), actual.get(0)));
        Assert.assertTrue(isRolesEquals(expectedData.get(1), actual.get(1)));
        Assert.assertTrue(isRolesEquals(expectedData.get(2), actual.get(2)));
        Assert.assertTrue(isRolesEquals(expectedData.get(3), actual.get(3)));
        Assert.assertTrue(isRolesEquals(expectedData.get(4), actual.get(4)));
    }

    private void fillData() {
        data.add(new Role("0"));
        data.add(new Role("1"));
        data.add(new Role("2"));
        data.add(new Role("3"));
        data.add(new Role("4"));
    }

    private SimpleArray<Role> expectedData() {
        SimpleArray<Role> expectedData = new SimpleArray<>(5);

        expectedData.add(new Role("0"));
        expectedData.add(new Role("1"));
        expectedData.add(new Role("2"));
        expectedData.add(new Role("6"));
        expectedData.add(new Role("4"));

        return expectedData;
    }

    private boolean isRolesEquals(Role expected, Role actual) {
        return expected.getId().equals(actual.getId());
    }
}
