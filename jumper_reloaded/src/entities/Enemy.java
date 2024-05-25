package entities;

import main.Game;
import utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;


import static utils.Constants.ZombieConstants.*;
import static utils.HelpMethods.canMoveHere;
import static utils.HelpMethods.isEntityOnFloor;

public class Enemy extends Entity{

    private BufferedImage spriteSheet;
    private BufferedImage[][] animations;
    private Player player;              //Referenz auf die Playerinstanz für attacking

    private int aniTick, aniIndex, aniSpeed = 30;
    public int[][] lvlData;
    private boolean inAir = false;
    private boolean attacking = false;
    private boolean disappearing = false;
    private boolean dying = false;
    private boolean lookingLeft = false;
    private boolean moving = false;
    private boolean spawned = false;
    private boolean visible = false;
    private float enemySpeed = 0.5f;


    private int zombieAction = IDLE;

    private float xDrawOffset = 20 * Game.SCALE;        //30,24 = free space in the sprite
    private float yDrawOffset = 30 * Game.SCALE;



    public Enemy(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, 40 * Game.SCALE, 60 * Game.SCALE);
    }           //26/54

    public void setPlayer(Player player){
        this.player = player;
    }

    private void loadAnimations() {
        spriteSheet = LoadSave.getSpriteSheet(LoadSave.ZOMBIE_SPRITESHEET);
        animations = new BufferedImage[8][12];
        for (int j = 0; j < animations.length; j++){
            for (int i = 0; i < animations[j].length; i++){
                animations[j][i] = spriteSheet.getSubimage(i * 64, j * 64 , 64 , 64);
            }
        }
    }

    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
//        if (!isEntityOnFloor(hitbox, lvlData)){
//            inAir = true;
//        }
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }


    public void render(Graphics g){
        if(visible){
            if (!dying && !disappearing) {
                lookingLeft = player.getHitbox().x < hitbox.x;
            }
            if (lookingLeft) {
                g.drawImage(animations[zombieAction][aniIndex], (int) (hitbox.x - xDrawOffset * 2) + width, (int) (hitbox.y - yDrawOffset * 2), -width, height, null);
            } else {
                g.drawImage(animations[zombieAction][aniIndex], (int) (hitbox.x - xDrawOffset * 2), (int) (hitbox.y - yDrawOffset * 2), width, height, null);
            }
        }

        //drawHitBox(g);
    }

    private void updateAnimationTick() {

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(zombieAction)) {
                aniIndex = 0;
                if (dying) {
                    dying = false;
                    disappearing = true;
                    zombieAction = DISAPPEAR;
                } else if (disappearing) {
                    disappearing = false;
                    visible = false;
                } else {
                    attacking = false;
                }
            }
        }
    }


    private void updatePos() {
        if(spawned && moving){
            if(player.getHitbox().x < hitbox.x){
//                if (canMoveHere(hitbox.x + enemySpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
                        hitbox.x -= enemySpeed;
//                    }
                } else {
//                if (canMoveHere(hitbox.x + enemySpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)){
                    hitbox.x += enemySpeed;
                }
            }
        }



    private void setAnimation() {
        int startAni = zombieAction;

        if(!spawned && Math.abs(player.getHitbox().x - hitbox.x) < 300){
            zombieAction = UPRISE;
            visible = true;
            if(animations[zombieAction][aniIndex] == animations[UPRISE][11]){
                moving = true;
                spawned = true;
            }
        }

        if(spawned && !dying && !disappearing) {
            if(Math.abs(player.getHitbox().x - hitbox.x) < 40){
            moving = false;
            attacking = true;
            zombieAction = ATTACK;
            } else {
            moving = true;
            zombieAction = WALK;
            }
        }

        if (Math.abs(player.getHitbox().x - hitbox.x) < 50 && player.attacking){
            zombieAction = DEATH;
            moving = false;
            dying = true;
        }

        if (startAni != zombieAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }




}



