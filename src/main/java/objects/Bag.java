package objects;

import utils.ImageUtils;
public class Bag extends GameObject {
    public Bag() {
        name = "Bag";

        image = ImageUtils.read("/objects/bag.png");

        collision = true;
    }
}
