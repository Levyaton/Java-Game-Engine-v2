package semestralka.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpriteLoader {
  public BufferedImage loadSprite(String path) {
    try {
      return ImageIO.read(SpriteLoader.class.getResourceAsStream(path));
    } catch (java.io.IOException e) {
      e.printStackTrace();
      System.out.println("Error, could not load sprite with path: " + path);
      return null;
    }
  }
}
