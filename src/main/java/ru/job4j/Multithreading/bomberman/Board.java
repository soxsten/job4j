package ru.job4j.Multithreading.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    public boolean move(Moveable moveable, Directions direction) throws InterruptedException {
        Coordinates coordinates = moveable.getCoordinates();
        ReentrantLock source = moveable.getCurrentPosition();
        switch (direction) {
            case UP: {
                int nextY = coordinates.getY() + moveable.getSpeed();
                if (canMoveBy(nextY, getMaxY())) {
                    return isMoved(moveable, source, coordinates.getX(), nextY);
                }
            }
            case DOWN: {
                int nextY = coordinates.getY() - moveable.getSpeed();
                if (canMoveBy(nextY, getMaxY())) {
                    return isMoved(moveable, source, coordinates.getX(), nextY);
                }
            }
            case LEFT: {
                int nextX = coordinates.getX() - moveable.getSpeed();
                if (canMoveBy(nextX, getMaxX())) {
                    return isMoved(moveable, source, nextX, coordinates.getY());
                }
            }
            case RIGHT: {
                int nextX = coordinates.getX() + moveable.getSpeed();
                if (canMoveBy(nextX, getMaxX())) {
                    return isMoved(moveable, source, nextX, coordinates.getY());
                }
            }
        }
        return false;
    }

    private Boolean move(ReentrantLock source, ReentrantLock dist) throws InterruptedException {
        if (dist.tryLock(500, TimeUnit.NANOSECONDS)) {
            source.unlock();
            Directions position = getPositionOf(dist, source);
            return new Boolean(true, position);
        }
        List<ReentrantLock> directions = getPossibleDirectionsFor(source);
        while (true) {
            for (ReentrantLock direction : directions) {
                if (direction.tryLock(500, TimeUnit.NANOSECONDS)) {
                    source.unlock();
                    Directions position = getPositionOf(dist, source);
                    return new Boolean(true, position);
                }
            }
        }
    }

    private Directions getPositionOf(ReentrantLock dist, ReentrantLock source) {
        Coordinates distCoordinates = Objects.requireNonNull(getCoordinatesFor(dist));
        Coordinates sourceCoordinates = Objects.requireNonNull(getCoordinatesFor(source));
        if (distCoordinates.getX() > sourceCoordinates.getX()) {
            return Directions.RIGHT;
        } else if (distCoordinates.getX() < sourceCoordinates.getX()) {
            return Directions.LEFT;
        } else if (distCoordinates.getY() > sourceCoordinates.getY()) {
            return Directions.UP;
        } else
            return Directions.DOWN;
    }

    private boolean isMoved(Moveable moveable, ReentrantLock source, int x, int y) throws InterruptedException {
        ReentrantLock dist = getField(x, y);
        Boolean whereDidGo = move(source, dist);
        moveable.move(whereDidGo.getDirection());
        return whereDidGo.isMoved();
    }

    private List<ReentrantLock> getPossibleDirectionsFor(ReentrantLock source) {
        List<ReentrantLock> directions = new ArrayList<>();
        Coordinates coordinates = Objects.requireNonNull(getCoordinatesFor(source));
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

    private ReentrantLock getField(int x, int y) {
        return board[x][y];
    }

    private int getMaxX() {
        return maxX;
    }

    private int getMaxY() {
        return maxY;
    }

    private boolean canMoveBy(int dist, int limit) {
        return dist >= 0 && dist < limit;
    }
}
