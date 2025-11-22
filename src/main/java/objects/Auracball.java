package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Auracball extends GameObject {
    public Auracball() {
        name = "AuracBall";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/auracball.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        collision = true;
    }
}
