package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Game;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static ui.Difficulty.*;


public class GameTest {

    Game testCase1, testCase2;
    private static final String GAME_FILE = "./data/game_files.txt";

    @BeforeEach
    void runBefore() {
        testCase1 = new Game(EASY);
        testCase2 = new Game(EASY);
    }

    @Test
    void saveGameTest() {
        File tempFile = new File(GAME_FILE);
        testCase1.saveGame();
        assertTrue(tempFile.exists());
    }

    @Test
    void testSetDifficulty() {
        testCase1.setDifficulty(EASY);
        assertEquals("Easy", testCase1.getDifficultyString());
        assertEquals(EASY,testCase1.getDifficulty());

        testCase1.setDifficulty(NORMAL);
        testCase2.setDifficulty(HARD);

        assertEquals("Normal", testCase1.getDifficultyString());
        assertEquals(NORMAL, testCase1.getDifficulty() );
        assertEquals("Hard", testCase2.getDifficultyString());
        assertEquals(HARD, testCase2.getDifficulty() );

    }

    @Test
    void newRoundTest() {
        assertEquals(1, testCase1.getRoundNumber());
        assertEquals(0,testCase1.getScore());
        testCase1.newRound();
        testCase1.updateScore();
        assertEquals(2, testCase1.getRoundNumber());
        assertEquals(0,testCase1.getScore());
        testCase1.getEnemies().killAll();
        testCase1.updateScore();
        assertEquals(3,testCase1.getRoundNumber());
        assertEquals(10,testCase1.getScore());
        testCase1.newRound();
        assertEquals(4,testCase1.getRoundNumber());
        assertEquals(10,testCase1.getScore());
        testCase1.updateScore();
        assertEquals(0,testCase1.getScore());
        testCase1.getEnemies().killAll();
        testCase1.updateScore();
        assertEquals(20,testCase1.getScore());

    }

    @Test
    void gameOverTest() {
        assertFalse(testCase1.gameOver());
        testCase1.getUserPlayer().setHealth(0);
        assertTrue(testCase1.gameOver());

    }

    @Test
    void fireBulletTest() {
        assertEquals(0,testCase1.getListOfBullets().size());
        testCase1.fireBullet();
        assertEquals(1, testCase1.getListOfBullets().size());
    }

}
