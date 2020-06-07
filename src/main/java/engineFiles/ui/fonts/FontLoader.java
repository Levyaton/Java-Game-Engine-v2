package engineFiles.ui.fonts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.FileInputStream;

//Class containing font loading logic
public class FontLoader {

  /**
   * @param path
   */
  public void register(String path) {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/main/java/resources" + path));
      ge.registerFont(font);
    } catch (java.io.IOException | FontFormatException e) {
      e.printStackTrace();
      System.out.println("Error, could not load font with path: " + path);
    }
  }
}
