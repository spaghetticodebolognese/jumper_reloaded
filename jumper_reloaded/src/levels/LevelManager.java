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
    private Level levelZero;

    public final static int CSV_WIDTH = 150;
    public final static int CSV_HEIGHT = 16;

    public LevelManager(Game game){
        this.game = game;
        levelZero = new Level(importCsv());

    }

    public static void drawTiles(Graphics g){
        levelSprite = null;

        levelSprite = LoadSave.getSpriteSheet(LoadSave.TILE_SHEET_BASE_GRASS);
        int TILE_SIZE = Game.TILES_DEFAULT_SIZE;
        int[][] csv = importCsv();


        for (int y = 0; y < csv.length; y++){
            for (int x = 0; x < csv[y].length; x++){
                int tileIndex = csv[y][x];
                if (tileIndex != -1) {
                    int tileX = tileIndex % 12;
                    int tileY = tileIndex / 12;
                    int srcX = tileX * TILE_SIZE;
                    int srcY = tileY * TILE_SIZE;

                    g.drawImage(levelSprite.getSubimage(srcX, srcY, TILE_SIZE, TILE_SIZE), x * TILE_SIZE, y * TILE_SIZE, null);
                }
            }
            }
    }



        public void update(){

        }


    public static int[][] importCsv() {

        int[][] level_0_csv = new int[CSV_HEIGHT][CSV_WIDTH];
        String[] values;

        try {
//            BufferedReader buffRead = new BufferedReader(new FileReader("C:/Users/Student/VS Studio/Java/Jumper_reloaded/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/0/test_ground.csv"));
            BufferedReader buffRead = new BufferedReader(new FileReader("D:/Kiwi/Coding/jumper_reloaded/jumper_reloaded/jumper_reloaded/resources/level_data/0/test_ground.csv"));
            String line;
            int lineCounter = 0;

            while ((line = buffRead.readLine()) != null) {
                values = line.split(",");
                for (int i = 0; i < values.length; i++){
                    level_0_csv[lineCounter][i] = Integer.parseInt(values[i]);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return level_0_csv;
    }

    public Level getCurrentLevel(){
        return levelZero;
    }






    }


