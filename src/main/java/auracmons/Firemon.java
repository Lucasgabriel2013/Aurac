package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Firemon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/firemon1.png");;

    public Firemon() {
        type1 = AuracmonType.FIRE;
        type2 = AuracmonType.FIRE;

        name = "Firemon";
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
