package engineFiles.ui.components;

import engineFiles.ui.fonts.FontLibrary;

import java.awt.*;


//Class containing a custom button definition
public class Button extends Component {

  private Rectangle bounds;
  private boolean hovering = false;
  private String text;
  private CallbackFunc func;

  public Button(String text, int x, int y, int width, int height, CallbackFunc func) {
    super(x, y, width, height);
    this.text = text;
    this.func = func;
    bounds = new Rectangle(x - (width / 2), y - (height / 2), width, height);
  }

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
