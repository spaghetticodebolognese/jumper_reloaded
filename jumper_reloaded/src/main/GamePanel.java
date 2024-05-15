package main;
import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel{

    private int xDelta = 0;
    private int yDelta = 0;
    private BufferedImage img;
    private int aniTick, aniIndex, aniSpeed = 200;
    private BufferedImage[] idleAni;
    private BufferedImage[] walkAni;
    private BufferedImage[] attackAni;
    private BufferedImage[] jumpAni;


    public GamePanel(){
        loadingIdleAnimation();
        loadingWalkAnimation();
        loadingAttackAnimation();
        loadingJumpAnimation();
        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
    }

    private void loadingIdleAnimation(){
        idleAni = new BufferedImage[4];
        InputStream is;
        for (int i = 0; i < idleAni.length; i++){
            is = getClass().getResourceAsStream("/crow Animations/idle/crow_idle" + (i+1) + ".png");
            try {
                idleAni[i] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadingWalkAnimation(){
        walkAni = new BufferedImage[4];
        InputStream is;
        for (int i = 0; i < walkAni.length; i++){
            is = getClass().getResourceAsStream("/crow Animations/walk/crow_walk" + (i+1) + ".png");
            try {
                walkAni[i] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadingAttackAnimation(){
        attackAni = new BufferedImage[5];
        InputStream is;
        for (int i = 0; i < attackAni.length; i++){
            is = getClass().getResourceAsStream("/crow Animations/attack/crow_attack" + (i+1) + ".png");
            try {
                attackAni[i] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void loadingJumpAnimation(){
        jumpAni = new BufferedImage[6];
        InputStream is;
        for (int i = 0; i < jumpAni.length; i++){
            is = getClass().getResourceAsStream("/crow Animations/jump/crow_jump" + (i+1) + ".png");
            try {
                jumpAni[i] = ImageIO.read(is);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }







    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);

    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimation();
        g.drawImage(idleAni[aniIndex], xDelta, yDelta, 40, 56, null);
        g.drawImage(walkAni[aniIndex], xDelta + 50, yDelta, 40, 56, null);
        g.drawImage(attackAni[aniIndex], xDelta + 100, yDelta, 70, 56, null);
        g.drawImage(jumpAni[aniIndex], xDelta + 180, yDelta, 40, 56, null);
        //g.drawImage(img.getScaledInstance(40, 56, 1), 0, 0, null);
        //g.fillRect(100 + xDelta, 100 + yDelta, 50, 100);


    }

    private void updateAnimation() {
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idleAni.length){
                aniIndex = 0;
            }
        }
    }

}
