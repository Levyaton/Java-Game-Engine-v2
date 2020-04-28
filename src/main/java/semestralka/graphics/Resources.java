package semestralka.graphics;

import java.awt.Font;

public class Resources {

  public static Font arialFontBold;
  public static Font zeldaFontMedium;

  public static void load() {
    new FontLoader().registerFont("/fonts/ZeldaFont.ttf");
    zeldaFontMedium = new Font("The Wild Breath of Zelda", Font.PLAIN, 45);
    arialFontBold = new Font("Arial", Font.BOLD, 10);
  }
}