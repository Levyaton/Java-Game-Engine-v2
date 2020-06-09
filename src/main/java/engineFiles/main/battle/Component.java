package engineFiles.main.battle;

import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * A custom definition for component used for GUI in battle
 *
 */
public abstract class Component {

  public Component() {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    LOG.config("Component Initialized");
  }

  private static final Logger LOG = Logger.getLogger(Component.class.getName());

  public abstract void input();

  public abstract void render(Graphics g);
}