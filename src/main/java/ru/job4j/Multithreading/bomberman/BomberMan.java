package ru.job4j.Multithreading.bomberman;

import static ru.job4j.Multithreading.bomberman.Directions.*;

public class BomberMan {
    private final Board board;
    private final Hero hero = new Hero(new Coordinates(0, 0));

    public BomberMan(int maxX, int maxY) {
        this.board = new Board(maxX, maxY);
    }

    public boolean moveUp(Moveable moveable) throws InterruptedException {
        return board.move(moveable, UP);
    }

    public boolean moveDown(Moveable moveable) throws InterruptedException {
        return board.move(moveable, DOWN);
    }

    public boolean moveLeft(Moveable moveable) throws InterruptedException {
        return board.move(moveable, LEFT);
    }

    public boolean moveRight(Moveable moveable) throws InterruptedException {
        return board.move(moveable, RIGHT);
    }

    public void moveNonStopFor(Moveable moveable) throws InterruptedException {
        while (true) {
            wait(1000);
            board.move(moveable, getRandomDirection(Directions.class));
        }
    }
}
