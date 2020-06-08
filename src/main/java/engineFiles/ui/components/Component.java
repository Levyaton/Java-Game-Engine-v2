package engineFiles.ui.components;

import java.awt.*;
import java.util.logging.Logger;

//Class containing a custom component definition
public abstract class Component {
  private static final Logger LOG = Logger.getLogger(Component.class.getName());
  protected int x, y, width, height;

  public Component(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    LOG.config("Component Initialized");
  }

  public abstract void input(int x, int y, boolean clicked);

  public abstract void render(Graphics g);
}
