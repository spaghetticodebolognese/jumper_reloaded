package levels;
import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class LevelManager {

    private Game game;
    private static BufferedImage levelSprite;

    public LevelManager(Game game){
        this.game = game;

    }

    public static void drawTiles(Graphics g){
        levelSprite = LoadSave.getSpriteSheet(LoadSave.TILE_SHEET_BASE_GRASS);
        int tileSize = Game.TILES_DEFAULT_SIZE;
        List<List<String>> csv = Level.importCsv();


        for (int y = 0; y < csv.size(); y++){
            for (int x = 0; x < csv.get(y).size(); x++){
                if (!csv.get(y).get(x).equals("-1")) {

                    g.drawImage(levelSprite.getSubimage((csv.get(y).get(x)), (), tileSize, tileSize), tileSize * x, tileSize * y, null);

                    //(y * 12 * tileSize) + (x * tileSize)
                            //spritesheet: max 168

                }
            }
            }


        //g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 128, 128, null);


//        for (List innerList : Level.importCsv()){       //importCsv ist die outerList
//            for (Object value : innerList){







        }
    }


