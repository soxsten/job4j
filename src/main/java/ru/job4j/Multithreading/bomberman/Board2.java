package ru.job4j.Multithreading.bomberman;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Board2 {
    private int maxX;
    private int maxY;
    private Field[][] board;

    public Board2(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
        this.board = setup(maxX, maxY);
    }

    private Field[][] setup(int maxX, int maxY) {
        Field[][] board = new Field[maxX][maxY];
        for (int y = 0; y < this.maxY; y++) {
            for (int x = 0; x < this.maxX; x++) {
                board[y][x] = new Field(new ReentrantLock(), new Coordinates(x, y));
            }
        }
        return board;
    }

    public boolean move(Moveable2 moveable, Directions direction) throws InterruptedException {
        switch (direction) {
            case UP: {
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newY = coordinates.getY() + moveable.getSpeed();
                if (canMoveBy(newY, maxY)) {
                    Field dist = getField(coordinates.getX(), newY);
                    if (move(moveable, coordinates, dist)) {
                        return true;
                    }
                }
            }
            case DOWN: {
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newY = coordinates.getY() - moveable.getSpeed();
                if (canMoveBy(newY, maxY)) {
                    Field dist = getField(coordinates.getX(), newY);
                    if (move(moveable, coordinates, dist)) {
                        return true;
                    }
                }
            }
            case LEFT: {
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newX = coordinates.getX() - moveable.getSpeed();
                if (canMoveBy(newX, maxX)) {
                    Field dist = getField(newX, coordinates.getY());
                    if (move(moveable, coordinates, dist)) {
                        return true;
                    }
                }
            }
            case RIGHT: {
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newX = coordinates.getX() + moveable.getSpeed();
                if (canMoveBy(newX, maxX)) {
                    Field dist = getField(newX, coordinates.getY());
                    if (move(moveable, coordinates, dist)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean move(Moveable2 moveable, Coordinates coordinates, Field dist) throws InterruptedException {
        if (dist.getLock().tryLock(500, TimeUnit.NANOSECONDS)) {
            moveable.getCurrentPosition().getLock().unlock();
            moveable.setNewPosition(dist);
        } else {
            List<Field> directions = getPossibleDirectionsFor(coordinates, moveable.getSpeed());
            while (true) {
                for (Field field : directions) {
                    if (field.getLock().tryLock(500, TimeUnit.NANOSECONDS)) {
                        moveable.getCurrentPosition().getLock().unlock();
                        moveable.setNewPosition(field);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private List<Field> getPossibleDirectionsFor(Coordinates coordinates, int speed) {
        List<Field> directions = new ArrayList<>();
        int incX = coordinates.getX() + speed;
        if (canMoveBy(incX, maxX)) {
            Field field = getField(incX, coordinates.getY());
            directions.add(field);
        }
        int decX = coordinates.getX() - speed;
        if (canMoveBy(decX, maxX)) {
            Field field = getField(decX, coordinates.getY());
            directions.add(field);
        }
        int incY = coordinates.getY() + speed;
        if (canMoveBy(incY, maxY)) {
            Field field = getField(coordinates.getX(), incY);
            directions.add(field);
        }
        int decY = coordinates.getY() - speed;
        if (canMoveBy(decY, maxY)) {
            Field field = getField(coordinates.getX(), decY);
            directions.add(field);
        }
        return directions;
    }

    private boolean canMoveBy(int dist, int limit) {
        return dist >= 0 && dist < limit;
    }

    private Field getField(int x, int y) {
        return board[y][x];
    }

    class Field {
        private ReentrantLock lock;
        private Coordinates coordinates;

        public Field(ReentrantLock lock, Coordinates coordinates) {
            this.lock = lock;
            this.coordinates = coordinates;
        }

        public ReentrantLock getLock() {
            return lock;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }
    }
}
