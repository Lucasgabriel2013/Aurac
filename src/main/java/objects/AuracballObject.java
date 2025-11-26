package objects;

import utils.ImageUtils;

public class AuracballObject extends GameObject {
    public AuracballObject() {
        name = "AuracBall";

        image = ImageUtils.read("/objects/auracball.png");

        collision = true;
    }
}
