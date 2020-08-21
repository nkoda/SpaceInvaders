package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;


/*
 * Represents the panel in which the scoreboard is displayed.
 */
@SuppressWarnings("serial")
public class ScorePanel extends JPanel {
    private static final String ENEMIES_KILLED_TXT = "Enemies Shot Down: ";
    private static final String ENEMIES_PRESENT_TXT = "Total Enemies: ";
    private static final String ROUND_NUM_TXT = "round: ";
    private static final String BULLETS_SHOT_TEXT = "Bullets Fired: ";
    private static final String SAVE_QUIT_TEXT = "Press: V to quit, Z to save";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private Game game;
    private JLabel enemiesDeadLbl;
    private JLabel roundNumLbl;
    private JLabel enemiesAliveLbl;
    private JLabel bulletsShotLbl;
    private JLabel commandsLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(Game g) {
        game = g;
        setBackground(new Color(180, 75, 76));
        enemiesDeadLbl = new JLabel(ENEMIES_KILLED_TXT + game.getScore());
        enemiesDeadLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        roundNumLbl = new JLabel(ROUND_NUM_TXT + game.getRoundNumber());
        roundNumLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        enemiesAliveLbl = new JLabel(ENEMIES_PRESENT_TXT + game.getEnemies().size());
        enemiesAliveLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        bulletsShotLbl = new JLabel(BULLETS_SHOT_TEXT + game.getListOfBullets().size());
        commandsLbl = new JLabel(SAVE_QUIT_TEXT);

        add(commandsLbl);
        add(Box.createHorizontalStrut(5));
        add(enemiesAliveLbl);
        add(enemiesDeadLbl);
        add(Box.createHorizontalStrut(5));
        add(roundNumLbl);
        add(Box.createHorizontalStrut(5));
        add(bulletsShotLbl);
        add(Box.createHorizontalStrut(5));

    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of invaders shot and number of missiles
    //           remaining to reflect current state of game
    public void update() {
        game.updateScore();
        enemiesDeadLbl.setText(ENEMIES_KILLED_TXT + game.getScore());
        roundNumLbl.setText(ROUND_NUM_TXT + game.getRoundNumber());
        enemiesAliveLbl.setText(ENEMIES_PRESENT_TXT + game.getEnemies().size());
        bulletsShotLbl.setText(BULLETS_SHOT_TEXT + game.getListOfBullets().size());
        repaint();
    }
}
