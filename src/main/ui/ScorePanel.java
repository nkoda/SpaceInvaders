package ui;

import javax.swing.*;
import java.awt.*;


/*
 * Represents the panel in which the scoreboard is displayed.
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
    private static final String ENEMIES_KILLED_TXT = "Enemies Shot Down: ";
    private static final String SCORE = "Score: ";
    private static final String ROUND_NUM_TXT = "round: ";
    private static final String HEALTH_TXT = "Health: ";
    private static final String SAVE_QUIT_TEXT = "Press: V to quit, Z to save";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private Game game;
    private JLabel playerHealthLbl;
    private JLabel enemiesDeadLbl;
    private JLabel roundNumLbl;
    private JLabel enemiesAliveLbl;
    private JLabel commandsLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g) {
        game = g;
        setBackground(new Color(180, 75, 76));
        enemiesDeadLbl = new JLabel(ENEMIES_KILLED_TXT + game.getScore());
        enemiesDeadLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        playerHealthLbl = new JLabel(HEALTH_TXT + game.getPlayerHealth());
        playerHealthLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        roundNumLbl = new JLabel(ROUND_NUM_TXT + game.getRoundNumber());
        roundNumLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        enemiesAliveLbl = new JLabel(SCORE + game.getScore());
        enemiesAliveLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        commandsLbl = new JLabel(SAVE_QUIT_TEXT);
        add(commandsLbl);
        add(Box.createHorizontalStrut(2));
        add(enemiesAliveLbl);
        add(Box.createHorizontalStrut(2));
        add(playerHealthLbl);
        add(Box.createHorizontalStrut(1));
        add(roundNumLbl);
        add(Box.createHorizontalStrut(2));
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        game.updateScore();
        playerHealthLbl.setText(HEALTH_TXT + game.getPlayerHealth());
        enemiesDeadLbl.setText(ENEMIES_KILLED_TXT + game.getScore());
        roundNumLbl.setText(ROUND_NUM_TXT + game.getRoundNumber());
        enemiesAliveLbl.setText(SCORE + game.getScore());
        repaint();
    }
}
