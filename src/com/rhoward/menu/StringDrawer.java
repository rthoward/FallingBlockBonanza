package com.rhoward.menu;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2f;

/**
 * Created with IntelliJ IDEA.
 * User: richie
 * Date: 2/28/13
 * Time: 2:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringDrawer {

    private int blockSize;

    public StringDrawer(int blockSize) {
        this.blockSize = blockSize;
    }

    public void drawLine(String line, int posX, int posY, Color color) {
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
                    glColor3f(color.getRed(), color.getGreen(), color.getBlue());
                    glBegin(GL_QUADS);
                    glVertex2f(currentX, currentY);
                    glVertex2f(currentX + this.blockSize, currentY);
                    glVertex2f(currentX + this.blockSize, currentY + this.blockSize);
                    glVertex2f(currentX, currentY + this.blockSize);
                    glEnd();
                    currentX += blockSize;
                }
                else if (currentGridCell == '0') {
                    currentX += blockSize;
                }
                else if (currentGridCell == ',') {
                    currentY += this.blockSize;
                    currentX = startX;
                }
            }
            startX += (this.blockSize * 4);
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
            case 'R':
                return "111,101,111,101,101";
            case 'M':
                return "111,101,101,101,101";
            case 'X':
                return "101,101,010,101,101";
            case 'I':
                return "111,010,010,010,111";
            case 'T':
                return "111,010,010,010,010";
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
                return "111,101,001,000,010";
        }
    }

}
