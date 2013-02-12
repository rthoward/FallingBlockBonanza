package com.rhoward;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SoundManager {

    private HashMap<String, Clip> sounds;

    public SoundManager() {
        this.sounds = new HashMap<String, Clip>(8);
        init();
    }

    private void init() {

        Clip clip = null;

        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("res/sounds/rotate.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.sounds.put("rotate", clip);
    }

    public void playSound(String soundName) {
        Clip currentClip = this.sounds.get(soundName);

        if (currentClip.isRunning())
            currentClip.stop();
        currentClip.setFramePosition(0);
        currentClip.start();
    }

}
