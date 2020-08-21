package ui;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

// this class is the button layout for choosing the difficulties in the main menu
public class DifficultyChooser extends VBox {

    private ToggleButton toggleButton;
    private ToggleGroup toggleGroup;

    public DifficultyChooser(Difficulty difficulty, ToggleGroup toggleGroup) {
        this.toggleGroup  = toggleGroup;
        init(difficulty);
        format();
    }

    //REQUIRES: none
    //MODIFIES: this
    //EFFECTS: formats this instance to preferred style
    private void format() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        //this.getChildren().add(difficultyLabel);
        this.getChildren().add(toggleButton);
    }

    //REQUIRES: none
    //MODIFIES: toggleButton
    //EFFECTS: assigns the text within the button
    private void init(Difficulty difficulty) {
        toggleButton = new ToggleButton();
        toggleButton.setPrefSize(100,75);
        toggleButton.setToggleGroup(toggleGroup);

        switch (difficulty) {
            case EASY:
             //   difficultyLabel.setText("EASY");
                toggleButton.setText("EASY");
                break;
            case NORMAL:
             //   difficultyLabel.setText("NORMAL");
                toggleButton.setText("NORMAL");
                break;
            case HARD:
            //    difficultyLabel.setText("HARD");
                toggleButton.setText("HARD");
                break;
        }
    }

    public ToggleButton getToggleButton() {
        return toggleButton;

    }

}
