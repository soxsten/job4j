package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BasicHashcode {
    @Test
    public void name() {
        Map<User, Object> map = new HashMap<>();
        Calendar calendar = Calendar.getInstance();
        NewUser a = new NewUser("A", 1, calendar);
        NewUser b = new NewUser("A", 1, calendar);
        map.put(a, "1");
        map.put(b, "1");
        System.out.println(map);
        Assert.assertEquals(a, b);
        Assert.assertNotEquals(a.hashCode(), b.hashCode());
    }

    private class NewUser extends User {
        NewUser(String name, int children, Calendar birthday) {
            super(name, children, birthday);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return super.getChildren() == user.getChildren() &&
                    Objects.equals(super.getName(), user.getName()) &&
                    Objects.equals(super.getBirthday(), user.getBirthday());
        }
    }
}
