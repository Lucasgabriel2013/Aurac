package main;

import entities.Entity;
import tile.Tile;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public Tile checkCollision(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                    entity.onCollision = true;
                }

                return gp.tileM.tiles[tileNum1];
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                    entity.onCollision = true;
                }

                return gp.tileM.tiles[tileNum1];
            case LEFT:
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                    entity.onCollision = true;
                }

                return gp.tileM.tiles[tileNum1];
            case RIGHT:
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;

                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) {
                    entity.onCollision = true;
                }

                return gp.tileM.tiles[tileNum1];
        }
        return null;
    }

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if (gp.obj[i] != null) {
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                gp.obj[i].solidArea.x += gp.obj[i].worldX;
                gp.obj[i].solidArea.y += gp.obj[i].worldY;

                switch (entity.direction) {
                    case UP -> {
                        entity.solidArea.y -= entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.onCollision = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                    }

                    case DOWN -> {
                        entity.solidArea.y += entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.onCollision = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                    }

                    case LEFT -> {
                        entity.solidArea.x -= entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.onCollision = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                    }

                    case RIGHT -> {
                        entity.solidArea.x += entity.speed;

                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.onCollision = true;
                            }

                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;

                target[i].solidArea.x += target[i].worldX;
                target[i].solidArea.y += target[i].worldY;

                switch (entity.direction) {
                    case UP -> {
                        entity.solidArea.y -= entity.speed;

                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.onCollision = true;
                            index = i;
                        }
                    }

                    case DOWN -> {
                        entity.solidArea.y += entity.speed;

                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.onCollision = true;
                            index = i;
                        }
                    }

                    case LEFT -> {
                        entity.solidArea.x -= entity.speed;

                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.onCollision = true;
                            index = i;
                        }
                    }

                    case RIGHT -> {
                        entity.solidArea.x += entity.speed;

                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.onCollision = true;
                            index = i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }

        return index;
    }
}