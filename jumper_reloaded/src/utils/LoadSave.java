package utils;

import levels.LevelManager;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_SPRITESHEET = "crow Animations/crow_spritesheet.png";
    public static final String ZOMBIE_SPRITESHEET = "Pale Moon/Creatures/Zombie/zombie_spritesheet.png";
    public static final String BACKGROUND = "the dark forest/Background/Night.png";
    public static final String TILE_SHEET_BASE_GRASS = "Pale Moon/Image Sheets/base_grass_tiles.png" ;


    public static BufferedImage getSpriteSheet(String fileName){
        BufferedImage image = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + fileName);
        if (is != null){
            try {
                image = ImageIO.read(is);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Error: Could not find resource: " + fileName);
        }
        return image;
    }

//    public static int[][] getLevelData(){
//        //int[][] lvlData = new int[LevelManager.CSV_HEIGHT][LevelManager.CSV_WIDTH];
//        return LevelManager.importCsv();
//    }


}
