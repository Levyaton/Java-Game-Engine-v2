package semestralka.models.items;

import java.awt.image.BufferedImage;

public abstract class Item {

  private int id;
  private int count;
  private String name;
  private BufferedImage img;

  public Item(int id, String name) {
    this.id = id;
    this.name = name;
  }
}