package ui;

import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Duration;


// the subscene logic for the gui
public class MenuSubScene extends SubScene {

    private static final Integer HEIGHT = 400;
    private static final Integer WIDTH = 600;
    Color bgColour = Color.rgb(231,89,92,0.7);
    BackgroundFill backgroundFill = new BackgroundFill(bgColour, CornerRadii.EMPTY, Insets.EMPTY);

    private boolean isHidden;

    public MenuSubScene() {
        super(new AnchorPane(), WIDTH, HEIGHT);
        prefHeight(HEIGHT);
        prefWidth(WIDTH);

        AnchorPane root2 = (AnchorPane) this.getRoot();

        root2.setBackground(new Background(backgroundFill));

        isHidden = true;

        setLayoutX(1024);
        setLayoutY(180);
    }

    public void moveSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.3));
        transition.setNode(this);

        if (isHidden) {
            transition.setToX(-676);
            isHidden = false;
        } else {
            transition.setToX(0);
            isHidden = true;
        }

        transition.play();
    }

    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
