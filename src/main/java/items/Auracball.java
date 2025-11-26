package items;

import utils.ImageUtils;

public class Auracball extends Item {
    public Auracball() {
        image = ImageUtils.read("/objects/auracball.png");
        name = "Auracball";
        description = "É usado para pegar auracmons";
    }
}
