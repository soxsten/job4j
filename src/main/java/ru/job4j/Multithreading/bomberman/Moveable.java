package ru.job4j.Multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public interface Moveable {
    ReentrantLock getCurrentPosition();

    Coordinates getCoordinates();

    int getSpeed();

    int getX();

    int getY();

    void move(Directions direction);
}
