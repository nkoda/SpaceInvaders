package ui;

import model.*;
// Represents an astroid shooter game

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static ui.Difficulty.*;

// this game represents the main logic of the game
public class Game {
    private static final Integer EASY_SPAWN = 5;
    private static final Integer NORMAL_SPAWN = 10;
    private static final Integer HARD_SPAWN = 15;

    private final SaveGame saveGame = new SaveGame(this);
    private final GameKeyHandler gameKeyHandler = new GameKeyHandler(this);

    private Difficulty difficulty;
    private Integer score = 0;
    private Integer enemySpawn;
    private Integer roundNumber = 1;
    private Boolean isGameOver = false;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Enemies enemies = new Enemies();
    private MainPlayer userPlayer = new MainPlayer();
    private List<Bullet> listOfBullets = new ArrayList<>();

    private int panelPosX;
    private int panelPosY;
    private GameScene gameScene;


    public Game(Difficulty difficulty) {
        this.difficulty = difficulty == null ? EASY : difficulty;
        init();
    }


    // Updates the game on clock tick
    // modifies: this
    // effects:  updates tank, missiles and invaders
    public void update() {
        userPlayer.move();
        giveEnemiesUserPlayerLocation();
        enemies.updateEnemies();
        moveListOfBullets();
    }

    //todo save round and score of the game

    // EFFECTS: saves state of the game
    public void saveGame() {
        saveGame.saveGame();
    }

    //EFFECTS: this loads any game data saved
    public void loadGame() {
        saveGame.loadGame();
    }


    //EFFECTS: initializes the game
    private void init() {
        setDifficulty(difficulty);
        giveEnemiesUserPlayerLocation();
        enemies.spawn(enemySpawn);
        newRound();
    }


    //EFFECTS: updates the position of each bullet.
    private void moveListOfBullets() {
        for (Bullet b: listOfBullets) {
            b.move();
        }
    }

    //EFFECTS: checks
    // Responds to key press codes
    // modifies: this
    // effects:  turns tank, fires missiles and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        gameKeyHandler.keyPressed(keyCode);
    }


    // MODIFIES: this
    // EFFECTS: fires and records the bullet
    public void fireBullet() {
        Bullet b = new Bullet(userPlayer.getPositionX(), userPlayer.getPositionY());
        setBulletDirection(b);
        listOfBullets.add(b);
    }

    private void setBulletDirection(Bullet b) {
        Integer mousePosX = MouseInfo.getPointerInfo().getLocation().x;
        Integer mousePosY = MouseInfo.getPointerInfo().getLocation().y;
        Integer dxBullet = (mousePosX - userPlayer.getPositionX() - panelPosX)
                / abs(mousePosX - userPlayer.getPositionX() - panelPosX) * 4;
        Integer dyBullet = (mousePosY - userPlayer.getPositionY() - panelPosY)
                / abs(mousePosY - userPlayer.getPositionY() - panelPosY) * 4;

        b.setDx(dxBullet);
        b.setDy(dyBullet);
    }


    // MODIFIES: this
    // EFFECTS: sets the difficulty of the game.
    public void setDifficulty(Difficulty level) {
        difficulty = level;
        switch (level) {
            case EASY:
                this.enemySpawn = EASY_SPAWN;
                break;
            case NORMAL:
                this.enemySpawn = NORMAL_SPAWN;
                break;
            case HARD:
                this.enemySpawn = HARD_SPAWN;
                break;
        }

    }

    //MODIFIES: this
    //EFFECTS: will start a new round if all enemies are dead
    //EFFECTS: True if the game is over
    public void newRound() {
        if (enemies.initSize() == enemies.getNumDead()) {
            roundNumber++;
            //updateMetaData();
        }
        enemies.spawn(roundNumber * enemySpawn);
    }

    //EFFECTS: true if the game is over, false otherwise
    public Boolean gameOver() {
        isGameOver = userPlayer.isDead();
        return isGameOver;
    }

    //MODIFIES: this
    //EFFECTS: sets your score depending on how many enemies are dead
    public void updateScore() {
        this.score += enemies.getNumDead();
        //updateMetaData();
    }

    //EFFECTS: gives the enemies the location of the player
    private void giveEnemiesUserPlayerLocation() {
        enemies.setMainPlayerPosX(userPlayer.getPositionX());
        enemies.setMainPlayerPosY(userPlayer.getPositionY());
       // enemies.updateEnemies();
    }

    public String getDifficultyString() {
        String status = "";
        switch (difficulty) {
            case EASY:
                status = "Easy";
                break;
            case NORMAL:
                status = "Normal";
                break;
            case HARD:
                status = "Hard";
                break;
        }
        return status;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Integer getScore() {
        return this.score;
    }

    public Integer getRoundNumber() {
        return roundNumber;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public MainPlayer getUserPlayer() {
        return userPlayer;
    }


    public List<Bullet> getListOfBullets() {
        return listOfBullets;
    }

    public void setPanelLocation(Point location) {
        this.panelPosX = location.x;
        this.panelPosY = location.y;
    }

    public void setGameSceneRef(GameScene gameScene) {
        this.gameScene = gameScene;
    }

    public Window getGameScene() {
        return gameScene;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }
}
