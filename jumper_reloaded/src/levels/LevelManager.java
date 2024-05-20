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
                int tileIndex = Integer.parseInt(csv.get(y).get(x));
                if (tileIndex != -1) {
                    int tileX = tileIndex % 12;
                    int tileY = tileIndex / 12;
                    int srcX = tileX * tileSize;
                    int srcY = tileY * tileSize;

                    g.drawImage(levelSprite.getSubimage(srcX, srcY, tileSize, tileSize), x * tileSize, y * tileSize, null);


                }
            }
            }








        //g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 128, 128, null);


//        for (List innerList : Level.importCsv()){       //importCsv ist die outerList
//            for (Object value : innerList){







        }
    }


