package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.JPanel;

import model.*;

/*
 * The panel in which the game is rendered.
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private static final String OVER = "Game Over!";
    private static final String REPLAY = "R to replay";

    private static final String PAUSED = "Game Paused.";
    private static final String QUITNSAVE = "Press K: Quit and Save";
    private static final String QUIT = "Press L: Quit";
    private static final String CONT = "Press J: Continue Game";

    private Game game;
    //private GamePauseMenu gamePauseMenu;

    // Constructs a game panel
    // effects:  sets size and background colour of panel,
    //           updates this with the game to be displayed
    public GamePanel(Game game) {
        setPreferredSize(new Dimension(Game.WIDTH, Game.HEIGHT));
        setBackground(new Color(0,7,4));
        this.game = game;
        game.setPanelLocation(this.getLocation());

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawGame(graphics);

        if (game.gameOver()) {
            gameOverVisual(graphics);
        }
    }

    // Draws the game
    // modifies: g
    // effects:  draws the game onto g
    private void drawGame(Graphics graphics) {
        drawUserPlayer(graphics);
        drawEnemies(graphics);
        drawBullets(graphics);
    }

    // Draw the tank
    // modifies: g
    // effects:  draws the tank onto g
    private void drawUserPlayer(Graphics graphics) {
        MainPlayer p = game.getUserPlayer();
        Color savedCol = graphics.getColor();
        graphics.setColor(p.COLOR);
        graphics.fillRect(p.getPositionX() - p.SIZE_X / 2, p.getPositionY() - p.SIZE_Y / 2, p.SIZE_X, p.SIZE_Y);
        graphics.setColor(savedCol);
    }

    //todo
    // finish implementing drawing of invader

    // Draw the invaders
    // modifies: g
    // effects:  draws the invaders onto g
    private void drawEnemies(Graphics g) {
        for (Map.Entry<Integer, Enemy> entry :game.getEnemies().getEnemies().entrySet()) {
            drawEnemy(g, entry.getValue());
        }
    }

   // Draw an invader
   // modifies: g
   // effects:  draws the invader i onto g
    private void drawEnemy(Graphics g, Enemy i) {
        Color savedCol = g.getColor();
        g.setColor(Enemy.COLOR);
        g.fillOval(i.getPositionX() - Enemy.SIZE_X / 2, i.getPositionY() - Enemy.SIZE_Y / 2,
                Enemy.SIZE_X, Enemy.SIZE_Y);
        g.setColor(savedCol);
    }


    // Draws the missiles
    // modifies: g
    // effects:  draws the missiles onto g
    private void drawBullets(Graphics graphics) {
        for (Bullet next : game.getListOfBullets()) {
            drawBullet(graphics, next);
        }
    }

    // Draws a missile
    // modifies: g
    // effects:  draws the missile m onto g
    private void drawBullet(Graphics graphics, Bullet bullet) {
        Color savedCol = graphics.getColor();
        graphics.setColor(Bullet.COLOR);
        graphics.fillOval(bullet.getPosX() - Bullet.SIZE_X / 2,
                bullet.getPosY() - Bullet.SIZE_Y / 2,
                Bullet.SIZE_X, Bullet.SIZE_Y);
        graphics.setColor(savedCol);
    }

    // Draws the "game over" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    private void gameOverVisual(Graphics graphics) {
        Color saved = graphics.getColor();
        graphics.setColor(new Color(67, 84, 101));
        graphics.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = graphics.getFontMetrics();
        centreString(OVER, graphics, fm, Game.HEIGHT / 2);
        centreString(REPLAY, graphics, fm, Game.HEIGHT / 2 + 50);
        graphics.setColor(saved);
    }

    // Draws the "game pause" message and replay instructions
    // modifies: g
    // effects:  draws "game over" and replay instructions onto g
    public void pauseVisual(Graphics graphics) {
        Color saved = graphics.getColor();
        graphics.setColor(new Color(170, 180, 125));
        graphics.setFont(new Font("Arial", 20, 20));
        FontMetrics fm = graphics.getFontMetrics();
        centreString(PAUSED, graphics, fm, Game.HEIGHT / 2);
        centreString(QUITNSAVE, graphics, fm, Game.HEIGHT / 2 + 50);
        centreString(QUIT, graphics, fm, Game.HEIGHT / 2 + 80);
        centreString(CONT, graphics, fm, Game.HEIGHT / 2 + 110);
        graphics.setColor(saved);
    }

    // Centres a string on the screen
    // modifies: g
    // effects:  centres the string str horizontally onto g at vertical position yPos
    private void centreString(String str, Graphics graphics, FontMetrics fontMetrics, int posY) {
        int width = fontMetrics.stringWidth(str);
        graphics.drawString(str, (Game.WIDTH - width) / 2, posY);
    }
}
