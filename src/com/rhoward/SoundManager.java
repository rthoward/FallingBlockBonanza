package com.rhoward;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SoundManager {

    private HashMap<String, Clip> sounds;
    private List<String> soundNames;

    public SoundManager() {
        this.sounds = new HashMap<String, Clip>(8);
        this.soundNames = new ArrayList<String>(8);
        this.soundNames.add("rotate");
        this.soundNames.add("clear_line");
        init();
    }

    private void init() {

        Clip clip = null;
        String soundPath;

        for (String soundName : soundNames) {

            soundPath = "res/sounds/" + soundName + ".wav";

            try {
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(soundPath)));
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            this.sounds.put(soundName, clip);
        }
    }

    public void playSound(String soundName) {

        // TODO: sounds lag / play repeatedly

        Clip currentClip = this.sounds.get(soundName);

        if (currentClip.isRunning())
            currentClip.stop();
        currentClip.setFramePosition(0);
        currentClip.start();
    }

}
