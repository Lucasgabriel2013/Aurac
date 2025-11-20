package main;

import entities.Entity;
import tile.TileManager;

public class GrassChecker {
    GamePanel gp;

    public GrassChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tile1, tile2;

        tile1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
        tile2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

        if (gp.tileM.tiles[tile1].image == TileManager.grass || gp.tileM.tiles[tile2].image == TileManager.grass) {
            System.out.println("sigma");
        }
    }
}
