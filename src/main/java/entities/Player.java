package entities;

import auracmons.Auracmon;
import items.*;
import main.*;
import tile.Tile;
import utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
    KeyInput keyInput;

    public final int screenX, screenY;

    public List<Auracmon> auracmons = new ArrayList<>();
    public List<Item> items = new ArrayList<>();

    public Player(GamePanel gp, KeyInput keyI) {
        super(gp);

        this.keyInput = keyI;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = 800;
        worldY = 850;
        speed = 6; // Padrão: 6\
        direction = Direction.DOWN;
    }

    public void getPlayerImage() {
        up1 = ImageUtils.read("/player/up1.png");
        down1 = ImageUtils.read("/player/down1.png");
        left1 = ImageUtils.read("/player/left1.png");
        right1 = ImageUtils.read("/player/right1.png");

        up2 = ImageUtils.read("/player/up2.png");
        down2 = ImageUtils.read("/player/down2.png");
        left2 = ImageUtils.read("/player/left2.png");
        right2 = ImageUtils.read("/player/right2.png");
    }

    public void update() {
        if (keyInput.downPressed || keyInput.leftPressed || keyInput.upPressed || keyInput.rightPressed) {
            if (keyInput.upPressed) {
                direction = Direction.UP;
            } else if (keyInput.downPressed) {
                direction = Direction.DOWN;
            } else if (keyInput.leftPressed) {
                direction = Direction.LEFT;
            } else {
                direction = Direction.RIGHT;
            }

            onCollision = false;
            Tile t = gp.collisionChecker.checkCollision(this);

            if (t.equals(gp.tileM.tiles[1])) {
                int random = (int) (Math.random() * 150);

                if (random == 0) {
                    gp.gameState = GameState.BATTLE;
                }
            }

            int objIndex = gp.collisionChecker.checkObject(this, true);
            interactObject(objIndex);

            int npcIndex = gp.collisionChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            gp.eventHandler.checkEvent();

            if (!onCollision) {
                switch (direction) {
                    case UP:
                        worldY -= speed;
                        break;
                    case DOWN:
                        worldY += speed;
                        break;
                    case LEFT:
                        worldX -= speed;
                        break;
                    case RIGHT:
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void interactObject(int index) {
        if (index != 999) {
            if (gp.obj[index].name.equals("AuracBall")) {
                gp.playSound(1);
                gp.obj[index] = null;
                gp.ui.showMessage("+1 AuracBall", gp.ui.g2);
                items.add(new Auracball());
            } else if (gp.obj[index].name.equals("Bag")) {
                gp.obj[index] = null;
                gp.gameState = GameState.AURACMON_CHOICE;
                gp.npc[0] = null;
            }
        }
    }

    public void interactNPC(int index) {
        if (index != 999 && gp.keyInput.dialogue) {
            gp.gameState = GameState.DIALOGUE;
            gp.npc[index].speak();
        }

        gp.keyInput.dialogue = false;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case UP -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }

            case DOWN -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }

            case LEFT -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }

            case RIGHT -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}