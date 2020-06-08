package engineFiles.main.battle;

import java.awt.*;
import java.util.logging.Logger;

//A custom definition of a Component object
public abstract class Component {
  public Component() {
    LOG.config("Component Initialized");
  }

  private static final Logger LOG = Logger.getLogger(Component.class.getName());
  public abstract void input();

  public abstract void render(Graphics g);
}