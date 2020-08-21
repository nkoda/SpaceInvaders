package ui;

import javax.sound.sampled.FloatControl;

// the backround music heard in the main menu
public class SoundEffectBackgroundMusic extends SoundEffect {
    private final String audioPath = "src/main/ui/resources/backgroundMusic.wav";

    public SoundEffectBackgroundMusic() {
        setFile(audioPath);
        play();
    }

    @Override
    protected void play() {
        initializeVolume();
        clip.start();
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    @Override
    protected void initializeVolume() {
        FloatControl gainControl =
                (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-10.0f);
    }
}
