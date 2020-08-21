package ui;

import model.Enemy;
import model.PlayerData;
import persistence.Reader;
import persistence.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

// This class represents the save and loading functionality of a game
public class SaveGame {
    private static final String GAME_FILE = "./data/game_files.txt";
    private final Game game;

    // EFFECTS: saves state of the game
    public SaveGame(Game game) {
        this.game = game;
    }

    //EFFECTS: saves the game files into a text document
    public void saveGame() {
        try {
            Writer writer = new Writer(new File(GAME_FILE));
            writer.write(game.getUserPlayer());
            writer.write(game.getEnemies());
            writer.close();
            System.out.println("Game saved to file " + GAME_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save game to " + GAME_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    //MODIFIES: Game
    //EFFECTS: loads the game from the saved file
    public void loadGame() {

        try {
            List<PlayerData> playersData = Reader.readData(new File(GAME_FILE));
            initUserPlayer(playersData);

            Enemy tempEnemy = new Enemy(game.getUserPlayer().getPositionX(), game.getUserPlayer().getPositionY());
            initEnemiesProperties(playersData, tempEnemy);

        } catch (IOException e) {
            System.out.println("failed upload");
        }
    }

    //REQUIRES: ListOfPlayerData
    //MODIFIES: game's UserPlayer
    //EFFECTS: initialize the user player based on their saved data.
    private void initUserPlayer(List<PlayerData> playersData) {
        game.getUserPlayer().setHealth(playersData.get(0).getHealth());
        game.getUserPlayer().setHealth(playersData.get(0).getAttack());
        game.getUserPlayer().setPositionX(playersData.get(0).getPositionX());
        game.getUserPlayer().setPositionY(playersData.get(0).getPositionY());
    }

    //REQUIRES: List of Player Data, non-initialized Enemy
    //MODIFIES: Game's Enemy
    //EFFECTS: Initialize enemies in the game based on the parameters in the saved game
    private void initEnemiesProperties(List<PlayerData> playersData, Enemy tempEnemy) {
        for (int i = 1; i < playersData.size(); i++) {
            initEnemy(playersData, tempEnemy, i);
            game.getEnemies().forceAddEnemy(tempEnemy);
        }
    }

    //REQUIRES: Non-empty ListOfPlayerData, nonInitializedEnemy, element (i) in Enemies
    //MODIFIES: Enemy
    //EFFECTS: Initialize an enemy with the parameters saved in the game file
    private void initEnemy(List<PlayerData> playersData, Enemy tempEnemy, int i) {
        tempEnemy.setHealth(playersData.get(i).getHealth());
        tempEnemy.setAttack(playersData.get(i).getAttack());
        tempEnemy.setPositionX(playersData.get(i).getPositionX());
        tempEnemy.setPositionY(playersData.get(i).getPositionY());
    }
}