package main.ui;

import main.GamePanel;
import main.GameState;
import utils.ImageUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class InventoryScreen {
    GamePanel gp;

    int x, y;
    static int tab = 1;

    private static final BufferedImage INVENTORY_ITEM = ImageUtils.read("/objects/inventoryItem.png");

    public InventoryScreen(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(new Color(83, 69, 46));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        y = 0;
        x = gp.tileSize;

        for (int i = 0; i < 6; i++) {
            g2.drawImage(INVENTORY_ITEM, x, y, gp.tileSize * 2, gp.tileSize * 2, null);

            if (tab == 1) drawAuracmon(g2, i);
            if (tab == 2) drawItem(g2, i);
        }

        g2.setFont(g2.getFont().deriveFont(30f));
        if (tab == 1) UI.shadowDraw(g2, "Auracmons", gp.tileSize * 5, gp.tileSize, 2);
        if (tab == 2) UI.shadowDraw(g2, "Itens", gp.tileSize * 6, gp.tileSize, 2);
    }

    private void drawAuracmon(Graphics2D g2, int index) {
        if (gp.player.auracmons.size() > index) {
            if (gp.player.auracmons.get(index) != null) {
                g2.drawImage(
                        gp.player.auracmons.get(index).auracdexImage(),
                        x, y, gp.tileSize * 2, gp.tileSize * 2, null
                );
            }
        }

        y += gp.tileSize * 2;
    }

    private void drawItem(Graphics2D g2, int index) {
        if (gp.player.items.size() > index) {
            if (gp.player.items.get(index) != null) {
                g2.drawImage(
                        gp.player.items.get(index).image,
                        x, y, gp.tileSize * 2, gp.tileSize * 2, null
                );
            }
        }

        y += gp.tileSize * 2;
    }

    public void input(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = GameState.PAUSED;
            gp.ui.commandNum = 0;
        }

        // Mudar de inventario
        if (code == KeyEvent.VK_A) {
            tab--;
            if (tab == 0) {
                tab = 2;
            }
        }

        if (code == KeyEvent.VK_D) {
            tab++;
            if (tab == 3) {
                tab = 1;
            }
        }
    }
}
