package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Writer;
import ui.Game;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest extends PlayerTest {

    @BeforeEach
    void beforeEach() {
        testCase1 = new Enemy(1,2);
        testCase2 = new Enemy(1,2);
        testCase3 = new Enemy(1,2);
        //testCase4 = new EnemyTest();

    }

    @Test
    void spawnLocationTest() {
        testCase1.spawnLocation();
        assertTrue(testCase1.positionX > - 5 && testCase1.positionX < Game.WIDTH + 5);
        assertTrue(testCase1.positionY > - 5 && testCase1.positionY < Game.HEIGHT + 5);
    }

    @Test
    void HealthTest() {
        assertEquals(5,testCase1.getHealth());
        testCase1.setHealth(200);
        testCase2.setHealth(15);
        assertEquals(200,testCase1.getHealth());
        assertEquals(15,testCase2.getHealth());

        testCase1.setHealth(0);
        testCase3.setHealth(-1);
        assertTrue(testCase1.isDead());
        assertFalse(testCase2.isDead());
        assertTrue(testCase3.isDead());

        testCase3.setHealth(10);
        testCase3.damaged(5);
        testCase3.damaged(2);
        assertEquals(3,testCase3.getHealth());
    }

    @Test
    void attackTest() {
        assertEquals(1, testCase1.getAttack());
        testCase1.setAttack(12);
        assertEquals(12,testCase1.getAttack());
    }

    @Test
    void PositionTest() {
        assertEquals(0, testCase1.getPositionX());
        assertEquals(0, testCase1.getPositionY());

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

//    @Override
//    public void move() {
//
//        Integer directionX;
//        Integer directionY;
//        if ((mainPlayerPosX - positionX) != 0 && (mainPlayerPosY - positionY) != 0) {
//            directionX = (mainPlayerPosX - positionX) / Math.abs(mainPlayerPosX - positionX);
//            directionY = (mainPlayerPosY - positionY) / Math.abs(mainPlayerPosY - positionY);
//        } else {
//            directionX = 0;
//            directionY = 0;
//        }
//        this.positionX += directionX * DX;
//        this.positionY += directionY * DY;
//    }
    @Test
    void moveTest() {
        Integer initPosY = testCase1.positionY;
        Integer initPosX = testCase1.positionX;

        testCase1.move();

        assertFalse(testCase1.positionY.equals(initPosY));
        assertFalse(testCase1.positionX.equals(initPosX));

    }

    @Test
    void atPlayerLocationMoveTest() {
        testCase1.setPositionY(2);
        testCase1.setPositionX(1);

        Integer initPosY = testCase1.positionY;
        Integer initPosX = testCase1.positionX;

        testCase1.move();

        assertTrue(testCase1.positionX.equals(initPosX));
        assertTrue(testCase1.positionY.equals(initPosY));
    }


}
