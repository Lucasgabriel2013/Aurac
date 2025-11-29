package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Grassmon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/grassmon/grassmon1.png");
    private static final BufferedImage playerBattleImage = ImageUtils.read("/auracmons/grassmon/grassmon2.png");
    private static final BufferedImage enemyBattleImage = ImageUtils.read("/auracmons/grassmon/grassmon3.png");

    public Grassmon() {
        type1 = AuracmonType.GRASS;
        type2 = AuracmonType.GRASS;

        name = "Grassmon";
        description = "Um auracmon de tipo grama, é um inicial";
        level = 5;
        hp = 60;
        maxLife = 60;
    }

    @Override
    public BufferedImage auracdexImage() {
        return auracdexImage;
    }

    @Override
    public BufferedImage playerBattleImage() {
        return playerBattleImage;
    }

    @Override
    public BufferedImage enemyBattleImage() {
        return enemyBattleImage;
    }
}
