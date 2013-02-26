package com.rhoward;

import static org.lwjgl.opengl.GL11.*;


public class ScoreEntity {

    private final float BLOCK_SIZE = 8;
    private int score;
    private int x, y;
    private int level = 1;
    private boolean paused = false;
    private boolean lost = false;

    public ScoreEntity(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public int getScore() {
        return this.score;
    }


    public void incrementScore(int increment) {
        this.score += increment;
    }

    public void draw() {
        drawLine(Integer.toString(this.score), this.x, this.y);
        drawLine("LEVEL " + Integer.toString(this.level), this.x, this.y + 60);

        if (this.paused && !this.lost)
            drawLine("-- PAUSED --", 90, 50);
        if (this.lost)
            drawLine("YOU LOSE", 90, 50);
    }

    private void drawLine(String line, int posX, int posY) {
        String drawString = line;
        int startX = posX;
        int startY = posY;
        int currentX = startX;
        int currentY = startY;
        String currentGrid;
        char currentGridCell;

        while (drawString.length() < 6)
            drawString = "0" + drawString;

        for (int i = 0; i < drawString.length(); i++) {

            currentGrid = getGrid(drawString.charAt(i));
            currentX = startX;
            currentY = startY;

            for (int j = 0; j < currentGrid.length(); j++) {
                currentGridCell = currentGrid.charAt(j);


                if (currentGridCell == '1') {
                    glColor3f(255.0f, 255.0f, 152.0f);
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
            case ' ':
                return "000,000,000,000,000";
            case '-':
                return "000,000,111,000,000";
            case 'P':
                return "111,101,111,100,100";
            case 'A':
                return "010,010,101,111,101";
            case 'U':
                return "101,101,101,101,111";
            case 'S':
                return "111,100,111,001,111";
            case 'D':
                return "111,101,101,101,111";
            case 'Y':
                return "101,101,010,010,010";
            case 'O':
                return "111,101,101,101,111";
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
                return "111,100,111,001,111";
            case '6':
                return "111,100,111,101,111";
            case '7':
                return "111,001,001,001,001";
            case '8':
                return "111,101,111,101,111";
            case '9':
                return "111,101,111,001,001";
            case 'L':
                return "100,100,100,100,111";
            case 'E':
                return "111,100,111,100,111";
            case 'V':
                return "101,101,101,010,010";
            default:
                return "101,101,111,001,001";
        }
    }
}
