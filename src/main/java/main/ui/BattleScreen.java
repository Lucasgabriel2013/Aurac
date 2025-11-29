package main.ui;

import auracmons.Auracmon;
import auracmons.Firemon;
import items.Auracball;
import items.Item;
import main.GamePanel;
import main.GameState;

import java.awt.*;
import java.awt.event.KeyEvent;

public class BattleScreen {
    GamePanel gp;
    Auracmon enemy = new Firemon();
    int x, y;
    int turn;
    Graphics2D g2;

    public BattleScreen(GamePanel gp, Graphics2D g2) {
        this.gp = gp;
        this.g2 = g2;
    }

    public void draw(Graphics2D g2) {
        Auracmon playerAuracmon = gp.player.auracmons.getFirst();

        // Fundo
        g2.setColor(new Color(70, 120, 80));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Seu auracmon
        g2.setFont(g2.getFont().deriveFont(20f));
        UI.shadowDraw(g2, playerAuracmon.name, gp.tileSize, gp.tileSize * 6, -1);

        g2.setFont(g2.getFont().deriveFont(15f));
        g2.drawString(playerAuracmon.hp + " / " + playerAuracmon.maxLife, gp.tileSize, gp.tileSize * 7);

        g2.setColor(new Color(50, 80, 50));
        g2.fillOval(gp.tileSize, (int) (gp.tileSize * 7.5), gp.tileSize * 4, gp.tileSize * 4);

        g2.drawImage(playerAuracmon.playerBattleImage(), (int) (gp.tileSize * 1.5), gp.tileSize * 8, gp.tileSize * 3, gp.tileSize * 3, null);

        // Auracmon selvagem
        g2.setFont(g2.getFont().deriveFont(20f));
        UI.shadowDraw(g2, enemy.name, gp.tileSize * 12, gp.tileSize * 7, -1);

        g2.setFont(g2.getFont().deriveFont(15f));
        g2.drawString(enemy.hp + " / " + enemy.maxLife, gp.tileSize * 12, gp.tileSize * 6);

        g2.setColor(new Color(50, 80, 50));
        g2.fillOval((int) (gp.tileSize * 11.5), gp.tileSize, gp.tileSize * 4, gp.tileSize * 4);
        g2.drawImage(enemy.enemyBattleImage(), gp.tileSize * 12, (int) (gp.tileSize * 1.5), gp.tileSize * 3, gp.tileSize * 3, null);

        // Menu
        x = gp.tileSize * 13;
        y = gp.tileSize * 9;
        g2.setColor(new Color(46, 93, 136));
        g2.fillRoundRect(x , y - gp.tileSize / 2, 90, 30, gp.tileSize, gp.tileSize);
        UI.shadowDraw(g2, "Fugir", x - gp.tileSize / 16, y, -3);

        if (gp.ui.commandNum == 1) {
            UI.shadowDraw(g2, ">", x - gp.tileSize / 2, y, -3);
        }

        g2.setColor(new Color(140, 58, 58));
        g2.fillRoundRect((int) (x - gp.tileSize * 2.80), y - gp.tileSize / 2, 90, 30, gp.tileSize, gp.tileSize);
        UI.shadowDraw(g2, "Atacar", x - gp.tileSize * 3, y, -3);

        if (gp.ui.commandNum == 0) {
            UI.shadowDraw(g2, ">", x - gp.tileSize * 3 - gp.tileSize / 2, y, -3);
        }

        x =  gp.tileSize * 13;
        y = gp.tileSize * 10;
        g2.setColor(new Color(165, 162, 8));
        g2.fillRoundRect(x , y - gp.tileSize / 2, 90, 30, gp.tileSize, gp.tileSize);
        UI.shadowDraw(g2, "Bolsa", x - gp.tileSize / 16, y, -3);

        if (gp.ui.commandNum == 3) {
            UI.shadowDraw(g2, ">", x - gp.tileSize / 2, y, -3);
        }

        g2.setColor(new Color(47, 83, 55));
        g2.setFont(g2.getFont().deriveFont(15f));
        g2.fillRoundRect((int) (x - gp.tileSize * 2.80), y - gp.tileSize / 2, 90, 30, gp.tileSize, gp.tileSize);
        UI.shadowDraw(g2, "Capturar", x - gp.tileSize * 3, y, -3);

        if (gp.ui.commandNum == 2) {
            UI.shadowDraw(g2, ">", x - gp.tileSize * 3 - gp.tileSize / 2, y, -3);
        }
    }

    public void input(int code) {
        if (code == KeyEvent.VK_ENTER) {
            if (gp.ui.commandNum == 0) {
                // Atacar
            }

            if (gp.ui.commandNum == 1) {
                if (turn == 0) {
                    int r = (int) (Math.random() * 2);

                    if (r == 0) {
                        gp.gameState = GameState.PLAY;
                        gp.ui.commandNum = 0;
                    } else {
                        gp.ui.showMessage("Não conseguiu fugir", g2);
                        turn = 1;
                    }
                }
            }

            if (gp.ui.commandNum == 2) {
                boolean hasAuracball = false;
                Item auracball = null;

                if (turn == 0) {
                    for (Item item : gp.player.items) {
                        if (item.name.equals("Auracball")) {
                            gp.player.auracmons.add(enemy);
                            gp.gameState = GameState.PLAY;
                            hasAuracball = true;
                            auracball = item;
                        }
                    }
                    gp.player.items.remove(auracball);

                    if (!hasAuracball) {
                        g2.setFont(g2.getFont().deriveFont(10f));
                        gp.ui.showMessage("Você não tem auracballs", g2);
                    }
                }
            }

            if (gp.ui.commandNum == 3) {
                // Bolsa
            }
        }

        if (code == KeyEvent.VK_A) {
            gp.ui.commandNum--;
            if (gp.ui.commandNum < 0) {
                gp.ui.commandNum = 3;
            }
        }

        if (code == KeyEvent.VK_D) {
            gp.ui.commandNum++;
            if (gp.ui.commandNum > 3) {
                gp.ui.commandNum = 0;
            }
        }
    }
}
