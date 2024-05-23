package main;

import entities.Player;
import entities.Player.*;
import levels.Level;
import levels.LevelManager;
import levels.LevelManager.*;
import utils.HelpMethods;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;


public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200; //200
    private Player player;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 42;       //standard: 26
    public final static int TILES_IN_HEIGHT = 14;       //standard: 14
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses(){
        levelManager = new LevelManager(this);
        player = new Player(200, 295, 128, 128);
        player.loadLvlData(LevelManager.importCsv());
    }


    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    public void update(){
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        BufferedImage bg = LoadSave.getSpriteSheet(LoadSave.BACKGROUND);
        g.drawImage(bg, 0,0, GAME_WIDTH, GAME_HEIGHT, null);
        LevelManager.drawTiles(g);
        player.render(g);
    }


    @Override
    public void run() {     //gameloop

        double timePerFrame = 1_000_000_000.0 / FPS_SET; //how long (in nanoseconds) each frame will last
        double timePerUpdate = 1_000_000_000.0 / UPS_SET;
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        long previousTime = System.nanoTime();

        double deltaU = 0;      //updates
        double deltaF = 0;      //frames


//        String widthTest = "-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1";
//        System.out.println("länge: " + widthTest.split(",").length);





        while(true){
            //player.testShowMeIfTileIsSolid(player.lvlData);
            //System.out.println(HelpMethods.canMoveHere(player.x, player.y, player.width, player.height, player.lvlData));



            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }


            //FPS Counter
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }
}
