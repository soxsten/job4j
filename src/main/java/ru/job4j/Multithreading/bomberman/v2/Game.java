package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private List<Moveable2> gameObjects = new ArrayList<>();
    private MoveApi moveApi;
    ExecutorService pool;

    public Game(int sizeX, int sizeY, int numberOfImpassableAreas, Moveable2 hero, Moveable2... monsters) {
        gameObjects.add(hero);
        Collections.addAll(gameObjects, monsters);
        this.moveApi = generateImpassableAreas(sizeX, sizeY, numberOfImpassableAreas);
    }

    private MoveApi generateImpassableAreas(int x, int y, int numberOfImpassableAreas) {
        Random randomX = new Random();
        Random randomY = new Random();
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < numberOfImpassableAreas; i++) {
            coordinates.add(new Coordinates(randomX.nextInt(x - 1), randomY.nextInt(y - 1)));
        }
        return new MoveApi(x, y, coordinates);
    }

    public void start() {
        pool = Executors.newFixedThreadPool(gameObjects.size());
        Moveable2 hero = gameObjects.get(0);
        HeroMover heroMover = new HeroMover(hero);
        pool.submit(heroMover);
        if (gameObjects.size() > 1) {
            for (int i = 1; i < gameObjects.size(); i++) {
                Moveable2 moster = gameObjects.get(i);
                pool.submit(new MonsterMover(moster));
            }
        }
    }

    public void stop() {
        pool.shutdown();
    }

    public static void main(String[] args) {
        Game game = new Game(4, 4, 5, new Hero2(1, 500, 1000));
        game.start();
        game.stop();
    }

    private class MonsterMover extends Thread {

        private Moveable2 monster;

        public MonsterMover(Moveable2 monster) {
            this.monster = monster;
        }

        @Override
        public void run() {
            while (true) {
                Board2.Field lock = moveApi.getRandomLock();
                lock.getLock().lock();
                monster.setNewPosition(lock);
                try {
                    moveApi.moveSomewhere(monster);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class HeroMover extends Thread {

        private Moveable2 hero;

        public HeroMover(Moveable2 hero) {
            this.hero = hero;
        }

        @Override
        public void run() {
            Board2.Field pos = moveApi.getRandomLock();
            pos.getLock().lock();
            hero.setNewPosition(pos);
            while (true) {
                try {
                    moveApi.moveSomewhere(hero);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
