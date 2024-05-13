package main;
import inputs.KeyboardInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel{

    private int xDelta = 0;
    private int yDelta = 0;
    public GamePanel(){

        addKeyListener(new KeyboardInputs(this));

    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(100 + xDelta, 100 + yDelta, 50, 100);
        repaint();

    }

}
