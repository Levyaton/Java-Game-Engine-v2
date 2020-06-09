package engineFiles.ui.fonts;

import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing a font library
public class FontLibrary {
  private static final Logger LOG = Logger.getLogger(FontLibrary.class.getName());
  public static Font arialFontBold, zeldaFontMedium, zeldaFontSmall;

  /**
   * Initialzes font loading
   */
  public FontLibrary() {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    loadFonts();
    LOG.config("FontLibrary Initialized");
  }

  /**
   * Loads the fonts for setting up custom font
   */
  public void loadFonts() {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    new FontLoader().register("/fonts/ZeldaFont.ttf");
    zeldaFontSmall = new Font("The Wild Breath of Zelda", Font.PLAIN, 30);
    zeldaFontMedium = new Font("The Wild Breath of Zelda", Font.PLAIN, 50);
    arialFontBold = new Font("Arial", Font.BOLD, 10);
    LOG.info("Fonts loaded");
  }
}