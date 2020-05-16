package semestralka.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Resources {

  public static Font arialFontBold, zeldaFontMedium, zeldaFontSmall;

  public static BufferedImage battleBackground, battleOptions, battleDialog, battleStats;

  public static BufferedImage[] basictiles;

  public static BufferedImage[][] player;
  public static BufferedImage[][] skeleton;

  public void load() {
    loadFonts();
    loadBasictiles();
    loadCharacters();
    loadBattleComponents();
  }

  public static void loadFonts() {
    new FontLoader().register("/fonts/ZeldaFont.ttf");
    zeldaFontSmall = new Font("The Wild Breath of Zelda", Font.PLAIN, 20);
    zeldaFontMedium = new Font("The Wild Breath of Zelda", Font.PLAIN, 45);
    arialFontBold = new Font("Arial", Font.BOLD, 10);
  }

  public static void loadBasictiles() {
    BufferedImage sheet = new SpriteLoader().load("/textures/basictiles.png");
    int width = sheet.getWidth() / 16;
    int height = sheet.getHeight() / 16;

    basictiles = new BufferedImage[120];

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        basictiles[x + (y * width)] = sheet.getSubimage(x * 16, y * 16, 16, 16);
      }
    }
  }

  public static void loadCharacters() {
    BufferedImage sheet = new SpriteLoader().load("/textures/characters.png");

    player = new BufferedImage[3][4];
    loopCharacters(48, 0, sheet, player);

    skeleton = new BufferedImage[3][4];
    loopCharacters(144, 0, sheet, skeleton);
  }

  public static void loopCharacters(int originX, int originY, BufferedImage sheet, BufferedImage[][] character) {
    for (int x = 0; x < 3; x++) {
      for (int y = 0; y < 4; y++) {
        character[x][y] = sheet.getSubimage((x * 16) + originX, (y * 16) + originY, 16, 16);
      }
    }
  }

  public void loadBattleComponents() {
    battleBackground = new SpriteLoader().load("/components/battle_bg.png");
    battleOptions = new SpriteLoader().load("/components/battle_options.png");
    battleDialog = new SpriteLoader().load("/components/battle_dialog.png");
    battleStats = new SpriteLoader().load("/components/battle_stats.png");
  }
}