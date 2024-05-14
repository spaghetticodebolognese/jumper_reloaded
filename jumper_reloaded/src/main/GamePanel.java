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



    public GamePanel(){

        importImg();
        addKeyListener(new KeyboardInputs(this));
        setPanelSize();
        

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/crow Animations/idle/crow_idle1.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
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
        g.drawImage(img, xDelta, yDelta, 40, 56, null);
        //g.drawImage(img.getScaledInstance(40, 56, 1), 0, 0, null);
        //g.fillRect(100 + xDelta, 100 + yDelta, 50, 100);


    }

}
