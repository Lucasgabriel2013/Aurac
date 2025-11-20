package main;

import java.awt.*;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font = new Font("Arial", Font.PLAIN, 40);
    public boolean messageOn = false;
    public String message = "";
    int messageCounter;
    public String currentDialogue = "";

    public UI(GamePanel gp) {
        this.gp = gp;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(font);
        g2.setColor(Color.WHITE);

        // Pause state
        if (gp.gameState == GameState.PAUSED) {
            String text = "PAUSED";

            int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            int x = gp.screenWidth / 2 - length / 2;
            int y = gp.screenHeight / 2;

            g2.drawString(text, x, y);
        }
        // Dialogue state
        if (gp.gameState == GameState.DIALOGUE) {
            drawDialogueScreen();
        }

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30f));
            g2.drawString(message, gp.tileSize * 5.2f, 50);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    public void drawDialogueScreen() {
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(32f));
        x += gp.tileSize / 2;
        y += gp.tileSize;

        for (String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 220);
        g2.setColor(color);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        color = new Color(255, 255, 255);
        g2.setColor(color);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}