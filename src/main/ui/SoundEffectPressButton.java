package ui;

// the sound effect heard when pressing a button in the main menu

public class SoundEffectPressButton extends SoundEffect {
    private final String audioPath = "src/main/ui/resources/pressButtonSound.wav";


    //MODIFIES: super
    //EFFECTS: Plays the audio file for when the presses a button
    public SoundEffectPressButton() {
        setFile(audioPath);
        play();
    }


}
