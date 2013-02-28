package com.rhoward.menu;

import com.rhoward.pit.Pit;

import static org.lwjgl.opengl.GL11.*;


public class Menu {

    enum MainMenuSelection {
        RESUME, RESET, EXIT
    }

    private Pit pit;
    private MainMenuSelection selection = MainMenuSelection.RESUME;
    StringDrawer stringDrawer = new StringDrawer(8);
    private MenuInputHandler input;
    private boolean isActive = false;
    private Color defaultColor = new Color(255.0f, 255.0f, 152.0f);
    private Color selectedColor = new Color(100.0f, 0.0f, 0.0f);

    public Menu(Pit pit) {
        this.pit = pit;
        input = new MenuInputHandler(this);
    }

    public void logic() {
        if (!this.isActive)
            return;
        this.input.processInput();
        System.out.println("selected: " + this.selection.toString());
    }

    public void draw() {

        if (!isActive)
            return;

        int posX = 200;
        int posY = 100;

        glColor3f(0.0f, 225.0f, 225.0f);
        glBegin(GL_QUADS);
            glVertex2f(0,0);
            glVertex2f(640, 0);
            glVertex2f(640, 480);
            glVertex2f(0, 480);
        glEnd();

        Color resumeColor = this.defaultColor;
        Color resetColor = this.defaultColor;
        Color exitColor = this.defaultColor;

        if (this.selection == MainMenuSelection.RESUME)
            resumeColor = this.selectedColor;
        else if (this.selection == MainMenuSelection.RESET)
            resetColor = this.selectedColor;
        else
            exitColor = this.selectedColor;

        this.stringDrawer.drawLine("RESUME", posX, posY, resumeColor);
        this.stringDrawer.drawLine("RESET ", posX, posY + 80, resetColor);
        this.stringDrawer.drawLine("EXIT  ", posX, posY + 160, exitColor);
    }

    public void moveDown() {
        if (this.selection == MainMenuSelection.RESUME)
            this.selection = MainMenuSelection.RESET;
        else if (this.selection == MainMenuSelection.RESET)
            this.selection = MainMenuSelection.EXIT;
        else
            this.selection = MainMenuSelection.RESUME;
    }

    public void moveUp() {
        if (this.selection == MainMenuSelection.RESUME)
            this.selection = MainMenuSelection.EXIT;
        else if (this.selection == MainMenuSelection.RESET)
            this.selection = MainMenuSelection.RESUME;
        else
            this.selection = MainMenuSelection.RESET;
    }

    public void select() {
        if (this.selection == MainMenuSelection.RESUME) {
            this.deactivate();
        }
        else if (this.selection == MainMenuSelection.RESET) {
            this.pit.clearGrid();
            this.pit.resetScore();
            this.deactivate();
        }
        else
            this.deactivate();

        this.pit.onUnpause();
    }

    public void activate() {
        this.isActive = true;
        this.input = new MenuInputHandler(this);
    }

    public void deactivate() {
        this.isActive = false;
        this.input = null;
    }
}
