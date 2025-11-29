package auracmons;

import utils.ImageUtils;

import java.awt.image.BufferedImage;

public class Firemon extends Auracmon {
    private static final BufferedImage auracdexImage = ImageUtils.read("/auracmons/firemon/firemon1.png");
    private static final BufferedImage playerBattleImage = ImageUtils.read("/auracmons/firemon/firemon2.png");
    private static final BufferedImage enemyBattleImage = ImageUtils.read("/auracmons/firemon/firemon3.png");

    public Firemon() {
        type1 = AuracmonType.FIRE;
        type2 = AuracmonType.FIRE;

        name = "Firemon";
        level = 5;
        hp = 65;
        maxLife = 65;
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
