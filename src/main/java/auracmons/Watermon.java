package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Watermon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/watermon1.png");;

    public Watermon() {
        type1 = AuracmonType.WATER;
        type2 = AuracmonType.WATER;

        name = "Watermon";
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
