package engineFiles.main.models.Sprites.Battle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;

//Class containing the battle gui
public class battleGUI {

  public static BufferedImage battleBackground, battleOptions, battleDialog, battleStats;

  public static void loadResources() {
    battleBackground = load("/battleGUI/battle_bg.png");
    battleOptions = load("/battleGUI/battle_options.png");
    battleDialog = load("/battleGUI/battle_dialog.png");
    battleStats = load("/battleGUI/battle_stats.png");
  }

  static public BufferedImage load(String path) {
    try {
      return ImageIO.read(new FileInputStream("src/main/java/resources" + path));
    } catch (java.io.IOException e) {
      e.printStackTrace();
      System.out.println("Error, could not load GUI component with path: " + path);
      return null;
    }
  }
}