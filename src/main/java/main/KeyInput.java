package main;

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

        // Playing
        if (gp.gameState == GameState.PLAY) {
            // Moves
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
        // Paused
        else if (gp.gameState == GameState.PAUSED) {
            if (code == KeyEvent.VK_E) {
                gp.gameState = GameState.PLAY;
            }
        }
        // in Dialogue
        else if (gp.gameState == GameState.DIALOGUE) {
            if (code == KeyEvent.VK_F) {
                gp.gameState = GameState.PLAY;
            }
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
}
