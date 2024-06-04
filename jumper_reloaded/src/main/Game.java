package main;

import entities.Enemy;
import entities.Player;
import levels.LevelManager;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200; //200
    private Player player;
    private Enemy zombie1;
    private Enemy zombie2;
    private Enemy zombie3;
    private Enemy zombie4;
    private Enemy zombie5;
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 1.0f;
    public final static int TILES_IN_WIDTH = 40;       //standard: 26
    public final static int TILES_IN_HEIGHT = 25;       //standard: 14
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;


    //for X scrolling the level
    private int xLvlOffset;
    private int leftBorder = (int) (0.35 * GAME_WIDTH);
    private int rightBorder = (int) (0.65 * GAME_WIDTH);
    private int lvlTilesWide = LevelManager.importCsv(LevelManager.LVL_01_GROUND)[0].length;
    private int maxTilesOffsetX = lvlTilesWide - TILES_IN_WIDTH;                //amount of tiles - what we can see
    private int maxLvlOffsetX = maxTilesOffsetX * TILES_SIZE;

    //for X scrolling the level
    private int yLvlOffset;
    private int aboveBorder = (int) (0.2 * GAME_HEIGHT);
    private int belowBorder = (int) (0.8 * GAME_HEIGHT);
    private int lvlTilesHigh = LevelManager.importCsv(LevelManager.LVL_01_GROUND).length;
    private int maxTilesOffsetY = lvlTilesHigh - TILES_IN_HEIGHT;
    private int maxLvlOffsetY = maxTilesOffsetY * TILES_SIZE;



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
        player.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));

        zombie1 = new Enemy(3668, 585, 128, 128);
        zombie1.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));
        zombie1.setPlayer(player);

//        zombie2 = new Enemy(3800, 585, 128, 128);
//        zombie2.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));
//        zombie2.setPlayer(player);
//
//        zombie3 = new Enemy(4000, 585, 128, 128);
//        zombie3.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));
//        zombie3.setPlayer(player);
//
//        zombie4 = new Enemy(4330, 585, 128, 128);
//        zombie4.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));
//        zombie4.setPlayer(player);
//
//        zombie5 = new Enemy(5542, 585, 128, 128);
//        zombie5.loadLvlData(LevelManager.importCsv(LevelManager.LVL_01_GROUND));
//        zombie5.setPlayer(player);
    }


    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }



    public void update(){
        checkCloseToBorder();
        player.update();
        zombie1.update();
//        zombie2.update();
//        zombie3.update();
//        zombie4.update();
//        zombie5.update();
        levelManager.update();
    }

    private void checkCloseToBorder() {
        //X Axis
        int playerX = (int) player.getHitbox().x;
        int diffX = playerX - xLvlOffset;

        if(diffX > rightBorder){
            xLvlOffset += diffX - rightBorder;
        } else if(diffX < leftBorder){
            xLvlOffset += diffX - leftBorder;
        }

        if(xLvlOffset > maxLvlOffsetX){
            xLvlOffset = maxLvlOffsetX;
        } else if (xLvlOffset < 0){
            xLvlOffset = 0;
        }

        //Y Axis
        int playerY = (int) player.getHitbox().y;
        int diffY = playerY - yLvlOffset;

        if(diffY > belowBorder){
            yLvlOffset += diffY - belowBorder;
        } else if(diffY < aboveBorder){
            yLvlOffset += diffY - aboveBorder;
        }

        if(yLvlOffset > maxLvlOffsetY){
            yLvlOffset = maxLvlOffsetY;
        } else if (yLvlOffset < 0){
            yLvlOffset = 0;
        }


    }

    public void render(Graphics g){
        BufferedImage bg = LoadSave.getSpriteSheet(LoadSave.BACKGROUND);
//        if(player.getHitbox().y <600){
        g.drawImage(bg, 0,0, GAME_WIDTH, GAME_HEIGHT, null);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_GROUND, LoadSave.TILE_SHEET_BASE_GRASS, 12);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_GRAVES, LoadSave.TILE_SHEET_CEMETERY, 10);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_STRUCTURES, LoadSave.TILE_SHEET_STRUCTURE, 6);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_STRUCTURES02, LoadSave.TILE_SHEET_STRUCTURE, 6);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_STRUCTURES_LADDER, LoadSave.TILE_SHEET_STRUCTURE, 6);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_TREES01, LoadSave.TILE_SHEET_NATURE, 20);
        LevelManager.drawTiles(g, xLvlOffset, yLvlOffset, LevelManager.LVL_01_TREES02, LoadSave.TILE_SHEET_NATURE, 20);
        zombie1.render(g, xLvlOffset, yLvlOffset);
//        zombie2.render(g, xLvlOffset, yLvlOffset);
//        zombie3.render(g, xLvlOffset, yLvlOffset);
//        zombie4.render(g, xLvlOffset, yLvlOffset);
//        zombie5.render(g, xLvlOffset, yLvlOffset);
        player.render(g, xLvlOffset, yLvlOffset);
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


        while(true){
//        System.out.println("X: " + player.getHitbox().x + " Y:" + player.getHitbox().y);


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
//                System.out.println("FPS: " + frames + " | UPS: " + updates);
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
