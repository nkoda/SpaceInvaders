package model;

import persistence.*;
import java.io.PrintWriter;
import ui.Game;

//A model for any Player classes
public abstract class Player implements Saveable {
    protected Integer health = 5;
    protected Integer attack = 5;

    protected Integer positionX = 0;
    protected Integer positionY = 0;
    protected Integer directionX = 0;
    protected Integer directionY = 0;

    protected static final int LEFT = -1;
    protected static final int RIGHT = 1;
    protected static final int UP = -1;
    protected static final int DOWN = 1;

    protected static final int DX = 2;
    protected static final int DY = 2;

    //EFFECTS: spawns player at location
    protected abstract void spawnLocation();

    // modifies: this
    // effects: moves the
    public abstract void move();

    //REQUIRES: an integer greater than 0
    //EFFECTS: remove given amount of health from the players health
    public void damaged(Integer amount) {
        this.health -= amount;
    }

    //EFFECTS: returns true if the player is dead, false if alive
    public boolean isDead() {
        boolean returnValue = false;

        if (health <= 0) {
            returnValue = true;
        }
        return returnValue;
    }

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.print(health);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(attack);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(positionX);
        printWriter.print(Reader.DELIMITER);
        printWriter.print(positionY);
        printWriter.print('\n');
    }

    //REQUIRES: an integer greater than 0
    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    // modifies: this
    // effects: set player direction left
    public void moveLeft() {
        directionX  = LEFT;
    }

    // modifies: this
    // effects: set player direction right
    public void moveRight() {
        directionX = RIGHT;
    }

    // modifies: this
    // effects: set player direction up
    public void moveUp() {
        directionY = UP;
    }

    // modifies: this
    // effects: set player direction down
    public void moveDown() {
        directionY = DOWN;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getHealth() {
        return health;
    }

    public Integer getPositionX() {
        return positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

}
