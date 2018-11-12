package ru.job4j.Multithreading;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect));
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(
                event -> thread.interrupt()
        );
    }
}

class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        double speed = 5;
        double speedX = speed;
        double speedY = speed;
        while (!Thread.currentThread().isInterrupted()) {
            double currentX = this.rect.getX();
            double newPosX = currentX + speed;
            if (shouldChangeDirectionFor(newPosX)) {
                speedX = changeDirectionFor(speedX);
            }
            this.rect.setX(currentX + speedX);

            double currentY = this.rect.getY();
            double newPosY = currentY + speed;
            if (shouldChangeDirectionFor(newPosY)) {
                speedY = changeDirectionFor(speedY);
            }
            this.rect.setY(currentY + speedY);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private boolean shouldChangeDirectionFor(double pos) {
        return pos >= 300 || pos <= 0;
    }

    private double changeDirectionFor(double dir) {
        return dir * -1;
    }
}
