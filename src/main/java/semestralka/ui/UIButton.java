package semestralka.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import semestralka.graphics.Resources;
import semestralka.utils.AABB;
import semestralka.utils.Vector2;

public class UIButton extends UIComponent {

  private AABB bounds;
  private boolean hovering = false;
  private String text;

  public UIButton(String text, Vector2 pos, int width, int height) {
    super(pos, width, height);
    this.text = text;
    bounds = new AABB((int) pos.x - width / 2, (int) pos.y - height / 2, width, height);
  }

  @Override
  public void update() {

  }

  @Override
  public void render(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect((int) pos.x - width / 2, (int) pos.y - height / 2, width, height);

    g.setColor(Color.WHITE);
    g.setFont(Resources.zeldaFontMedium);
    int textWidth = g.getFontMetrics().stringWidth(text);
    int textHeight = g.getFontMetrics().getHeight();
    g.drawString(text, (int) pos.x - textWidth / 2, (int) pos.y - 5 + textHeight / 2);
  }

  @Override
  public void keyPressed(KeyEvent e) {

  };

  @Override
  public void mouseMoved(MouseEvent e) {
    if (bounds.isColliding(e.getX(), e.getY())) {
      hovering = true;
    }
  };
}