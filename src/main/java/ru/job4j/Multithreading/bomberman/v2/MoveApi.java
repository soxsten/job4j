package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;
import ru.job4j.Multithreading.bomberman.Directions;

import java.util.List;

import static ru.job4j.Multithreading.bomberman.Directions.getRandomDirection;

public class MoveApi {
    private final Board2 board;

    public MoveApi(int maxX, int maxY) {
        this.board = new Board2(maxX, maxY);
    }

    public MoveApi(int maxX, int maxY, List<Coordinates> coordinates) {
        this.board = new Board2(maxX, maxY);
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
        board.move(moveable, getRandomDirection(Directions.class));
    }

    private void lockFor(Coordinates coordinates) {
        board.lockFor(coordinates);
    }
}
