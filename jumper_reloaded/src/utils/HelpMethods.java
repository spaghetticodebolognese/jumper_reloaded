package utils;

import main.Game;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, int width, int height, int[][] lvlData){

        if(!isSolid(x, y, lvlData)){                                     //check top left corner
            if(!isSolid(x + width, y + height, lvlData)) {        //check bottom right corner
                if(!isSolid(x + width, y, lvlData)){                //check top right
                    if(!isSolid(x, y + height, lvlData)){          //check bottom left
                        return true;
                    }
                }
            }
        }
        return false;
    }




    private static boolean isSolid(float x, float y, int[][] lvlData){
        if (x < 0 || x >= Game.GAME_WIDTH){
            return true;
        }
        if (y < 0 || y >= Game.GAME_HEIGHT){
            return true;
        }

        float xIndex = x / Game.TILES_SIZE;         //vllt nur für die pixelgenaue berechnungs methode?
        float yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        System.out.println(value);
        //return (value >= 168 || value < 0);
        if(value >= 168 || value < 0){          //tilesheet is 12x14 = 168 tiles
            return true;
        } else {
            return false;
        }

    }


}
