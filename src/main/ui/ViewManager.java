package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

// the main logic behind the main menu gui
public class ViewManager {

    private static final int MENU_BUTTONS_START_X = 100;
    private static final int MENU_BUTTONS_START_Y = 150;

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private MenuSubScene playerControlSubScene;
    private MenuSubScene initNewGameSubScene;
    private MenuSubScene loadSavedGameSubScene;
    private MenuSubScene exitGameSubScene;

    private MenuSubScene sceneToHide;

    private Game game;

    List<Button> menuButtons;
    List<DifficultyChooser> difficultyList;
    private Difficulty difficultyChosen;
    private boolean isDifficultySet;

    public ViewManager() {

        isDifficultySet = false;

        initViewManager();
        createSubScenes();
        initButtons();

        new SoundEffectBackgroundMusic();

    }

    //REQUIRES: none
    //MODIFIES: mainStage
    //EFFECTS: initializes the mainStage
    private void initViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainPane.setStyle("-fx-background-color: rgba(0, 7, 4, 1);");
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
    }

    //REQUIRES: none
    //MODIFIES: mainPane
    //EFFECTS: creates all the subScenes in the main menu
    private void createSubScenes() {
        createNewPlayerControlSubScene();

        createNewGameSubScene();

        createLoadSavedGameSubScene();

        exitGameSubScene = new MenuSubScene();
        mainPane.getChildren().add(exitGameSubScene);

    }

    private void createNewPlayerControlSubScene() {
        playerControlSubScene = new MenuSubScene();
        mainPane.getChildren().add(playerControlSubScene);
        VBox vboxControls = new VBox();
        vboxControls.setLayoutX(180);
        vboxControls.setLayoutY(10);
        vboxControls.setAlignment(Pos.CENTER_LEFT);
        vboxControls.setSpacing(15);

        InitPlayerControlText initPlayerControlText = new InitPlayerControlText().invoke();
        Label playerControls = initPlayerControlText.getPlayerControls();
        Label moveUp = initPlayerControlText.getMoveUp();
        Label moveLeft = initPlayerControlText.getMoveLeft();
        Label moveDown = initPlayerControlText.getMoveDown();
        Label moveRight = initPlayerControlText.getMoveRight();
        Label saveGame = initPlayerControlText.getSaveGame();
        Label exitGame = initPlayerControlText.getExitGame();

        vboxControls.getChildren().add(playerControls);
        vboxControls.getChildren().add(moveUp);
        vboxControls.getChildren().add(moveLeft);
        vboxControls.getChildren().add(moveDown);
        vboxControls.getChildren().add(moveRight);
        vboxControls.getChildren().add(saveGame);
        vboxControls.getChildren().add(exitGame);

        playerControlSubScene.getPane().getChildren().add(vboxControls);

    }

    //REQUIRES: none
    //MODIFIES: mainPane, chosenDifficultyLabel
    //EFFECTS: initializes the load save game subScene
    private void createLoadSavedGameSubScene() {
        loadSavedGameSubScene = new MenuSubScene();
        mainPane.getChildren().add(loadSavedGameSubScene);

        InfoLabel chooseDifficultyLabel = new InfoLabel("Set Difficulty: ");
        initGameSubMenuProperties(chooseDifficultyLabel, loadSavedGameSubScene, createLoadSavedGameStartButton());
    }

    //    //REQUIRES: none
    //    //MODIFIES: mainPane, chosenDifficultyLabel
    //    //EFFECTS: initializes the new game subScene
    private void createNewGameSubScene() {
        initNewGameSubScene = new MenuSubScene();
        mainPane.getChildren().add(initNewGameSubScene);

        InfoLabel chooseDifficultyLabel = new InfoLabel("Set Difficulty: ");
        initGameSubMenuProperties(chooseDifficultyLabel, initNewGameSubScene, createStartButton());
    }

    //REQUIRES: none
    //MODIFIES: infoLabel, menuSubScene
    //EFFECTS: initializes properties of the GameSubMenu
    private void initGameSubMenuProperties(InfoLabel infoLabel, MenuSubScene menuSubScene, Button button) {
        infoLabel.setLayoutX(110);
        infoLabel.setLayoutY(25);
        menuSubScene.getPane().getChildren().add(infoLabel);
        menuSubScene.getPane().getChildren().add(createDifficultyToChoose());
        menuSubScene.getPane().getChildren().add(button);
    }

    //REQUIRES: none
    //MODIFIES: none
    //EFFECTS: creates and returns the game difficulty toggles as a HBox
    private HBox createDifficultyToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        difficultyList = new ArrayList<>();
        ToggleGroup toggleGroup  = new ToggleGroup();
        initDifficultyButtons(box, toggleGroup);
        box.setLayoutX(62);
        box.setLayoutY(150);
        return box;
    }

    //REQUIRES: none
    //MODIFIES: mainPane, chosenDifficultyLabel
    //EFFECTS: initializes the load save game subScene
    private void showSubScene(MenuSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    //REQUIRES: none
    //MODIFIES: playerControlSubScene, initNewGameSubScene, loadSaveGameSubScene
    //EFFECTS: creates the buttons for the main menu to display
    private void initButtons() {
        createButton("Player Controls", playerControlSubScene);
        createButton("Create New Game", initNewGameSubScene);
        createButton("Load Saved Game", loadSavedGameSubScene);
        createExitButton();
    }

    //REQUIRES: HBox, ToggleGroup initialized with levels
    //MODIFIES: difficultyChosen, isDifficultySet
    //EFFECTS: creates a toggle button for each enum in Difficulty class.
    private void initDifficultyButtons(HBox box, ToggleGroup toggleGroup) {
        for (Difficulty difficulty : Difficulty.values()) {
            DifficultyChooser difficultyChooser = new DifficultyChooser(difficulty, toggleGroup);
            box.getChildren().add(difficultyChooser);
            difficultyChooser.getToggleButton().addEventHandler(MouseEvent.MOUSE_ENTERED,
                    event -> new SoundEffectHoverButton());
            difficultyChooser.getToggleButton().setOnAction(event -> {
                difficultyChosen = difficulty;
                isDifficultySet = true;
                new SoundEffectPressButton();
            });
        }
    }

    //REQUIRES: none
    //MODIFIES: none
    //EFFECTS: initializes the start button
    private Button createStartButton() {
        Button button = initStartButtonProperties();
        button.setOnAction(event -> {
            if (isDifficultySet) {
                game = new Game(difficultyChosen);
                new GameScene(game);
                new SoundEffectHoverButton();
            }
        });
        return button;
    }

    //REQUIRES: none
    //MODIFIES: none
    //EFFECTS: initializes the load save game button
    private Button createLoadSavedGameStartButton() {
        Button button = initStartButtonProperties();
        button.setOnAction(event -> {
            if (isDifficultySet) {
                game = new Game(difficultyChosen);
                game.loadGame();
                new GameScene(game);
                new SoundEffectPressButton();
            }
        });
        return button;
    }

    //REQUIRES: none
    //MODIFIES: button
    //EFFECTS: sets the layout of the button and assigns the event of the button
    private Button initStartButtonProperties() {
        Button button = new Button("Start Game");
        button.setPrefSize(200,50);
        button.setLayoutX(130);
        button.setLayoutY(300);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> new SoundEffectHoverButton());
        return button;
    }

    //REQUIRES: button
    //MODIFIES: menuButtons, MainPane
    //EFFECTS: adds a given button onto the scene of the main menu
    private void addMenuButton(Button button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    //REQUIRES: Text of button, the mainmenu sub scene
    //MODIFIES: none
    //EFFECTS: creates a new button for the main menu subscene
    private void createButton(String text, MenuSubScene menuSubScene) {
        Button button = new Button(text);

        initButtonProperties(button);
        button.setOnAction(event -> {
            showSubScene(menuSubScene);
            new SoundEffectPressButton();
        });
    }

    //EFFECTS: initializes the given button to appear in the menu
    private void initButtonProperties(Button button) {
        button.setPrefSize(200,50);
        addMenuButton(button);
        button.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> new SoundEffectHoverButton());
    }

    //REQUIRES: none
    //MODIFIES: mainStage
    //EFFECTS: creates a new exit button to close the stage
    private void createExitButton() {
        Button button = new Button("Exit");
        initButtonProperties(button);
        button.setOnAction(event -> {
            new SoundEffectPressButton();
            mainStage.close();
        });
    }


    private class InitPlayerControlText {
        private Label playerControls;
        private Label moveUp;
        private Label moveLeft;
        private Label moveDown;
        private Label moveRight;
        private Label saveGame;
        private Label exitGame;

        public Label getPlayerControls() {
            return playerControls;
        }

        public Label getMoveUp() {
            return moveUp;
        }

        public Label getMoveLeft() {
            return moveLeft;
        }

        public Label getMoveDown() {
            return moveDown;
        }

        public Label getMoveRight() {
            return moveRight;
        }

        public Label getSaveGame() {
            return saveGame;
        }

        public Label getExitGame() {
            return exitGame;
        }

        private InitPlayerControlText invoke() {
            playerControls = new Label("Player Controls: ");
            playerControls.setFont(Font.font("Verdana", 23));
            playerControls.setPadding(new Insets(40, 40,40,-50));

            moveUp = new Label("W - Move Up");
            moveLeft = new Label("A - Move Left");
            moveDown = new Label("S - move Down");
            moveRight = new Label("D - move Right");
            saveGame = new Label("Z - Save Game");
            exitGame = new Label("V - Quit Game");
            return this;
        }
        //todo
    }
}
