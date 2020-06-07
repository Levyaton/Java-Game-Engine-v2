package engineFiles.ui.fonts;

import java.awt.Font;

//Class containing a font library
public class FontLibrary {

  public static Font arialFontBold, zeldaFontMedium, zeldaFontSmall;

  public FontLibrary() {
    loadFonts();
  }

  public void loadFonts() {
    new FontLoader().register("/fonts/ZeldaFont.ttf");
    zeldaFontSmall = new Font("The Wild Breath of Zelda", Font.PLAIN, 30);
    zeldaFontMedium = new Font("The Wild Breath of Zelda", Font.PLAIN, 50);
    arialFontBold = new Font("Arial", Font.BOLD, 10);
  }
}