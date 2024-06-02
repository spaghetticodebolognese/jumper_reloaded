package levels;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LevelManager {

    private Game game;
    private static BufferedImage levelSprite;
    private Level levelOneGround;

    public final static int CSV_WIDTH = 180;
    public final static int CSV_HEIGHT = 120;

    public static final String LVL_01_GROUND = "D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/01/180x120ground.csv";
    public static final String LVL_01_GRAVES = "D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/01/180x120graves.csv";
    public static final String LVL_01_STRUCTURES = "D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/01/180x120structures.csv";
    public static final String LVL_01_TREES01 = "D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/01/180x120trees.csv";
    public static final String LVL_01_TREES02 = "D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/01/180x120trees2.csv";

    public LevelManager(Game game){
        this.game = game;
        levelOneGround = new Level(importCsv(LVL_01_GROUND));

    }

    public static void drawTiles(Graphics g, int xLvlOffset, String fileNameCSV, String fileNameTilesheet, int tilesWidthInSpritesheet){
        levelSprite = null;

        levelSprite = LoadSave.getSpriteSheet(fileNameTilesheet);
        int TILE_SIZE = Game.TILES_DEFAULT_SIZE;
        int[][] csv = importCsv(fileNameCSV);


        for (int y = 0; y < csv.length; y++){
            for (int x = 0; x < csv[0].length; x++){
                int tileIndex = csv[y][x];
                if (tileIndex != -1) {
                    int tileX = tileIndex % tilesWidthInSpritesheet;
                    int tileY = tileIndex / tilesWidthInSpritesheet;
                    int srcX = tileX * TILE_SIZE;
                    int srcY = tileY * TILE_SIZE;
                    g.drawImage(levelSprite.getSubimage(srcX, srcY, TILE_SIZE, TILE_SIZE), (x * TILE_SIZE) - xLvlOffset, y * TILE_SIZE, null);
                }
            }
            }
    }



    public void update(){
    }


    public static int[][] importCsv(String filename) {
        int[][] level_csv = new int[CSV_HEIGHT][CSV_WIDTH];
        String[] values;

        try {
//            BufferedReader buffRead = new BufferedReader(new FileReader("/level_data/0/test_ground.csv"));
//            BufferedReader buffRead = new BufferedReader(new FileReader("C:/Users/Student/VS Studio/Java/Jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/0/test_ground.csv"));
//            BufferedReader buffRead = new BufferedReader(new FileReader("D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/0/test_ground.csv"));
            BufferedReader buffRead = new BufferedReader(new FileReader(filename));
            String line;
            int lineCounter = 0;

            while ((line = buffRead.readLine()) != null) {
                values = line.split(",");
                for (int i = 0; i < values.length; i++){
                    level_csv[lineCounter][i] = Integer.parseInt(values[i]);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return level_csv;
    }

    public Level getCurrentLevel(){         //for when there are more levels
        return levelOneGround;
    }






    }


