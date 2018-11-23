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
                board[y][x] = new Field(new ReentrantLock(), new Coordinates(x, y), Type.NONE);
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
        field.setHoldedBy(Type.BLOCKED_AREA);
        return field.getLock().tryLock();
    }

    public Field getLock() {
        Random x = new Random();
        Random y = new Random();
        while (true) {
            Field field = board[y.nextInt(maxY)][x.nextInt(maxX)];
            if (!field.getLock().isLocked()) {
                System.out.println("Начальная позиция");
                printBoard();
                return field;
            }
        }
    }

    private boolean moveByDirection(Moveable2 moveable, Field dist) throws InterruptedException {
        if (dist.getLock().tryLock(moveable.getTryLockTime(), TimeUnit.NANOSECONDS)) {
            moveable.getCurrentPosition().getLock().unlock();
            moveable.setNewPosition(dist);
            printBoard();
            System.out.println("Получилось");
            System.out.println();
            Thread.sleep(moveable.getWaitingTime());
            return true;
        }
        return false;
    }

    public boolean moveSomewhere(Moveable2 moveable) throws InterruptedException {
        List<Field> directions = getPossibleDirectionsFor(moveable);
        if (directions.isEmpty()) {
            printBoard();
            System.out.println("Не могу двигаться");
            return false;
        }
        while (true) {
            Random random = new Random();
            int bound = directions.size();
            int index = bound == 0 ? 0 : random.nextInt(bound);
            Field field1 = directions.get(index);
            ReentrantLock lock = field1.getLock();
            if (lock.tryLock(moveable.getTryLockTime(), TimeUnit.NANOSECONDS)) {
                Field currentPosition = moveable.getCurrentPosition();
                currentPosition.setHoldedBy(Type.NONE);
                currentPosition.getLock().unlock();
                Coordinates oldPos = moveable.getCurrentPosition().getCoordinates();
                moveable.setNewPosition(field1);
                field1.setHoldedBy(moveable.getType());
                Coordinates newPos = field1.getCoordinates();
                printBoard();
                printDirection(oldPos, newPos);
                Thread.sleep(moveable.getWaitingTime());
                return true;
            }
        }
    }

    private List<Field> getPossibleDirectionsFor(Moveable2 moveable2) throws InterruptedException {
        Coordinates coordinates = moveable2.getCurrentPosition().getCoordinates();
        List<Field> directions = new ArrayList<>();
        int newX;
        int oldY;
        newX = coordinates.getX() + moveable2.getSpeed();
        oldY = coordinates.getY();
        if (canMoveBy(newX, maxX)) {
            Field field = getField(newX, oldY);
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        newX = coordinates.getX() - moveable2.getSpeed();
        oldY = coordinates.getY();
        if (canMoveBy(newX, maxX)) {
            Field field = getField(newX, oldY);
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        int newY;
        int oldX;
        newY = coordinates.getY() + moveable2.getSpeed();
        oldX = coordinates.getX();
        if (canMoveBy(newY, maxY)) {
            Field field = getField(oldX, newY);
            if (tryLockFor(field, moveable2)) {
                directions.add(field);
            }
        }
        newY = coordinates.getY() - moveable2.getSpeed();
        oldX = coordinates.getX();
        if (canMoveBy(newY, maxY)) {
            Field field = getField(oldX, newY);
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
        String s = "Пошел в: ";
        if (newPos.getX() > oldPos.getX()) {
            System.out.println(s + "ВПРАВО");
        }
        if (newPos.getX() < oldPos.getX()) {
            System.out.println(s + "ВЛЕВО");
        }
        if (newPos.getY() < oldPos.getY()) {
            System.out.println(s + "ВВЕРХ");
        }
        if (newPos.getY() > oldPos.getY()) {
            System.out.println(s + "ВНИЗ");
        }
    }

    private void printBoard() {
        System.out.print(" ");
        for (int i = 0; i < maxX; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        for (int y = 0; y < maxY; y++) {
            System.out.print(y);
            for (int x = 0; x < maxX; x++) {
                Type holdedBy = board[y][x].getHoldedBy();
                switch (holdedBy) {
                    case HERO: {
                        print("H");
                        break;
                    }
                    case MONSTER: {
                        print("M");
                        break;
                    }
                    case BLOCKED_AREA: {
                        print("x");
                        break;
                    }
                    case NONE: {
                        print(" ");
                        break;
                    }
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
        private Type holdedBy;

        public Field(ReentrantLock lock, Coordinates coordinates, Type type) {
            this.lock = lock;
            this.coordinates = coordinates;
            this.holdedBy = type;
        }

        public ReentrantLock getLock() {
            return lock;
        }

        public Coordinates getCoordinates() {
            return coordinates;
        }

        public boolean isBlockedArea() {
            return isBlockedArea;
        }

        public Type getHoldedBy() {
            return holdedBy;
        }

        public void setHoldedBy(Type holdedBy) {
            this.holdedBy = holdedBy;
        }
    }
}
