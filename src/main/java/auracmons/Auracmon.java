package auracmons;

import java.awt.image.BufferedImage;

public abstract class Auracmon {
    public AuracmonType type1, type2;
    public String name;
    public int hp, maxLife;
    public int level;

    public abstract BufferedImage auracdexImage();
    public abstract BufferedImage playerBattleImage();
    public abstract BufferedImage enemyBattleImage();
}
