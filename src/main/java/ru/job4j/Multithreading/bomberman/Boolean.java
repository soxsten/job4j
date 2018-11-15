package ru.job4j.Multithreading.bomberman;

public class Boolean {
    private boolean isMoved;
    private Directions direction;

    public Boolean(boolean isMoved, Directions direction) {
        this.isMoved = isMoved;
        this.direction = direction;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public Directions getDirection() {
        return direction;
    }
}
