package ru.job4j.Multithreading.bomberman;

import static ru.job4j.Multithreading.bomberman.Directions.getRandomDirection;

public class BomberMan {
    private final Board board;
    private final Hero hero = new Hero(new Coordinates(0, 0));

    public BomberMan(int maxX, int maxY) {
        this.board = new Board(maxX, maxY);
    }

    public boolean moveUp(Moveable moveable) throws InterruptedException {
        return board.move(moveable, Directions.UP);
    }

    public boolean moveDown(Moveable moveable) throws InterruptedException {
        return board.move(moveable, Directions.DOWN);
    }

    public boolean moveLeft(Moveable moveable) throws InterruptedException {
        return board.move(moveable, Directions.LEFT);
    }

    public boolean moveRight(Moveable moveable) throws InterruptedException {
        return board.move(moveable, Directions.RIGHT);
    }

    public void moveNonStopFor(Moveable moveable) throws InterruptedException {
        while (true) {
            wait(1000);
            board.move(moveable, getRandomDirection(Directions.class));
        }
    }
}
