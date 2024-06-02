package utils;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData){

        if(!isSolid(x, y, lvlData)){                                     //check top left corner
            if(!isSolid(x + width, y + height, lvlData)) {        //check bottom right corner
                if(!isSolid(x + width, y, lvlData)){                //check top right
                    if(!isSolid(x, y + height, lvlData)){          //check bottom left
                        if(!isSolid(x, y + height / 2, lvlData)){           //check left middle point
                            if(!isSolid(x + width, y + height / 2, lvlData)) {      //check right middle point
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }




    private static boolean isSolid(float x, float y, int[][] lvlData){
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        int maxHeight = lvlData.length * Game.TILES_SIZE;

        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        if (x < 0 || x >= maxWidth){
            return true;
        }
        if (y < 0 || y >= maxHeight){
            return true;
        }


        int value = lvlData[(int) yIndex][(int) xIndex];
        return (value >-1);
    }


    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int)(hitbox.x / Game.TILES_SIZE);
        if(xSpeed > 0){
            //Right
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset -1;       //-1 so edge of hitboxes dont overlap
        } else {
            //Left
            return currentTile * Game.TILES_SIZE;
        }
    }

    public static float getEntityYPosUnderRootOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
        int currentTile = (int)(hitbox.y / Game.TILES_SIZE);
        if(airSpeed > 0){
            //Falling
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height / 2.5);        // hitbox.height / 2.5
            return tileYPos + yOffset -1;
        } else {
            //jumping
            return currentTile * Game.TILES_SIZE;
        }
    }


    public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData){
        //check pixel below bottomleft and bottomright
        if(!isSolid(hitbox.x, hitbox.y + hitbox.height + 1 , lvlData)){
            if(!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)){
                return false;
            }
        }
        return true;

    }


}
