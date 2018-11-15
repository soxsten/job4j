package ru.job4j.Multithreading.bomberman;

public interface Moveable {
    int getSpeed();

    int getX();

    int getY();

    void up();

    void down();

    void left();

    void right();
}
