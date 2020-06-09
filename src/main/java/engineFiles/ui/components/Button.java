package engineFiles.ui.components;

import engineFiles.ui.fonts.FontLibrary;

import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing a custom button definition
public class Button extends Component {
  private static final Logger LOG = Logger.getLogger(Button.class.getName());
  private Rectangle bounds;
  private boolean hovering = false;
  private String text;
  private CallbackFunc func;

  /**
   * @param text
   * @param x
   * @param y
   * @param width
   * @param height
   * @param func
   * 
   */
  public Button(String text, int x, int y, int width, int height, CallbackFunc func) {
    super(x, y, width, height);
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    this.text = text;
    this.func = func;
    bounds = new Rectangle(x - (width / 2), y - (height / 2), width, height);
    LOG.config("Button Initialized");
  }

  /**
   * @param x
   * @param y
   * @param clicked
   * 
   *                Checks if button x, y coordinates interect with the rectangle
   *                boundaries. If yes hover is applied, else normal state
   */
  @Override
  public void input(int x, int y, boolean clicked) {
    if (bounds.contains(x, y)) {
      hovering = true;
      if (clicked) {
        func.apply();
      }
    } else {
      hovering = false;
    }
  }

  /**
   * @param g
   */
  @Override
  public void render(Graphics g) {
    g.drawRect((int) bounds.getX() - 1, (int) bounds.getY() - 1, (int) bounds.getWidth() + 1,
        (int) bounds.getHeight() + 1);
    if (hovering) {
      g.setColor(Color.WHITE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.fillRect((int) x - width / 2, (int) y - height / 2, width, height);
    if (hovering) {
      g.setColor(Color.BLACK);
    } else {
      g.setColor(Color.WHITE);
    }
    g.setFont(FontLibrary.zeldaFontMedium);
    int textWidth = g.getFontMetrics().stringWidth(text);
    int textHeight = g.getFontMetrics().getHeight();
    g.drawString(text, (int) x - textWidth / 2, (int) y - 5 + textHeight / 2);
  }
}
