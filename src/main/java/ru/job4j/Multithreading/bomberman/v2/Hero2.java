package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.v2.Board2.Field;

public class Hero2 implements Moveable2 {
    private Field field;
    private int speed;
    private int tryLockTime;

    public Hero2(int speed, int tryLockTime) {
        this.speed = speed;
        this.tryLockTime = tryLockTime;
    }

    @Override
    public int getTryLockTime() {
        return tryLockTime;
    }

    @Override
    public void setNewPosition(Field field) {
        this.field = field;
    }

    @Override
    public Field getCurrentPosition() {
        return field;
    }

    @Override
    public int getSpeed() {
        return speed;
    }
}
