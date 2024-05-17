package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_SPRITESHEET = "crow Animations/crow_spritesheet.png";

    public static BufferedImage getSpriteSheet(String fileName){
        BufferedImage spriteSheet = null;
        InputStream is = LoadSave.class.getResourceAsStream("/" + PLAYER_SPRITESHEET);
        try {
            spriteSheet = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spriteSheet;
    }


}
