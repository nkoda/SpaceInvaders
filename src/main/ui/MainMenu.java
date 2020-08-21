package ui;

import java.util.Scanner;
import model.*;

import static ui.Difficulty.*;

// This code used TellerApp as a referenced
//Note that this class has been replaced with a GUI


// this app is modelled after the TellerApp repo
public class MainMenu {
    private Scanner input;


// EFFECTS: runs the main menu of the game
    public MainMenu() {
        boolean keepGoingMainMenu = true;
        String command;
        input = new Scanner(System.in);

        while (keepGoingMainMenu) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoingMainMenu = false;
            } else {
                processCommandDisplayMenu(command);
            }
        }
    }

    // EFFECTS:  The display Menu of the main menu
    private void displayMenu() {
        System.out.println("\n Astroid Shooting Game");
        System.out.println("\tn New Game");
        System.out.println("\tl Load Game");
        System.out.println("\te Exit");
        System.out.println("\tc Game Controls");
    }

    // EFFECTS: handles the controls for the display Menu
    private void processCommandDisplayMenu(String command) {
        if (command.equals("n")) {
            runNewGame();
        } else if (command.equals("l")) {
            Game game = new Game(EASY);
            game.loadGame();
            new GameScene(game);
        } else if (command.equals("c")) {
            printControls();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: handles the controls for the runNewGame menu
    private void processCommandRunNewGame(String command) {
        if (command.equals("e")) {
            Game game = new Game(EASY);
            new GameScene(game);
        } else if (command.equals("n")) {
            Game game = new Game(NORMAL);
            new GameScene(game);
        } else if (command.equals("h")) {
            Game game = new Game(NORMAL);
            game.setDifficulty(HARD);
            //printGameStatistics(game);
            new GameScene(game);
        } else {
            System.out.println("\n Input was not a valid command");
        }
    }

    //EFFECTS: the controls used to play the game
    private void printControls() {
        System.out.println("\n CONTROLS:");
        System.out.println("\t W - Move up");
        System.out.println("\t A - Move left");
        System.out.println("\t S - Move down");
        System.out.println("\t D - move right");
        System.out.println("\n Press Z anytime to save the game");
        System.out.println("\n Press V anytime to quit the game");
        System.out.println("\n Press F anytime to pause the game");
    }

    //EFFECTS: displays to the user the newly generated game stats.
    private void printGameStatistics(Game game) {
        System.out.println("\n Difficulty: " + game.getDifficultyString());
        System.out.println("\n UserPlayer");
        System.out.println("\t Health: " + game.getUserPlayer().getHealth());
        System.out.println("\t Attack Magnitude: " + game.getUserPlayer().getAttack());
        System.out.println("\n Enemies");
        System.out.println("\t Size:" + game.getEnemies().initSize());
    }


//TODO Save menu to save game


//    private void runSaveMenu() {}

    // EFFECTS: new game menu
    private void runNewGame() {
        input = new Scanner(System.in);
        String command;
        boolean keepGoingRunNewGame = true;

        while (keepGoingRunNewGame) {
            System.out.println(("\n Game Settings"));
            System.out.println("\n Set Difficulty");
            System.out.println("\tE Easy");
            System.out.println("\tN Normal");
            System.out.println("\tH Hard");
            System.out.println("\tQ return to Main Menu");
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoingRunNewGame = false;
            } else {
                processCommandRunNewGame(command);
            }
        }
    }

//TODO  Remove saved Games
//    private void removeGame() {}

}