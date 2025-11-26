package main.ui;

import auracmons.Auracmon;
import auracmons.Firemon;
import auracmons.Grassmon;
import auracmons.Watermon;
import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class ChoiceScreen {
    GamePanel gp;

    int i;
    int x, y;
    int length;
    String text;

    public ChoiceScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        BufferedImage img;

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        text = "Escolha um inicial";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y = gp.tileSize * 3;

        UI.shadowDraw(g2, text, x, y, 5);

        drawText(g2, "GRAMA", new Grassmon(), (int) ((float) gp.screenWidth / 2 - length / 2.5f));
        drawText(g2, "ÁGUA", new Watermon(), gp.screenWidth / 3 - length / 3);
        drawText(g2, "FOGO", new Firemon(), gp.screenWidth / 3 - length / 3);
    }

    public void drawText(Graphics2D g2, String text, Auracmon auracmon, int x) {
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        this.text = text;
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        this.x += x;
        y = gp.tileSize * 6;
        g2.drawString(this.text, this.x, y);
        if (gp.ui.commandNum == i) {
            g2.drawString(">", (int) (this.x - gp.tileSize / 1.5), y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString(auracmon.name, this.x, y);

        y += gp.tileSize / 2;
        BufferedImage img = auracmon.auracdexImage();
        g2.drawImage(img, this.x, y, gp.tileSize, gp.tileSize, null);

        i++;
    }

    public void input(int code) {
        if (code == KeyEvent.VK_A) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 2;
            }
        }

        if (code == KeyEvent.VK_D) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 2) {
                gp.ui.commandNum = 0;
            }
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                gp.gameState = GameState.PLAY;
                gp.player.auracmons.add(new Grassmon());
            }

            if (gp.ui.commandNum == 1) {
                gp.gameState = GameState.PLAY;
                gp.player.auracmons.add(new Watermon());
            }

            if (gp.ui.commandNum == 2) {
                gp.gameState = GameState.PLAY;
                gp.player.auracmons.add(new Firemon());
            }
        }
    }
}