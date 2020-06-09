package engineFiles.main.models.Sprites.Battle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * BattleGui stores the buffered images of loaded GUI components from the files
 */
public class battleGUI {
  private static final Logger LOG = Logger.getLogger(battleGUI.class.getName());
  public static BufferedImage battleBackground, battleOptions, battleDialog, battleStats;

  /**
   * Loads the GUI images for battle
   */
  public static void loadResources() {
    battleBackground = load("/battleGUI/battle_bg.png");
    battleOptions = load("/battleGUI/battle_options.png");
    battleDialog = load("/battleGUI/battle_dialog.png");
    battleStats = load("/battleGUI/battle_stats.png");
  }

  /**
   * @param path
   * @return BufferedImage
   */
  static public BufferedImage load(String path) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    try {
      return ImageIO.read(new FileInputStream("src/main/java/resources" + path));
    } catch (java.io.IOException e) {
      e.printStackTrace();
      LOG.severe("Error, could not load GUI component with path: " + path);
      return null;
    }
  }
}