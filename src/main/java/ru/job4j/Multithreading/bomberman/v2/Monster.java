package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.v2.Board2.Field;

public class Monster implements Moveable2 {
    private Field field;
    private int speed;
    private int tryLockTime;

    public Monster(Field field, int speed, int tryLockTime) {
        this.field = field;
        this.speed = speed;
        this.tryLockTime = tryLockTime;
    }

    @Override
    public int getTryLockTime() {
        return tryLockTime;
    }

    @Override
    public Field getCurrentPosition() {
        return field;
    }

    @Override
    public void setNewPosition(Field field) {
        this.field = field;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
