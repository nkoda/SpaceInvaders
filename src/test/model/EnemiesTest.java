package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EnemiesTest {

    private Enemies enemies1, enemies2;
    private Enemy e1, e2, e3;

    @BeforeEach
    void runBefore() {
        enemies1 = new Enemies();
        enemies2 = new Enemies();

        enemies1.setMainPlayerPosX(0);
        enemies1.setMainPlayerPosY(0);

        enemies2.setMainPlayerPosX(0);
        enemies2.setMainPlayerPosY(0);
        enemies1.spawn(10);




        e1 = new Enemy(1,2);
        e2 = new Enemy(1,2);
        e3 = new Enemy(1,2);

    }

    @Test
    void spawnTest() {
        assertEquals(10, enemies1.size());
    }

    @Test
    void getNumDeadTest() {
        assertEquals(0, enemies1.getNumDead());
        e1.setHealth(0);
        enemies1.forceAddEnemy(e1);
        assertEquals(1, enemies1.getNumDead());

        e2.setHealth(-1);
        e3.setHealth(0);
        enemies1.forceAddEnemy(e2);
        enemies1.forceAddEnemy(e3);
        assertEquals(3, enemies1.getNumDead());
    }

//    @Test
//    void removeDead() {
//        e1.setHealth(0);
//        enemies1.forceAddEnemy(e1);
//        enemies1.removeDead();
//        assertEquals(0,enemies1.getNumDead());
//    }

    @Test
    void getEnemiesTest() {
        assertEquals(10,enemies1.getEnemies().size());
        enemies1.forceAddEnemy(e1);
        enemies1.forceAddEnemy(e2);
        enemies1.forceAddEnemy(e3);
        assertEquals(13, enemies1.getEnemies().size());

    }

    @Test
    void saveTest() {
        String GAME_FILE = "./data/saveTestEnemy.txt";
        try {
            Writer writer = new Writer(new File(GAME_FILE));

            writer.write(enemies1);
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
