package entities;

import main.Direction;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OldMan extends Entity {

    public OldMan(GamePanel gp) {
        super(gp);

        direction = Direction.DOWN;
        getImage();
        setDialogues();
    }

    public void getImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/npcs/oldMan.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setDialogues() {
        dialogue[0] = "Escolha um inicial antes de passar, \nsó irei te deixar passar com um.";
        dialogue[1] = "Siga o caminho e pegue a bolsa";
    }
}
