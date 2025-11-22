package objects;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.InputStream;

public class Door extends GameObject {
    public InputStream place;
    public int nextX, nextY;
    public int worldX, worldY;

    public Door() {
        name = "Door";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        collision = true;
    }
}
