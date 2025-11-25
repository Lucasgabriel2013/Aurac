package main.ui;

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

    public ChoiceScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        BufferedImage img;

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        String text = "Escolha um inicial";
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenWidth / 2 - length / 2;
        int y = gp.tileSize * 3;

        UI.shadowDraw(g2, text, x, y, 5);

        // Inicial: Grama
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        text = "GRAMA";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 4 - 35 - length / 4;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Grassmon", x, y);

        y += gp.tileSize / 2;

        img = new Grassmon().auracdexImage();
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);

        // Inicial: Água
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        y = gp.tileSize * 6;
        text = "ÁGUA";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x += gp.screenWidth / 3 - length / 3;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Watermon", x, y);

        y += gp.tileSize / 2;
        img = new Watermon().auracdexImage();
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);

        // Inicial: Fogo
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        text = "FOGO";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x += gp.screenWidth / 3 - length / 3;
        y = gp.tileSize * 6;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Firemon", x, y);

        y += gp.tileSize / 2;
        img = new Firemon().auracdexImage();
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
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
            }

            if (gp.ui.commandNum == 1) {
                gp.gameState = GameState.PLAY;
            }

            if (gp.ui.commandNum == 2) {
                gp.gameState = GameState.PLAY;
            }
        }
    }
}