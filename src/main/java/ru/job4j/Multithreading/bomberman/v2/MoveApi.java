package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;
import ru.job4j.Multithreading.bomberman.Directions;

import java.util.List;

public class MoveApi {
    private final Board2 board;

    public MoveApi(int maxX, int maxY, Hero2 hero) {
        this.board = new Board2(maxX, maxY, hero);
    }

    public MoveApi(int maxX, int maxY, Moveable2 hero, List<Coordinates> coordinates) {
        this.board = new Board2(maxX, maxY, hero);
        for (Coordinates coordinate : coordinates) {
            lockFor(coordinate);
        }
    }

    public boolean moveUp(Moveable2 moveable) throws InterruptedException {
        return board.move(moveable, Directions.UP);
    }

    public boolean moveDown(Moveable2 moveable) throws InterruptedException {
        return board.move(moveable, Directions.DOWN);
    }

    public boolean moveLeft(Moveable2 moveable) throws InterruptedException {
        return board.move(moveable, Directions.LEFT);
    }

    public boolean moveRight(Moveable2 moveable) throws InterruptedException {
        return board.move(moveable, Directions.RIGHT);
    }

    public void moveSomewhere(Moveable2 moveable) throws InterruptedException {
        board.moveSomewhere(moveable);
    }

    public Board2.Field getRandomLock(Moveable2 moveable2) {
        return board.getLock(moveable2);
    }

    private void lockFor(Coordinates coordinates) {
        board.lockFor(coordinates);
    }
}
