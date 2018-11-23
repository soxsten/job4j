package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.v2.Board2.Field;

public interface Moveable2 {
    Field getCurrentPosition();

    void setNewPosition(Field field);

    int getSpeed();

    int getTryLockTime();

    int getWaitingTime();

    Type getType();
}
