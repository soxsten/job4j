package ru.job4j.Multithreading.bomberman;

import java.util.Random;

public enum Directions {
    UP, DOWN, LEFT, RIGHT;

    public static <T extends Enum<?>> T getRandomDirection(Class<T> clazz) {
        int x = new Random().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
