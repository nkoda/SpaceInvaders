package model;

import java.awt.Color;

/*
 * Represents a bullet.
 * Modelled after SpaceInvaders
 */
public class Bullet {

    public static final int SIZE_X = 5;
    public static final int SIZE_Y = 9;
    private int dy = 0;
    private int dx = 0;
    public static final Color COLOR = new Color(224, 202, 60);

    private int posX;
    private int posY;

    // Constructs a bullet
    // effects: missile is positioned at coordinates (x, y)
    public Bullet(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Integer getDx() {
        return dx;
    }

    public Integer getDy() {
        return dy;
    }

    // Updates the missile on clock tick
    // modifies: this
    // effects: missile is moved DY units (up the screen)
    public void move() {
        posY = posY + dy;
        posX = posX + dx;
    }
}
