package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//this is where the game window will be drawn
// model after SpaceInvadersBase's SpaceInvaders
public class GameScene extends JFrame {

    private static final int INTERVAL = 20;
    private Game game;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public GameScene(Game game) {
        super("Astroid Shooters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        this.game = game;
        this.game.setGameSceneRef(this);
        gamePanel = new GamePanel(game);
        scorePanel = new ScorePanel(game);
        add(gamePanel);
        add(scorePanel, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        addTimer();


    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    // Set time for game
    // modifies: none
    // effects:  initializes time such that the game updates over an
    //           INTERVAL milliseconds
    private void addTimer() {
        Timer t = new Timer(INTERVAL, ae -> {
            game.update();
            gamePanel.repaint();
            scorePanel.update();
            //game.gameSceneLocation(gamePanel.getLocation());
        });

        t.start();
    }

    /*
     * A key handler to respond to key events
     */
    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e.getKeyCode());
        }
    }

}
