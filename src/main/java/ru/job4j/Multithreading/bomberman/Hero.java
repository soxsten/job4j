package ru.job4j.Multithreading.bomberman;

class Hero implements Moveable {
    private int x;
    private int y;
    private int speed = 1;

    Hero(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void down() {
        y -= speed;
    }

    @Override
    public void left() {
        x -= speed;
    }

    @Override
    public void right() {
        x += speed;
    }

    @Override
    public void up() {
        y += speed;
    }

    @Override
    public int getSpeed() {
        return this.speed;
    }
}
