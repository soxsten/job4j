package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;
import ru.job4j.Multithreading.bomberman.Directions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
                System.out.println("Хочу пойти ВВЕРХ");
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newY = coordinates.getY() - moveable.getSpeed();
                int oldX = coordinates.getX();
                if (canMoveBy(newY, maxY)) {
                    Field dist = getField(oldX, newY);
                    if (moveByDirection(moveable, dist)) {
                        return true;
                    }
                }
                break;
            }
            case DOWN: {
                System.out.println("Хочу пойти ВНИЗ");
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int newY = coordinates.getY() + moveable.getSpeed();
                int oldX = coordinates.getX();
                if (canMoveBy(newY, maxY)) {
                    Field dist = getField(oldX, newY);
                    if (moveByDirection(moveable, dist)) {
                        return true;
                    }
                }
                break;
            }
            case LEFT: {
                System.out.println("Хочу пойти НАЛЕВО");
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int oldY = coordinates.getY();
                int newX = coordinates.getX() - moveable.getSpeed();
                if (canMoveBy(newX, maxX)) {
                    Field dist = getField(newX, oldY);
                    if (moveByDirection(moveable, dist)) {
                        return true;
                    }
                }
                break;
            }
            case RIGHT: {
                System.out.println("Хочу пойти ВПРАВО");
                Coordinates coordinates = moveable.getCurrentPosition().coordinates;
                int oldY = coordinates.getY();
                int newX = coordinates.getX() + moveable.getSpeed();
                if (canMoveBy(newX, maxX)) {
                    Field dist = getField(newX, oldY);
                    if (moveByDirection(moveable, dist)) {
                        return true;
                    }
                }
                break;
            }
        }
        return moveSomewhere(moveable);
    }

    public boolean lockFor(Coordinates coordinates) {
        Field field = board[coordinates.getY()][coordinates.getX()];
        field.blockArea();
        return field.getLock().tryLock();
    }

    public Field getLock() {
        Random x = new Random();
        Random y = new Random();
        while (true) {
            Field field = board[y.nextInt(maxY - 1)][x.nextInt(maxX - 1)];
            if (!field.getLock().isLocked()) {
                System.out.println("Начальная позиция");
                printBoard(field.getCoordinates());
                return field;
            }
        }
    }

    private boolean moveByDirection(Moveable2 moveable, Field dist) throws InterruptedException {
        if (dist.getLock().tryLock(moveable.getTryLockTime(), TimeUnit.NANOSECONDS)) {
            moveable.getCurrentPosition().getLock().unlock();
            moveable.setNewPosition(dist);
            printBoard(moveable.getCurrentPosition().getCoordinates());
            System.out.println("Получилось");
            System.out.println();
            return true;
        }
        return false;
    }

    public boolean moveSomewhere(Moveable2 moveable) throws InterruptedException {
        List<Field> directions = getPossibleDirectionsFor(moveable);
        if (directions.isEmpty()) {
            printBoard(moveable.getCurrentPosition().getCoordinates());
            System.out.println("Не могу двигаться");
            return false;
        }
        while (true) {
            Random random = new Random();
            int i = random.nextInt(directions.size() - 1);
            Field field1 = directions.get(i);
            ReentrantLock lock = field1.getLock();
            if (lock.tryLock(moveable.getTryLockTime(), TimeUnit.NANOSECONDS)) {
                moveable.getCurrentPosition().getLock().unlock();
                Coordinates oldPos = moveable.getCurrentPosition().getCoordinates();
                moveable.setNewPosition(field1);
                Coordinates newPos = field1.getCoordinates();
                printBoard(moveable.getCurrentPosition().getCoordinates());
                printDirection(oldPos, newPos);
                return true;
            }
        }
    }

    private List<Field> getPossibleDirectionsFor(Moveable2 moveable2) throws InterruptedException {
        Coordinates coordinates = moveable2.getCurrentPosition().getCoordinates();
        List<Field> directions = new ArrayList<>();
        int incX = coordinates.getX() + moveable2.getSpeed();
        if (canMoveBy(incX, maxX)) {
            Field field = getField(incX, coordinates.getY());
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        int decX = coordinates.getX() - moveable2.getSpeed();
        if (canMoveBy(decX, maxX)) {
            Field field = getField(decX, coordinates.getY());
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        int incY = coordinates.getY() + moveable2.getSpeed();
        if (canMoveBy(incY, maxY)) {
            Field field = getField(coordinates.getX(), incY);
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        int decY = coordinates.getY() - moveable2.getSpeed();
        if (canMoveBy(decY, maxY)) {
            Field field = getField(coordinates.getX(), decY);
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        return directions;
    }

    private boolean tryLockFor(Field field, Moveable2 moveable2) throws InterruptedException {
        return field.getLock().tryLock(moveable2.getTryLockTime(), TimeUnit.NANOSECONDS);
    }

    private boolean canMoveBy(int dist, int limit) {
        return dist >= 0 && dist < limit;
    }

    private Field getField(int x, int y) {
        return board[y][x];
    }

    private void printDirection(Coordinates oldPos, Coordinates newPos) {
        String s = "В итоге пошел в: ";
        if (newPos.getX() > oldPos.getX()) {
            System.out.println(s + Directions.RIGHT);
        }
        if (newPos.getX() < oldPos.getX()) {
            System.out.println(s + Directions.LEFT);
        }
        if (newPos.getY() > oldPos.getY()) {
            System.out.println(s + Directions.UP);
        }
        if (newPos.getY() < oldPos.getY()) {
            System.out.println(s + Directions.DOWN);
        }
    }

    private void printBoard(Coordinates coordinates) {
        Field field = board[coordinates.getY()][coordinates.getX()];
        Coordinates pos = field.getCoordinates();
        System.out.print(" ");
        for (int i = 0; i < maxX; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int y = 0; y < maxY; y++) {
            System.out.print(y);
            for (int x = 0; x < maxX; x++) {
                if (pos.getX() == x && pos.getY() == y) {
                    print("H");
                    continue;
                }
                if (board[y][x].isBlockedArea()) {
                    print("x");
                } else {
                    print(" ");
                }
            }
            System.out.println();
        }
    }

    private void print(String symbol) {
        System.out.print("[" + symbol + "]");
    }

    class Field {
        private ReentrantLock lock;
        private volatile boolean isBlockedArea = false;
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

        public void blockArea() {
            isBlockedArea = true;
        }

        public boolean isBlockedArea() {
            return isBlockedArea;
        }
    }
}
