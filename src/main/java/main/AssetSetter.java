package main;

import entities.OldMan;
import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObjects() {
        gp.obj[0] = new Auracball();
        gp.obj[0].worldX = 8 * gp.tileSize;
        gp.obj[0].worldY = 42 * gp.tileSize;
        gp.obj[0].collision = true;

//        gp.obj[1] = new Door();
//        gp.obj[1].worldX = 37 * gp.tileSize;
//        gp.obj[1].worldY = 22 * gp.tileSize;
//        gp.obj[1].collision = true;
//        ((Door) gp.obj[1]).place = getClass().getResourceAsStream("/maps/laboratory.txt");
//        ((Door) gp.obj[1]).nextX = 7;
//        ((Door) gp.obj[1]).nextY = 3;
    }

    public void setNpcs() {
        gp.npc[0] = new OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 8;
        gp.npc[0].worldY = gp.tileSize * 8;
    }
}
