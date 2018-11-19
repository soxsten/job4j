package ru.job4j.Multithreading.bomberman;

import ru.job4j.Multithreading.bomberman.Board2.Field;

public interface Moveable2 {
    Field getCurrentPosition();

    void setNewPosition(Field field);

    int getSpeed();
}
