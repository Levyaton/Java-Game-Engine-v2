package semestralka.models;

import java.awt.image.BufferedImage;
import semestralka.graphics.Resources;
import semestralka.view.GamePanel;

public class TileMap {

  private BufferedImage[][] map;

  public TileMap() {
    map = new BufferedImage[GamePanel.width / 48][GamePanel.height / 48];

    for (int x = 0; x < (GamePanel.width / 48); x++) {
      for (int y = 0; y < (GamePanel.height / 48); y++) {
        map[x][y] = Resources.grass;
      }
    }
  }

  public BufferedImage getTile(int x, int y) {
    return map[x][y];
  }
}