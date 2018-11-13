package ru.job4j.Multithreading.bomberman;

final class HeroPosition {
    private int x;
    private int y;
    private int maxX;
    private int maxY;
    private int step = 1;

    HeroPosition(int x, int y, int maxX, int maxY) {
        this.x = x;
        this.y = y;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void moveDown() {
        y -= step;
    }

    void moveDownAndLeft() {
        moveDown();
        moveLeft();
    }

    void moveDownAndRight() {
        moveDown();
        moveRight();
    }

    void moveLeft() {
        x -= step;
    }

    void moveRight() {
        x += step;
    }

    void moveUp() {
        y += step;
    }

    public void moveUpAndLeft() {
        moveUp();
        moveLeft();
    }

    void moveUpAndRight() {
        moveUp();
        moveRight();
    }

    boolean hasUp() {
        return this.y + step < this.maxY;
    }

    boolean hasUpAndLeft() {
        return hasUp() && hasLeft();
    }

    boolean hasUpAndRight() {
        return hasUp() && hasRight();
    }

    boolean hasDown() {
        return this.y - step >= 0;
    }

    boolean hasDownAndLeft() {
        return hasDown() && hasLeft();
    }

    boolean hasDownAndRight() {
        return hasDown() && hasRight();
    }

    boolean hasLeft() {
        return this.x - step >= 0;
    }

    boolean hasRight() {
        return this.x + step < this.maxX;
    }
}
