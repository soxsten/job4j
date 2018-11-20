package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game {

    private List<Moveable2> gameObjects = new ArrayList<>();
    private MoveApi moveApi;
    private volatile boolean isRun = true;

    public Game(int sizeX, int sizeY, int numberOfImpassableAreas, Moveable2 hero, Moveable2... monsters) {
        gameObjects.add(hero);
        Collections.addAll(gameObjects, monsters);
        this.moveApi = generateImpassableAreas(sizeX, sizeY, numberOfImpassableAreas);
    }

    private MoveApi generateImpassableAreas(int x, int y, int numberOfImpassableAreas) {
        Random randomX = new Random(x - 1);
        Random randomY = new Random(y - 1);
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < numberOfImpassableAreas; i++) {
            coordinates.add(new Coordinates(randomX.nextInt(), randomY.nextInt()));
        }
        return new MoveApi(x, y, coordinates);
    }

    public void start() {
        Moveable2 hero = gameObjects.get(0);
        new HeroMover(hero).run();
        for (int i = 1; i < gameObjects.size(); i++) {
            Moveable2 moster = gameObjects.get(i);
            new MonsterMover(moster).run();
        }
    }

    public void stop() {
        isRun = false;
    }

    private class MonsterMover implements Runnable {
        private Moveable2 monster;

        public MonsterMover(Moveable2 monster) {
            this.monster = monster;
        }

        @Override
        public void run() {
            while (isRun) {
                try {
                    moveApi.moveSomewhere(monster);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private class HeroMover implements Runnable {
        private Moveable2 hero;

        public HeroMover(Moveable2 hero) {
            this.hero = hero;
        }

        @Override
        public void run() {
            while (isRun) {
                try {
                    moveApi.moveSomewhere(hero);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
