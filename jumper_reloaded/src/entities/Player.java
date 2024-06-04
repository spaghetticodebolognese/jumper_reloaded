package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;

public class Player extends Entity{

    private BufferedImage spriteSheet;
    private BufferedImage[][] animations;

    private int aniTick, aniIndex, aniSpeed = 30;
    private boolean left = false;
    private boolean right = false;
    private boolean lookingLeft = false;
    private boolean moving = false;
    public boolean attacking = false;
    private boolean jumping = false;
    private float playerSpeed = 2.0f;
    private int playerAction = IDLE;
    public int[][] lvlData;             //for collision detection
    private float xDrawOffset = 24 * Game.SCALE;        //24,19 = free space in the sprite
    private float yDrawOffset = 19 * Game.SCALE;

    // Jumping / Grafity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -3.5f * Game.SCALE;      //2.25 original
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;


    public Player(float x, float y, int width, int height){
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 26 * Game.SCALE, 54 * Game.SCALE);
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
        if (!isEntityOnFloor(hitbox, lvlData)){
            inAir = true;
        }
    }


    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }

    public void render(Graphics g, int xLvlOffset, int yLvlOffset){
        if(left){
            lookingLeft = true;
        } else if(right){
            lookingLeft = false;
        }

        if(lookingLeft){
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset * 2) - xLvlOffset  + width, (int) (hitbox.y - yDrawOffset * 2) - yLvlOffset, - width, height, null);
        } else {
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset * 2) - xLvlOffset, (int) (hitbox.y - yDrawOffset * 2) - yLvlOffset, width, height, null);
        }
//        drawHitBox(g, xLvlOffset, yLvlOffset);
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

        if (inAir){
            playerAction = JUMP;
        }

        if (attacking){
            playerAction = ATTACK;
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

        if(jumping){
            jump();
        }

        float xSpeed = 0;

        if(!inAir){
            if(!left && !right || right && left){
                return;
            }
        }

        if (left){
            xSpeed -= playerSpeed;
        }
        if (right){
            xSpeed += playerSpeed;
        }


        if(!inAir){
            if(!isEntityOnFloor(hitbox, lvlData)){
                inAir = true;
            }
        }

        if(inAir) {
            if(canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = getEntityYPosUnderRootOrAboveFloor(hitbox, airSpeed);
                if(airSpeed > 0){
                    resetInAir();                       //if hitting the floor
                } else {
                    airSpeed = fallSpeedAfterCollision;     //if hitting the roof
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }
        moving = true;
    }




    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
            hitbox.x += xSpeed;
        } else {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
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
