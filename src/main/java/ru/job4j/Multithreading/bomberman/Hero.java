package ru.job4j.Multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

class Hero implements Moveable {
    private final Coordinates coordinates;
    private int speed = 1;
    private ReentrantLock currentPosition;

    Hero(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int getX() {
        return coordinates.getX();
    }

    @Override
    public int getY() {
        return coordinates.getY();
    }

    @Override
    public ReentrantLock getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move(Directions direction) {
        switch (direction) {
            case UP: {
                coordinates.setY(coordinates.getY() + speed);
                break;
            }
            case DOWN: {
                coordinates.setY(coordinates.getY() - speed);
                break;
            }
            case LEFT: {
                coordinates.setX(coordinates.getX() - speed);
                break;
            }
            case RIGHT: {
                coordinates.setX(coordinates.getX() + speed);
                break;
            }
        }
    }
}
