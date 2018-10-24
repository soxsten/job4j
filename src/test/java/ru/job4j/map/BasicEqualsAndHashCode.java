package ru.job4j.map;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BasicEqualsAndHashCode {
    @Test
    public void name() {
        Map<User, Object> map = new HashMap<>();
        User a = new User("A", 1, Calendar.getInstance());
        User b = new User("A", 1, Calendar.getInstance());
        map.put(a, "1");
        map.put(b, "1");
        System.out.println(map);
        Assert.assertNotEquals(a, b);
    }
}
