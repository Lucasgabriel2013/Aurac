package main.ui;

import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DialogueScreen {
    GamePanel gp;

    public DialogueScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height, g2);

        g2.setFont(g2.getFont().deriveFont(15f));
        x += gp.tileSize / 2;
        y += gp.tileSize;

        for (String line : gp.ui.currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2) {
        Color color = new Color(0, 0, 0, 220);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }

    public void input(int code) {
        if (code == KeyEvent.VK_F) {
            gp.gameState = GameState.PLAY;
        }
    }
}
