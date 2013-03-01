package com.rhoward.menu;

import org.lwjgl.input.Keyboard;

public class MenuInputHandler {

    private Menu menu;

    public MenuInputHandler(Menu menu) {
        this.menu = menu;
    }

    public void processInput() {

        while (Keyboard.next()) {
            switch (Keyboard.getEventKey()) {
                case Keyboard.KEY_UP:
                    if (Keyboard.getEventKeyState())
                        this.menu.moveUp();
                    break;
                case Keyboard.KEY_DOWN:
                    if (Keyboard.getEventKeyState())
                        this.menu.moveDown();
                    break;
                case Keyboard.KEY_ESCAPE:
                    if (Keyboard.getEventKeyState())
                        this.menu.onPause();
                    break;
                case Keyboard.KEY_RETURN:
                    if (Keyboard.getEventKeyState())
                        this.menu.select();
                default:
                    break;
            }
        }
    }


}
