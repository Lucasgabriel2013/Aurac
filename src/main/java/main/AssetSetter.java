package main;

import entities.OldMan;
import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new OBJ_Auracball();
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 42 * gp.tileSize;
        gp.obj[0].collision = true;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 8 * gp.tileSize;
        gp.obj[1].worldY = 42 * gp.tileSize;
        gp.obj[1].collision = true;
    }

    public void setNpcs() {
        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 8;
        gp.npc[0].worldY = gp.tileSize * 8;
    }
}
