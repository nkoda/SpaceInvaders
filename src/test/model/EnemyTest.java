package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest extends PlayerTest {

    @BeforeEach
    void beforeEach() {
        testCase1 = new Enemy(1,2);
        testCase2 = new Enemy(1,2);
        testCase3 = new Enemy(1,2);
    }

    @Test
    void spawnLocationTest() {
        testCase1.spawnLocation();
        assertTrue(testCase1.positionX > - 5 && testCase1.positionX < Game.WIDTH + 5);
        assertTrue(testCase1.positionY > - 5 && testCase1.positionY < Game.HEIGHT + 5);
    }

    @Test
    void HealthTest() {
        assertEquals(1,testCase1.getHealth());
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
        assertEquals(5, testCase1.getAttack());
        testCase1.setAttack(12);
        assertEquals(12,testCase1.getAttack());
    }

    @Test
    void PositionTest() {
        testCase1.setPositionX(140);
        testCase1.setPositionY(23);
        assertEquals(140, testCase1.getPositionX());
        assertEquals(23, testCase1.getPositionY());
        testCase1.setPositionX(1223);
        testCase1.setPositionY(232);
        testCase1.setPositionX(1);
        testCase1.setPositionY(2);
        assertEquals(1, testCase1.getPositionX());
        assertEquals(2, testCase1.getPositionY());
    }

    @Test
    void moveTest() {
        Integer initPosY = testCase1.positionY;
        Integer initPosX = testCase1.positionX;
        testCase1.move();
        assertFalse(testCase1.positionY.equals(initPosY));
        assertFalse(testCase1.positionX.equals(initPosX));

    }
}
