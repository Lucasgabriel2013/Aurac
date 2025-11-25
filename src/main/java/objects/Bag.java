package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Bag extends GameObject {
    public Bag() {
        name = "Bag";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/bag.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        collision = true;
    }
}
