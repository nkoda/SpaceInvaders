package ui;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

//this class is intended to play .wav files in this game
public abstract class SoundEffect {
    protected Clip clip;

    //REQUIRES: String of .wav file path
    //MODIFIES: clip
    //EFFECTS: tries to access the audio clip
    protected void setFile(String soundFileName) {

        try {
            File file = new File(soundFileName);
            AudioInputStream sound  = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(sound);

        } catch (Exception e) {
            System.out.println("Error: can not load audio clip");
        }
    }

    //EFFECTS: plays the audio clip
    protected void play() {
        initializeVolume();
        clip.start();
    }

    //EFFECTS:
    protected void initializeVolume() {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-30.0f);
    }

}
