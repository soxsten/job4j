package ru.job4j.Multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {
    private final ReentrantLock[][] board;
    private Hero heroPosition;
    private int maxX;
    private int maxY;

    public BomberMan(int maxX, int maxY) {
        this.board = setup(maxX, maxY);
    }

    private void waitFor(int t) {
        try {
            wait(t);
        } catch (InterruptedException ignored) {
        }
    }

    private ReentrantLock[][] setup(int x, int y) {
        ReentrantLock[][] locks = new ReentrantLock[x][y];
        for (int i = 0; i < this.maxY; i++) {
            for (int j = 0; j < this.maxX; j++) {
                locks[i][j] = new ReentrantLock();
            }
        }
        return locks;
    }

    private ReentrantLock getField(int x, int y) {
        return board[x][y];
    }

    private boolean moveTo(Directions direction, Moveable object) {
        int speed = object.getSpeed();
        switch (direction) {
            case UP: {
                if (canMoveBy(object.getY() + speed, maxY)) {
                    object.up();
                    return true;
                }
                return false;
            }
            case DOWN: {
                if (canMoveBy(object.getY() - speed, maxY)) {
                    object.down();
                    return true;
                }
                return false;
            }
            case LEFT: {
                if (canMoveBy(object.getX() - speed, maxX)) {
                    object.left();
                    return true;
                }
                return false;
            }
            case RIGHT: {
                if (canMoveBy(object.getX() + speed, maxX)) {
                    object.right();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    private boolean canMoveBy(int coordinate, int coordinateMax) {
        return coordinate >= 0 && coordinate < coordinateMax;
    }
}
