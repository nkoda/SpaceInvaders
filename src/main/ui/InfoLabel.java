package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

//This class is to represent a headers for submenuScenes in the main menu
public class InfoLabel extends Label {

    public InfoLabel(String text) {
        setPrefSize(400,100);
        setPadding(new Insets(40, 40,40,40));
        setText(text);
        setWrapText(true);
        setLabelFont();
        setAlignment(Pos.CENTER_LEFT);
    }

    private void setLabelFont() {
        setFont(Font.font("Verdana", 23));
    }

}
