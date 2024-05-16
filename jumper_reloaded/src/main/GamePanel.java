package main;
import inputs.KeyboardInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constants.Directions.*;


public class GamePanel extends JPanel{

    private int frames;
    private long lastCheck = 0;
    private Game game;


    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }


    private void setPanelSize() {
        Dimension size = new Dimension(800, 600);
        setPreferredSize(size);
    }

    public void updateGame(){
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);


//        g.drawImage(idleAni[aniIndex], xDelta, yDelta, 40, 56, null);
//        g.drawImage(walkAni[aniIndex], xDelta + 50, yDelta, 40, 56, null);
//        g.drawImage(attackAni[aniIndex], xDelta + 100, yDelta, 70, 56, null);
//        g.drawImage(jumpAni[aniIndex], xDelta + 180, yDelta, 40, 56, null);
    }


    public Game getGame(){
        return game;
    }

}
