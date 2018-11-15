package ru.job4j.Multithreading.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board {
    private int maxX;
    private int maxY;
    private ReentrantLock[][] board;

    public Board(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.board = setup(maxX, maxY);
    }

    private ReentrantLock[][] setup(int maxX, int maxY) {
        ReentrantLock[][] locks = new ReentrantLock[maxX][maxY];
        for (int y = 0; y < this.maxY; y++) {
            for (int x = 0; x < this.maxX; x++) {
                locks[y][x] = new ReentrantLock();
            }
        }
        return locks;
    }

    public boolean move(ReentrantLock source, ReentrantLock dist) throws InterruptedException {
        if (source == null) {
            return false;
        }
        if (dist.tryLock(500, TimeUnit.NANOSECONDS)) {
            source.unlock();
            return true;
        }
        List<ReentrantLock> directions = getPossibleDirectionsFor(source);
        while (true) {
            for (ReentrantLock direction : directions) {
                if (direction.tryLock(500, TimeUnit.NANOSECONDS)) {
                    source.unlock();
                    return true;
                }
            }
        }
    }

    public ReentrantLock getField(int x, int y) {
        return board[x][y];
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public boolean canMoveBy(int dist, int limit) {
        return dist >= 0 && dist < limit;
    }

    private List<ReentrantLock> getPossibleDirectionsFor(ReentrantLock source) {
        List<ReentrantLock> directions = new ArrayList<>();
        Coordinates coordinates = getCoordinatesFor(source);
        if (canMoveBy(coordinates.getY() + 1, getMaxY())) {
            directions.add(board[coordinates.getY() + 1][coordinates.getX()]);
        }
        if (canMoveBy(coordinates.getY() - 1, getMaxY())) {
            directions.add(board[coordinates.getY() - 1][coordinates.getX()]);
        }
        if (canMoveBy(coordinates.getY() - 1, getMaxY())) {
            directions.add(board[coordinates.getY()][coordinates.getX() - 1]);
        }
        if (canMoveBy(coordinates.getX() + 1, getMaxX())) {
            directions.add(board[coordinates.getY() + 1][coordinates.getX() + 1]);
        }
        return directions;
    }

    private Coordinates getCoordinatesFor(ReentrantLock source) {
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                ReentrantLock field = board[y][x];
                if (source.equals(field)) {
                    return new Coordinates(x, y);
                }
            }
        }
        return null;
    }
}

class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
