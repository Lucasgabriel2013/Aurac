package entities;

import main.Direction;
import main.GamePanel;
import utils.ImageUtils;

public class OldMan extends Entity {

    public OldMan(GamePanel gp) {
        super(gp);

        direction = Direction.DOWN;
        down1 = ImageUtils.read("/npcs/oldMan.png");
        setDialogues();
    }

    public void setDialogues() {
        dialogue[0] = """
                Escolha um inicial antes de passar,
                só irei te deixar passar com um.
                """;
        dialogue[1] = "Siga o caminho e pegue a bolsa";
    }
}
