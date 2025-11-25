package objects;

import utils.ImageUtils;

public class Auracball extends GameObject {
    public Auracball() {
        name = "AuracBall";

        image = ImageUtils.read("/objects/auracball.png");

        collision = true;
    }
}
