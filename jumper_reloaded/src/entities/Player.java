package entities;

import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;

public class Player extends Entity{

    private BufferedImage spriteSheet;
    private BufferedImage[][] animations;

    private int aniTick, aniIndex, aniSpeed = 30;
    private boolean left, right;
    private boolean moving = false;
    private boolean attacking = false;
    private boolean jumping = false;
    private float playerSpeed = 2.0f;
    private int playerAction = IDLE;

    //for collision detection
    private int[][] lvlData;

    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimations();
    }

    private void loadAnimations(){


            spriteSheet = LoadSave.getSpriteSheet(LoadSave.PLAYER_SPRITESHEET);

            animations = new BufferedImage[4][6];   // 4 animations, the longest one is 6 frames
            for (int j = 0; j < animations.length; j++){
                for (int i = 0; i < animations[j].length; i++){
                    animations[j][i] = spriteSheet.getSubimage(i * 64, j * 64 , 64 , 64);
                }
            }

    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }


    public void update(){
        updatePos();
        updateHitbox();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 128, 128, null);
        drawHitBox(g);
    }


    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
                jumping = false;
            }
        }
    }


    private void setAnimation(){
        int startAni = playerAction;

        if (moving) {
            playerAction = WALK;
        } else {
            playerAction = IDLE;
        }

        if (attacking){
            playerAction = ATTACK;
        }

        if (jumping){
            playerAction = JUMP;
        }

        if (startAni != playerAction){
             resetAniTick();
        }

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos(){

        moving = false;

        if (left && !right){
            x -= playerSpeed;
            moving = true;
        } else if (right && !left){
            x += playerSpeed;
            moving = true;
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
    }

}
