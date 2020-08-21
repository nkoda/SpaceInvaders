package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import persistence.Writer;
import ui.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class PlayerTest {
    Player testCase1;
    Player testCase2;
    Player testCase3;

    @Test
    abstract void HealthTest();

    @Test
    abstract void attackTest();

    @Test
    void PositionTest() {
        assertEquals(Game.WIDTH / 2, testCase1.getPositionX());
        assertEquals(Game.HEIGHT / 2, testCase1.getPositionY());

        testCase1.setPositionX(140);
        testCase1.setPositionY(23);
        assertEquals(140, testCase1.getPositionX());assertEquals(23, testCase1.getPositionY());

        testCase1.setPositionX(1223);
        testCase1.setPositionY(232);
        testCase1.setPositionX(1);
        testCase1.setPositionY(2);
        assertEquals(1, testCase1.getPositionX());
        assertEquals(2, testCase1.getPositionY());
    }

    @Test
    void moveLeftTest() {
        testCase1.moveLeft();
        assertEquals(Player.LEFT, testCase1.directionX);
    }

    @Test
    void moveLeftRight() {
        testCase1.moveRight();
        assertEquals(Player.RIGHT, testCase1.directionX);
    }

    @Test
    void moveLeftUp() {
        testCase1.moveUp();
        assertEquals(Player.UP, testCase1.directionY);
    }

    @Test
    void moveLeftDown() {
        testCase1.moveDown();
        assertEquals(Player.DOWN, testCase1.directionY);
    }


}
