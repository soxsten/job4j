package ru.job4j.Multithreading.bomberman.v2;

import ru.job4j.Multithreading.bomberman.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static ru.job4j.Multithreading.bomberman.v2.Type.HERO;

public class Game {
    private List<Moveable2> gameObjects = new ArrayList<>();
    private MoveApi moveApi;
    private ExecutorService pool;

    public Game(int sizeX, int sizeY, int numberOfImpassableAreas, Moveable2 hero, Moveable2... monsters) {
        gameObjects.add(hero);
        Collections.addAll(gameObjects, monsters);
        this.moveApi = generateImpassableAreas(sizeX, sizeY, numberOfImpassableAreas, hero);
    }

    public static void main(String[] args) {
        Hero2 hero = new Hero2(0, 500, 1000, HERO);
        Monster monster = new Monster(1, 5000, 1000, Type.MONSTER);
        Game game = new Game(2, 2, 0, hero, monster);
        game.start();
        game.stop();
    }

    private MoveApi generateImpassableAreas(int x, int y, int numberOfImpassableAreas, Moveable2 hero) {
        Random randomX = new Random();
        Random randomY = new Random();
        ArrayList<Coordinates> coordinates = new ArrayList<>();
        for (int i = 0; i < numberOfImpassableAreas; i++) {
            coordinates.add(new Coordinates(randomX.nextInt(x), randomY.nextInt(y)));
        }
        return new MoveApi(x, y, hero, coordinates);
    }

    public void stop() {
        pool.shutdown();
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
        while (true) {
            if (!hero.isAlive()) {
                stop();
                break;
            }
        }
    }

    private class MonsterMover extends Thread {
        private Moveable2 monster;

        public MonsterMover(Moveable2 monster) {
            this.monster = monster;
        }

        @Override
        public void run() {
            while (!pool.isShutdown()) {
                boolean isLocked;
                Board2.Field pos;
                do {
                    pos = moveApi.getRandomLock(monster);
                    isLocked = pos.getLock().tryLock();
                } while (!isLocked);
                pos.setHoldedBy(monster.getType());
                monster.setNewPosition(pos);
                while (!pool.isShutdown()) {
                    try {
                        moveApi.moveSomewhere(monster);
                    } catch (InterruptedException e) {
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
            boolean isLocked;
            Board2.Field pos;
            do {
                pos = moveApi.getRandomLock(hero);
                isLocked = pos.getLock().tryLock();
            } while (!isLocked);
            pos.setHoldedBy(hero.getType());
            hero.setNewPosition(pos);
            while (!pool.isShutdown()) {
                try {
                    moveApi.moveSomewhere(hero);
                } catch (InterruptedException e) {
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
