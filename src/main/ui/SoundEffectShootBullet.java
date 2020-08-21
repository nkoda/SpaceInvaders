package ui;

// the sound effect heard when shooting a bullet in the gamepanel
public class SoundEffectShootBullet extends SoundEffect {
    private final String audioPath = "src/main/ui/resources/ShootBullet.wav";

    //MODIFIES: super
    //EFFECTS: Plays the audio file for when the presses a button
    public SoundEffectShootBullet() {
        setFile(audioPath);
        play();
    }
}
