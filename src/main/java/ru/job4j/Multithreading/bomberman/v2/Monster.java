package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.v2.Board2.Field;

public class Monster implements Moveable2 {
    private Field field;
    private int speed;
    private int tryLockTime;
    private int waitingTime;

    public Monster(int speed, int tryLockTime, int waitingTime) {
        this.speed = speed;
        this.tryLockTime = tryLockTime;
        this.waitingTime = waitingTime;
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

    @Override
    public int getWaitingTime() {
        return waitingTime;
    }
}
