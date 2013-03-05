package com.rhoward.pit;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextureManager {

    // singleton class
    private static TextureManager instance = null;
    private String textureDirectory = "res/texture/";
    private List<String> textureNames = new ArrayList<String>(8);

    private HashMap<String, Texture> textures;

    protected TextureManager() {
        initialize();
    }

    public static TextureManager getInstance() {
        if (instance == null)
            instance = new TextureManager();
        return instance;
    }

    public Texture getTexture(String key) {
        return this.textures.get(key);
    }

    private void initialize() {
        textures = new HashMap<String, Texture>(8);
        textureNames.add("blue");
        textureNames.add("green");
        textureNames.add("red");
        textureNames.add("yellow");
        textureNames.add("cyan");
        textureNames.add("orange");
        textureNames.add("purple");

        Texture texture;
        String texturePath;

        for (String name : textureNames) {
            texturePath = textureDirectory + name + ".png";
            try {
                texture = TextureLoader.getTexture("PNG", new FileInputStream(new File(texturePath)));
            } catch (IOException e) {
                System.out.println("[TEXTURE] " + name + " failed");
                continue;
            }

            textures.put(name, texture);
        }

    }


}
