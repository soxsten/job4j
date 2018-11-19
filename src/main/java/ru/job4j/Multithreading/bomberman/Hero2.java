package ru.job4j.Multithreading.bomberman;

import ru.job4j.Multithreading.bomberman.Board2.Field;

public class Hero2 implements Moveable2 {
    private Field field;
    private int speed;

    public Hero2(Field field, int speed) {
        this.field = field;
        this.speed = speed;
    }

    @Override
    public void setNewPosition(Field field) {
        this.field = field;
    }

    @Override
    public Field getCurrentPosition() {
        return field;
    }

    public int getSpeed() {
        return speed;
    }
}
