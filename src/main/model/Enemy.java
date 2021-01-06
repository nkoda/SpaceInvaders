package model;

import ui.Game;

import java.awt.*;
import java.util.Random;

// This class represents the enemy ships MainPlayer is attacking
public class Enemy extends Player {
    private static final Integer HEALTH = 1;
    public static final Color COLOR = new Color(180, 75, 76);
    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 9;
    private Integer mainPlayerPosX;
    private Integer mainPlayerPosY;

    public Enemy(int mainPlayerPosX, int mainPlayerPosY) {
        this.mainPlayerPosX = mainPlayerPosX;
        this.mainPlayerPosY = mainPlayerPosY;
        this.initialize();
        health = HEALTH;
    }

    //EFFECTS: initializes the enemies to move on their own
    private void initialize() {
        this.spawnLocation();
    }

    @Override
    protected void spawnLocation() {
        if (this.getRandomBoolean()) {
            setPositionX(getRandomInt(Game.WIDTH));
        } else {
            setPositionX(0);
        }
        if (this.getRandomBoolean()) {
            setPositionY(getRandomInt(Game.WIDTH));
        } else {
            setPositionY(0);
        }
    }

    //returns true if enemy is ready to fire
    public Boolean isReadyToShoot() {
        return (this.getRandomInt(30) == 1);
    }

    private int getRandomInt(int uppperBound) {
        Random random = new Random();
        return random.nextInt(uppperBound);
    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    //EFFECTS: this moves the enemy towards the player
    @Override
    public void move() {
        if (!this.isDead()) {
            Integer directionX;
            Integer directionY;
            Random rand = new Random();
            Random rand2 = new Random();
            if ((mainPlayerPosX - positionX) != 0 && (mainPlayerPosY - positionY) != 0) {
                directionX = (mainPlayerPosX - positionX) / Math.abs(mainPlayerPosX - positionX);
                directionY = (mainPlayerPosY - positionY) / Math.abs(mainPlayerPosY - positionY);
            } else {
                directionX = 1;
                directionY = 1;
            }
            //todo change this
            //this is to introduce variations to the directions so enemies dont clump up
            if (rand.nextInt(100) == 1) {
                directionX = rand.nextInt(8) - 4;
                directionY = rand2.nextInt(8) - 4;
            }
            this.positionX += directionX;
            this.positionY += directionY;
        }
    }

    public void update(int posX, int posY) {
        this.mainPlayerPosX = posX;
        this.mainPlayerPosY = posY;
        this.move();
    }
}
