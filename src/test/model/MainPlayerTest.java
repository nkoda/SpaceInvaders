package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import persistence.Writer;
import ui.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPlayerTest extends PlayerTest {
//todo

    @BeforeEach
    void beforeEach() {
        testCase1 = new MainPlayer();
        testCase2 = new MainPlayer();
        testCase3 = new MainPlayer();
    }

    @Test
    void HealthTest() {
        assertEquals(20,testCase1.getHealth());
        testCase1.setHealth(200);
        testCase2.setHealth(15);
        assertEquals(200,testCase1.getHealth());
        assertEquals(15,testCase2.getHealth());

        testCase1.setHealth(0);
        testCase3.setHealth(-1);
        assertEquals(true, testCase1.isDead());
        assertEquals(false,testCase2.isDead());
        assertEquals(true, testCase3.isDead());

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
    void positionTest() {
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
    void moveTest() {
        assertEquals(Game.WIDTH / 2, testCase1.positionX);
        assertEquals(Game.HEIGHT / 2, testCase1.positionY);
        testCase1.moveRight();
        testCase1.move();
        assertEquals(Game.WIDTH / 2 + (testCase1.directionX * testCase1.DX), testCase1.positionX);
        assertEquals(Game.HEIGHT / 2, testCase1.positionY);
        testCase1.moveDown();
        testCase1.move();
        assertEquals(Game.HEIGHT / 2 + (testCase1.directionY * testCase1.DY), testCase1.positionY);

    }

    @Test
    void handleBoundaryTest() {
        testCase1.setPositionX(0);
        testCase1.setPositionY(0);
        testCase1.moveUp();
        testCase1.moveLeft();
        testCase1.move();

        assertEquals(0, testCase1.positionX);
        assertEquals(0, testCase1.positionY);

        testCase1.setPositionX(Game.WIDTH);
        testCase1.setPositionY(Game.HEIGHT);
        testCase1.moveDown();
        testCase1.moveRight();
        testCase1.move();

        assertEquals(Game.WIDTH, testCase1.positionX);
        assertEquals(Game.HEIGHT, testCase1.positionY);


    }

    @Test
    void saveTest() {
        String GAME_FILE = "./data/saveTestPlayer.txt";
        try {
            Writer writer = new Writer(new File(GAME_FILE));

            writer.write(testCase1);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + GAME_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
        File tempFile = new File(GAME_FILE);
        boolean exists = tempFile.exists();
        assertTrue(exists);
    }



}
