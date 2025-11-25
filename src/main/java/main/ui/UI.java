package main.ui;

import main.GamePanel;

import java.awt.*;
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
            case TITLE -> new TitleScreenUI(gp).draw(g2);
            case PAUSED -> drawPauseScreen();
            case DIALOGUE -> new DialogueScreenUI(gp).draw(g2);
            case AURACMON_CHOICE -> new ChoiceScreenUI(gp).draw(g2);
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
}