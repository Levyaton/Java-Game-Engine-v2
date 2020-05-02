package semestralka.ui;

import java.awt.Graphics;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;

public abstract class UIComponent {

  protected int width, height;
  protected Position pos;

  public UIComponent(Position pos, int width, int height) {
    this.pos = pos;
    this.width = width;
    this.height = height;
  }

  public abstract void input(MouseManager mouseManager);

  public abstract void update();

  public abstract void render(Graphics g);
}