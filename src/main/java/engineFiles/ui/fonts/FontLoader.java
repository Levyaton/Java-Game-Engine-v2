package engineFiles.ui.fonts;

import java.awt.*;
import java.io.FileInputStream;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing font loading logic
public class FontLoader {
  private static final Logger LOG = Logger.getLogger(FontLoader.class.getName());
  /**
   * @param path
   */
  public void register(String path) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
    LOG.addHandler(stdout);
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    try {
      Font font = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("src/main/java/resources" + path));
      ge.registerFont(font);
    } catch (java.io.IOException | FontFormatException e) {
      e.printStackTrace();
      LOG.severe("Error, could not load font with path: " + path);
    }
  }
}
