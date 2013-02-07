package com.rhoward;

import static org.lwjgl.opengl.GL11.*;


public class ScoreEntity {

    private final float BLOCK_SIZE = 8;

    private int score;
    private int x, y;

    public ScoreEntity(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void incrementScore(int increment) {
        this.score += increment;
    }

    public void draw() {
        String scoreString = Integer.toString(this.score);
        int startX = this.x;
        int startY = this.y;
        int currentX = startX;
        int currentY = startY;
        String currentGrid;
        char currentGridCell;

        while (scoreString.length() < 6)
            scoreString = "0" + scoreString;

        for (int i = 0; i < scoreString.length(); i++) {
            currentGrid = getGrid(scoreString.charAt(i));
            currentX = startX;
            currentY = startY;

            for (int j = 0; j < currentGrid.length(); j++) {
                currentGridCell = currentGrid.charAt(j);


                if (currentGridCell == '1') {
                    glBegin(GL_QUADS);
                        glVertex2f(currentX, currentY);
                        glVertex2f(currentX + this.BLOCK_SIZE, currentY);
                        glVertex2f(currentX + this.BLOCK_SIZE, currentY + this.BLOCK_SIZE);
                        glVertex2f(currentX, currentY + this.BLOCK_SIZE);
                    glEnd();
                    currentX += BLOCK_SIZE;
                }
                else if (currentGridCell == '0') {
                    currentX += BLOCK_SIZE;
                }
                else if (currentGridCell == ',') {
                    currentY += this.BLOCK_SIZE;
                    currentX = startX;
                }
            }
            startX += (this.BLOCK_SIZE * 4);
        }
    }

    private String getGrid(char num) {
        switch (num) {
            case '0':
                return "111,101,101,101,111";
            case '1':
                return "110,010,010,010,111";
            case '2':
                return "111,001,010,100,111";
            case '3':
                return "111,001,111,001,111";
            case '4':
                return "101,101,111,001,001";
            case '5':
                return "101,101,111,001,001";
            case '6':
                return "101,101,111,001,001";
            case '7':
                return "101,101,111,001,001";
            case '8':
                return "101,101,111,001,001";
            case '9':
                return "101,101,111,001,001";
            default:
                return "101,101,111,001,001";
        }
    }
}
