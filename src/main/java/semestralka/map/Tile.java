package semestralka.map;

import java.awt.image.BufferedImage;
import semestralka.graphics.Resources;

public class Tile {

  public BufferedImage tile;
  public int id;

  public Tile(int id) {
    this.tile = Resources.basictiles[id];
    this.id = id;
  }
}