package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Watermon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/watermon/watermon1.png");
    private static final BufferedImage playerBattleImage = ImageUtils.read("/auracmons/watermon/watermon2.png");
    private static final BufferedImage enemyBattleImage = ImageUtils.read("/auracmons/watermon/watermon3.png");

    public Watermon() {
        type1 = AuracmonType.WATER;
        type2 = AuracmonType.WATER;

        name = "Watermon";
        level = 5;
        hp = 55;
        maxLife = 55;
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
