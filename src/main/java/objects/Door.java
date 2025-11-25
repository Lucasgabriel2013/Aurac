package objects;

import utils.ImageUtils;

import java.io.InputStream;

public class Door extends GameObject {
    public Door() {
        name = "Door";

        image = ImageUtils.read("/objects/door.png");

        collision = true;
    }
}
