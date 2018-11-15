package ru.job4j.Multithreading.bomberman;

import java.util.concurrent.locks.ReentrantLock;

public class BomberMan {
    private final ReentrantLock[][] board;
    private Hero hero = new Hero(0, 0);
    private int maxX;
    private int maxY;

    public BomberMan(int maxX, int maxY) {
        this.board = setup(maxX, maxY);
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

    public boolean moveUp() {
        return moveTo(Directions.UP, hero);
    }

    public boolean moveDown() {
        return moveTo(Directions.DOWN, hero);
    }

    public boolean moveLeft() {
        return moveTo(Directions.LEFT, hero);
    }

    public boolean moveRight() {
        return moveTo(Directions.RIGHT, hero);
    }

    private boolean moveTo(Directions direction, Moveable object) {
        int speed = object.getSpeed();
        ReentrantLock currentPosition = getField(object.getX(), object.getY());
        switch (direction) {
            case UP: {
                if (canMoveBy(object.getY() + speed, maxY)) {
                    ReentrantLock field = getField(object.getX(), object.getY() + speed);
                    if (tryLockFieldBy(object, field)) {
                        object.up();
                        currentPosition.unlock();
                        return true;
                    }
                }
                return false;
            }
            case DOWN: {
                if (canMoveBy(object.getY() - speed, maxY)) {
                    ReentrantLock field = getField(object.getX(), object.getY() - speed);
                    if (tryLockFieldBy(object, field)) {
                        object.down();
                        currentPosition.unlock();
                        return true;
                    }
                }
                return false;
            }
            case LEFT: {
                if (canMoveBy(object.getX() - speed, maxX)) {
                    ReentrantLock field = getField(object.getX() - speed, object.getY());
                    if (tryLockFieldBy(object, field)) {
                        object.left();
                        currentPosition.unlock();
                        return true;
                    }
                }
                return false;
            }
            case RIGHT: {
                if (canMoveBy(object.getX() + speed, maxX)) {
                    ReentrantLock field = getField(object.getX() + speed, object.getY());
                    if (tryLockFieldBy(object, field)) {
                        object.right();
                        currentPosition.unlock();
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private ReentrantLock getField(int x, int y) {
        return board[x][y];
    }

    private boolean tryLockFieldBy(Moveable object, ReentrantLock field) {
        int count = 1;
        int countMax = 2;
        do {
            boolean isLocked = field.tryLock();
            if (isLocked) {
                return true;
            }
            if (count < countMax) {
                waitFor(500);
            }
            count++;
        } while (count <= countMax);
        return false;
    }

    private boolean canMoveBy(int coordinate, int coordinateMax) {
        return coordinate >= 0 && coordinate < coordinateMax;
    }

    private void waitFor(int t) {
        try {
            wait(t);
        } catch (InterruptedException ignored) {
        }
    }
}
