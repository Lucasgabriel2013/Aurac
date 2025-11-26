package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Grassmon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/grassmon1.png");;

    public Grassmon() {
        type1 = AuracmonType.GRASS;
        type2 = AuracmonType.GRASS;

        name = "Grassmon";
        description = "Um auracmon de tipo grama, é um inicial";
        level = 5;
        hp = 55;
    }

    @Override
    public BufferedImage auracdexImage() {
        return auracdexImage;
    }

    @Override
    public BufferedImage playerBattleImage() {
        return null;
    }

    @Override
    public BufferedImage enemyBattleImage() {
        return null;
    }
}
