package main;

import entities.Entity;
import entities.Player;
import objects.GameObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // Screen Settings
    private final int originalTileSize = 16;
    private final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World Settings
    public int maxWorldCol = 50;
    public int maxWorldRow = 30;

    // Others
    public TileManager tileM = new TileManager(this);
    public KeyInput keyInput = new KeyInput(this);
    public Sound music = new Sound();
    public Sound soundEffect = new Sound();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public UI ui = new UI(this);
    public EventHandler eventHandler = new EventHandler(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public Thread gameThread;


    public Player player = new Player(this, keyInput);
    public GameObject[] obj = new GameObject[10];
    public Entity[] npc = new Entity[10];

    // Game State
    public GameState gameState = GameState.TITLE;

    int FPS = 60;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setDoubleBuffered(true);
        setBackground(Color.BLACK);
        addKeyListener(keyInput);
        requestFocusInWindow();
        setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setNpcs();
        assetSetter.setObjects();
        playMusic(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000.00 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        if (gameState == GameState.PLAY) {
            player.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Title screen
        if (gameState == GameState.TITLE) {
            ui.draw(g2);
        }

        // Others
        else {
            // Tiles
            tileM.draw(g2);

            // Objects
            for (GameObject gameObject : obj) {
                if (gameObject != null) {
                    gameObject.draw(g2, this);
                }
            }

            // NPC
            for (Entity ent : npc) {
                if (ent != null) {
                    ent.draw(g2);
                }
            }

            // Player
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void playSE(int i) {
        soundEffect.setFile(i);
        soundEffect.play();
    }
}
