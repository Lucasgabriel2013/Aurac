package main.ui;

import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TitleScreen {
    GamePanel gp;
    int length;
    int x, y;
    int i;
    String text;

    public TitleScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70f));
        String text = "AuracMon";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y = gp.tileSize * 3;

        UI.shadowDraw(g2, text, x, y, 5);

        // Character image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));

        // Buttons
        y += gp.tileSize * 3;

        drawText(g2, "NEW GAME");
        drawText(g2, "LOAD GAME");
        drawText(g2, "QUIT");
    }

    private void drawText(Graphics2D g2, String text) {
        this.text = text;
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize;
        UI.shadowDraw(g2, text, x, y, -2);
        if (gp.ui.commandNum == i) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        i++;
    }

    public void input(int code) {
        if (code == KeyEvent.VK_W) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = GameState.PLAY;
            }

            if (gp.ui.commandNum == 1) {
                // Later
            }

            if (gp.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
}
