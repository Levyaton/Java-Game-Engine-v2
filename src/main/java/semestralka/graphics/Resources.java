package semestralka.graphics;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Resources {

  public static Font arialFontBold;
  public static Font zeldaFontMedium;

  public static BufferedImage grass, rock, tree;
  public static BufferedImage[] player;
  public static BufferedImage[] player_run_down, player_run_up, player_run_left, player_run_right;

  public static void load() {
    new FontLoader().registerFont("/fonts/ZeldaFont.ttf");
    zeldaFontMedium = new Font("The Wild Breath of Zelda", Font.PLAIN, 45);
    arialFontBold = new Font("Arial", Font.BOLD, 10);

    BufferedImage tileSheet = new SpriteLoader().loadSprite("/textures/basictiles.png");
    BufferedImage entitySheet = new SpriteLoader().loadSprite("/textures/characters.png");

    grass = tileSheet.getSubimage(48, 16, 16, 16);
    rock = tileSheet.getSubimage(16, 0, 16, 16);
    tree = tileSheet.getSubimage(56, 216, 16, 16);

    player = new BufferedImage[4];
    player[0] = entitySheet.getSubimage(64, 48, 16, 16);
    player[1] = entitySheet.getSubimage(64, 0, 16, 16);
    player[2] = entitySheet.getSubimage(64, 16, 16, 16);
    player[3] = entitySheet.getSubimage(64, 32, 16, 16);

    player_run_down = new BufferedImage[2];
    player_run_down[0] = entitySheet.getSubimage(48, 0, 16, 16);
    player_run_down[1] = entitySheet.getSubimage(80, 0, 16, 16);

    player_run_up = new BufferedImage[2];
    player_run_up[0] = entitySheet.getSubimage(48, 48, 16, 16);
    player_run_up[1] = entitySheet.getSubimage(80, 48, 16, 16);

    player_run_left = new BufferedImage[2];
    player_run_left[0] = entitySheet.getSubimage(48, 16, 16, 16);
    player_run_left[1] = entitySheet.getSubimage(80, 16, 16, 16);

    player_run_right = new BufferedImage[2];
    player_run_right[0] = entitySheet.getSubimage(48, 32, 16, 16);
    player_run_right[1] = entitySheet.getSubimage(80, 32, 16, 16);
  }
}