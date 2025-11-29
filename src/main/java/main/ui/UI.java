package main.ui;

import main.GamePanel;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class UI {
    GamePanel gp;
    public Graphics2D g2;
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

    public void showMessage(String text, Graphics2D g2) {
        this.g2 = g2;
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(pressStart);
        g2.setColor(Color.WHITE);

        switch (gp.gameState) {
            case TITLE -> new TitleScreen(gp).draw(g2);
            case PAUSED -> new PauseScreen(gp).draw(g2);
            case INVENTORY -> new InventoryScreen(gp).draw(g2);
            case DIALOGUE -> new DialogueScreen(gp).draw(g2);
            case AURACMON_CHOICE -> new ChoiceScreen(gp).draw(g2);
            case BATTLE -> new BattleScreen(gp, g2).draw(g2);
        }

        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(20f));
            int length = (int) g2.getFontMetrics().getStringBounds(message, g2).getWidth();

            int x = gp.screenWidth / 2 - length / 2;

            g2.drawString(message, x, 50);

            messageCounter++;

            if (messageCounter > 120) {
                messageCounter = 0;
                messageOn = false;
            }
        }
    }

    public static void shadowDraw(Graphics2D g2, String text, int x, int y, int shadowSize) {
        g2.setColor(Color.BLACK);
        g2.drawString(text, x + shadowSize, y + shadowSize);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }
}