package model;

import java.awt.*;
import java.util.Random;
import ui.Game;

// This class represents the enemy ships MainPlayer is attacking
public class Enemy extends Player {
    private static final Integer ATTACK = 1;

    public static final Color COLOR = new Color(180, 75, 76);
    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 9;



    private Integer mainPlayerPosX;
    private Integer mainPlayerPosY;

    public Enemy(int mainPlayerPosX, int mainPlayerPosY) {
        attack = ATTACK;
        this.mainPlayerPosX = mainPlayerPosX;
        this.mainPlayerPosY = mainPlayerPosY;
    }

    //todo
    // make spawn of enemies at random location outside game screen
    @Override
    protected void spawnLocation() {
        Random randX = new Random();
        Random randY = new Random();

        setPositionX(Game.WIDTH + randX.nextInt(5));
        setPositionY(Game.HEIGHT + randY.nextInt(5));
    }

    //todo implement firing of bullets enemy
//    //EFFECTS: Fires a bullet
//    private void fireBullet() {
//        new Bullet(getPositionX(), getPositionY());
//    }

    //EFFECTS: this moves the enemy towards the player
    @Override
    public void move() {

        Integer directionX;
        Integer directionY;
        if ((mainPlayerPosX - positionX) != 0 && (mainPlayerPosY - positionY) != 0) {
            directionX = (mainPlayerPosX - positionX) / Math.abs(mainPlayerPosX - positionX);
            directionY = (mainPlayerPosY - positionY) / Math.abs(mainPlayerPosY - positionY);
        } else {
            directionX = 0;
            directionY = 0;
        }
        this.positionX += directionX * DX;
        this.positionY += directionY * DY;
    }



}
