package com.rhoward;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;


import java.io.IOException;
import java.util.HashMap;

public class SoundManager {

    private HashMap<String, Audio> sounds;

    public SoundManager() {
        this.sounds = new HashMap<String, Audio>(8);
        init();
    }

    private void init() {

        // TODO: probably don't need openAL for these simple sound effects

        Audio rotate = null;

        try {
            rotate = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/sounds/rotate.wav"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.sounds.put("rotate", rotate);
    }

    public void playSound(String soundName) {
        this.sounds.get(soundName).playAsSoundEffect(1.0f, 1.0f, false);
    }

}
