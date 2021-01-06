package model;

import ui.Game;

import java.awt.*;

//MainPlayer is a player played by the user
public class MainPlayer extends Player {
    public static final Color COLOR = new Color(44, 165, 141);
    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 8;
    private static final Integer HEALTH = 20;

    public MainPlayer() {
        health = HEALTH;
        spawnLocation();
    }

    //todo
    // -make spawn at center screen, not at set integer
    @Override
    protected void spawnLocation() {
        setPositionX(Game.WIDTH / 2);
        setPositionY(Game.HEIGHT / 2);
    }

    @Override
    public void move() {
        if (!isDead()) {
            this.positionX += directionX * DX;
            this.positionY += directionY * DY;
            handleBoundary();
        }
    }

    // Constrains mainPlayer so that it doesn't travel of sides of screen
    // modifies: this
    // effects: mainPlayer is constrained to remain within boundaries of game
    private void handleBoundary() {
        if (positionX < 0) {
            positionX = 0;
        } else if (positionX > Game.WIDTH) {
            positionX = Game.WIDTH;
        }
        if (positionY < 0) {
            positionY = 0;
        } else if (positionY > Game.HEIGHT) {
            positionY = Game.HEIGHT;
        }
    }

}
