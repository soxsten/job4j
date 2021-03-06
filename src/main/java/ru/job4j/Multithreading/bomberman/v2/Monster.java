package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.v2.Board2.Field;

public class Monster implements Moveable2 {
    private Field field;
    private int speed;
    private int tryLockTime;
    private int waitingTime;
    private Type type;

    public Monster(int speed, int tryLockTime, int waitingTime, Type type) {
        this.speed = speed;
        this.tryLockTime = tryLockTime;
        this.waitingTime = waitingTime;
        this.type = type;
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

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    @Override
    public void dead() {
    }
}
