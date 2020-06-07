package engineFiles.ui.components;

import java.awt.Graphics;


//Class containing a custom component definition
public abstract class Component {

  protected int x, y, width, height;

  public Component(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public abstract void input(int x, int y, boolean clicked);

  public abstract void update();

  public abstract void render(Graphics g);
}
