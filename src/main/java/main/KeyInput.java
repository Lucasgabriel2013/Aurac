package main;

import main.ui.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed, dialogue;

    public KeyInput(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        switch (gp.gameState) {
            case PLAY -> playing(code);
            case TITLE -> new TitleScreen(gp).input(code);
            case PAUSED -> new PauseScreen(gp).input(code);
            case INVENTORY -> new InventoryScreen(gp).input(code);
            case DIALOGUE -> new DialogueScreen(gp).input(code);
            case AURACMON_CHOICE -> new ChoiceScreen(gp).input(code);
            case BATTLE -> new BattleScreen(gp, gp.ui.g2).input(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    public void playing(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }

        if (code == KeyEvent.VK_E) {
            gp.gameState = GameState.PAUSED;
        }

        if (code == KeyEvent.VK_F) {
            dialogue = true;
        }
    }
}
