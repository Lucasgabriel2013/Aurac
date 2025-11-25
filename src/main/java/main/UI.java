package main;

import auracmons.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font pressStart;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        try {
            InputStream is = getClass().getResourceAsStream("/font/PressStart.ttf");
            pressStart = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pressStart);
        g2.setColor(Color.WHITE);

        switch (gp.gameState) {
            case TITLE -> drawTitleScreen();
            case PAUSED -> drawPauseScreen();
            case DIALOGUE -> drawDialogueScreen();
            case AURACMON_CHOICE -> drawChoiceScreen();
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

    private void drawPauseScreen() {
        String text = "PAUSED";
        g2.setFont(g2.getFont().deriveFont(30f));

        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenWidth / 2 - length / 2;
        int y = gp.screenHeight / 2;

        g2.setColor(Color.BLACK);
        g2.drawString(text, x + 2, y + 2);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }

    public void drawTitleScreen() {
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
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "LOAD GAME";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 2 - length / 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void drawDialogueScreen() {
        // Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(15f));
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

    public void drawChoiceScreen() {
        BufferedImage img;

        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        String text = "Escolha um inicial";
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        int x = gp.screenWidth / 2 - length / 2;
        int y = gp.tileSize * 3;

        // Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        // Main color
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // Inicial: Grama
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));
        text = "GRAMA";
        length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = gp.screenWidth / 4 - 35 - length / 4;
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Nome", x, y);

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
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Nome", x, y);

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
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 15f));
        y += gp.tileSize;
        g2.drawString("Nome", x, y);

        y += gp.tileSize / 2;
        img = new Firemon().auracdexImage();
        g2.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
    }
}