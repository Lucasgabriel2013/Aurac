package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public static BufferedImage dirt;
    public static BufferedImage grass;
    public static BufferedImage water;
    public static BufferedImage quartz;

    static {
        try {
            dirt = ImageIO.read(TileManager.class.getResourceAsStream("/tiles/dirt.png"));
            grass = ImageIO.read(TileManager.class.getResourceAsStream("/tiles/grass.png"));
            water = ImageIO.read(TileManager.class.getResourceAsStream("/tiles/water.png"));
            quartz = ImageIO.read(TileManager.class.getResourceAsStream("/tiles/quartz.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[5];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        tiles[0] = new Tile();
        tiles[0].image = dirt;

        tiles[1] = new Tile();
        tiles[1].image = grass;

        tiles[2] = new Tile();
        tiles[2].image = water;
        tiles[2].collision = true;

        tiles[3] = new Tile();
        tiles[3].image = quartz;
        tiles[3].collision = true;
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (Exception ignored) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                    && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                    && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                    && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
