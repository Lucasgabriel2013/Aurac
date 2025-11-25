package main.ui;

import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PauseScreen {
    GamePanel gp;
    int length;
    int x, y;
    int i;
    String text;

    public PauseScreen(GamePanel gp) {
        this.gp = gp;
    }
    
    public void draw(Graphics2D g2) {
        drawText(g2, "Resume");
        drawText(g2, "Bag");
        drawText(g2, "Save");
        drawText(g2, "Quit");
    }

    private void drawText(Graphics2D g2, String text) {
        this.text = text;
        g2.setFont(g2.getFont().deriveFont(30f));

        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth() / 2;

        x = gp.screenWidth / 2 - length + gp.screenWidth / 3;
        y += gp.screenHeight / 2 - gp.screenHeight / 3;
        UI.shadowDraw(g2, this.text, x, y, 2);

        if (gp.ui.commandNum == i) {
            UI.shadowDraw(g2, ">", x - gp.tileSize, y, 2);
        }

        i++;
    }

    public void input(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = GameState.PLAY;
            }

            if (gp.ui.commandNum == 1) {
                gp.gameState = GameState.INVENTORY;
            }

            if (gp.ui.commandNum == 2) {
                // Later
            }

            if (gp.ui.commandNum == 3) {
                gp.gameState = GameState.TITLE;
            }
        }
    }
}
