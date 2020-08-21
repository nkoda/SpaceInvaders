package persistence;

import model.Enemy;
import model.PlayerData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {
    private static final Integer NULLPOS = 1;

    @Test
    void TestParsePlayer() {
        try {
            List<PlayerData> playerData = Reader.readData(new File("./data/game_files.txt"));
            assertEquals(20, playerData.get(0).getHealth());
            assertEquals(5, playerData.get(0).getAttack());
            assertEquals(400, playerData.get(0).getPositionX());
            assertEquals(300, playerData.get(0).getPositionY());

            Enemy tempEnemy = new Enemy(NULLPOS, NULLPOS);
            List<Enemy> enemies = new ArrayList<>();

            for (int i = 1; i < playerData.size(); i++) {
                tempEnemy.setHealth(playerData.get(i).getHealth());
                tempEnemy.setAttack(playerData.get(i).getAttack());
                tempEnemy.setPositionX(playerData.get(i).getPositionX());
                tempEnemy.setPositionY(playerData.get(i).getPositionY());

                assertEquals(5, tempEnemy.getHealth());
                assertEquals(1, tempEnemy.getAttack());
                assertEquals(0, tempEnemy.getPositionX());
                assertEquals(0, tempEnemy.getPositionY());
                enemies.add(tempEnemy);
                }
            assertEquals(5, enemies.size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    void readDataTest() {
        try {
            List<PlayerData> playerData = Reader.readData(new File("./data/game_files.txt"));
            assertEquals(20, playerData.get(0).getHealth());
            assertEquals(5, playerData.get(0).getAttack());
            assertEquals(400, playerData.get(0).getPositionX());
            assertEquals(300, playerData.get(0).getPositionY());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }

    }

    @Test
    void testIOException() {
        try {
            Reader.readData(new File("./path/does/not/exist/data.txt"));
        } catch (IOException e) {
            // expected
        }
    }
}