package ru.job4j.Multithreading.bomberman;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

import static ru.job4j.Multithreading.bomberman.Directions.*;

public class BomberMan {
    private final ReentrantLock[][] board;
    private HeroPosition heroPosition;
    private int maxX;
    private int maxY;

    public BomberMan(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.board = setup(maxX, maxY);
        heroPosition = new HeroPosition(0, 0, maxX, maxY);
    }

    public void start() {

    }

    private Directions chooseDirection() {
        ArrayList<Directions> possibilities = new ArrayList<>();
        if (heroPosition.hasUp()) {
            possibilities.add(MOVE_UP);
        }
        if (heroPosition.hasUpAndLeft()) {
            possibilities.add(MOVE_UP_AND_LEFT);
        }
        if (heroPosition.hasUpAndRight()) {
            possibilities.add(MOVE_UP_AND_RIGHT);
        }
        if (heroPosition.hasDown()) {
            possibilities.add(MOVE_DOWN);
        }
        if (heroPosition.hasDownAndLeft()) {
            possibilities.add(MOVE_DOWN_AND_LEFT);
        }
        if (heroPosition.hasDownAndRight()) {
            possibilities.add(MOVE_DOWN_AND_RIGHT);
        }
        if (heroPosition.hasLeft()) {
            possibilities.add(MOVE_LEFT);
        }
        if (heroPosition.hasRight()) {
            possibilities.add(MOVE_RIGHT);
        }
        int chooseDirection = new Random(possibilities.size() - 1).nextInt();
        return possibilities.get(chooseDirection);
    }

    private void moveBy(Directions direction) {
        switch (direction) {
            case MOVE_UP: {
                heroPosition.moveUp();
                break;
            }
            case MOVE_UP_AND_LEFT: {
                heroPosition.hasUpAndLeft();
                break;
            }
            case MOVE_UP_AND_RIGHT: {
                heroPosition.moveUpAndRight();
            }
            case MOVE_DOWN: {
                heroPosition.moveDown();
                break;
            }
            case MOVE_DOWN_AND_LEFT: {
                heroPosition.moveDownAndLeft();
                break;
            }
            case MOVE_DOWN_AND_RIGHT: {
                heroPosition.moveDownAndRight();
                break;
            }
            case MOVE_LEFT: {
                heroPosition.moveLeft();
                break;
            }
            case MOVE_RIGHT: {
                heroPosition.moveRight();
                break;
            }
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
}
