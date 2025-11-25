package main.ui;

import main.GamePanel;

import java.awt.*;

public class TitleScreenUI {
    GamePanel gp;

    public TitleScreenUI(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 70f));
        String text = "AuracMon";
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenWidth / 2 - length / 2;
        int y = gp.tileSize * 3;

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // Main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Character image
        x = gp.screenWidth / 2 - (gp.tileSize * 2) / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));

        text = "NEW GAME";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (gp.ui.commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }
}
