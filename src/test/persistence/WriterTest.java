package persistence;

import model.Enemy;
import model.MainPlayer;
import model.PlayerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Difficulty;
import ui.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class WriterTest {
    private static final String TEST_FILE = "./data/data_filesWrite.txt";
    private Writer testWriter;
    private MainPlayer player;
    private Enemy enemy;

    @BeforeEach
    void runBefore() throws FileNotFoundException, UnsupportedEncodingException {
        testWriter = new Writer(new File(TEST_FILE));
        player = new MainPlayer();
        enemy = new Enemy(player.getPositionX(), player.getPositionY());
    }

    @Test
    void testWriteAccounts() {
        // save chequing and savings accounts to file
        testWriter.write(player);
        testWriter.write(enemy);
        testWriter.close();

        // now read them back in and verify that the accounts have the expected values
        try {
            List<PlayerData> playerData = Reader.readData(new File(TEST_FILE));
            //player data
            assertEquals(20, playerData.get(0).getHealth());
            assertEquals(5, playerData.get(0).getAttack());
            assertEquals(Game.WIDTH / 2, playerData.get(0).getPositionX());
            assertEquals(Game.HEIGHT / 2, playerData.get(0).getPositionY());

            //Enemy data
            assertEquals(1, playerData.get(1).getHealth());
            assertEquals(5, playerData.get(1).getAttack());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }
    @Test
    void testLoadGame() {
        Game g = new Game(Difficulty.EASY);
        g.saveGame();
        g.loadGame();

    }
}