package ui;

// the sound effect heard when hovering over a button in the main menu
public class SoundEffectHoverButton extends SoundEffect {
    private final String audioPath = "src/main/ui/resources/hoverButtonSound.wav";

    //MODIFIES: super
    //EFFECTS: Plays the audio file for when the user hovers over a button
    public SoundEffectHoverButton() {
        setFile(audioPath);
        play();
    }

}
