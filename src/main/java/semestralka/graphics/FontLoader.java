package semestralka.graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;

public class FontLoader {
  public void registerFont(String path) {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      Font font = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path));
      ge.registerFont(font);
    } catch (java.io.IOException | FontFormatException e) {
      e.printStackTrace();
      System.out.println("Error, could not load font with path: " + path);
    }
  }
}
