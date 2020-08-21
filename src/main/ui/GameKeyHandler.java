package ui;

import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

// this class represents the event handler for the games
public class GameKeyHandler {
    private final Game game;

    public GameKeyHandler(Game game) {
        this.game = game;
    }

    // Responds to key press codes
    // modifies: this
    // effects:  turns tank, fires missiles and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_SPACE) {
            game.fireBullet();
            new SoundEffectShootBullet();
        } else if (keyCode == KeyEvent.VK_Z) {
            game.getSaveGame().saveGame();
        } else if (keyCode == KeyEvent.VK_V) {
            game.getGameScene().dispatchEvent(new WindowEvent(game.getGameScene(), WindowEvent.WINDOW_CLOSING));
        } else if (keyCode == KeyEvent.VK_SHIFT) {
            game.newRound();
            game.getEnemies().killAll();
        } else {
            playerControl(keyCode);
        }
    }

    // modifies: this
    // effects: turns tank in response to key code
    public void playerControl(int keyCode) {
        if (keyCode == KeyEvent.VK_A) {
            game.getUserPlayer().moveLeft();
        } else if (keyCode == KeyEvent.VK_D) {
            game.getUserPlayer().moveRight();
        } else if (keyCode == KeyEvent.VK_W) {
            game.getUserPlayer().moveUp();
        } else if (keyCode == KeyEvent.VK_S) {
            game.getUserPlayer().moveDown();
        }
    }
}