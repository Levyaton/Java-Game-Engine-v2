package semestralka.ui;

import java.awt.Color;
import java.awt.Graphics;
import semestralka.graphics.Resources;
import semestralka.utils.AABB;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;

public class UIButton extends UIComponent {

  private AABB bounds;
  private boolean hovering = false;
  private String text;
  private ClickFunc func;

  public UIButton(String text, Position pos, int width, int height, ClickFunc func) {
    super(pos, width, height);
    this.text = text;
    this.func = func;
    bounds = new AABB(pos.x - width / 2, pos.y - height / 2, width, height);
  }

  @Override
  public void input(MouseManager mouseManager) {
    if (bounds.isColliding(mouseManager.getX(), mouseManager.getY())) {
      hovering = true;
      if (mouseManager.isClicked()) {
        func.apply();
      }
    } else {
      hovering = false;
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void render(Graphics g) {
    if (hovering) {
      g.setColor(Color.WHITE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.fillRect((int) pos.x - width / 2, (int) pos.y - height / 2, width, height);
    if (hovering) {
      g.setColor(Color.BLACK);
    } else {
      g.setColor(Color.WHITE);
    }
    g.setFont(Resources.zeldaFontMedium);
    int textWidth = g.getFontMetrics().stringWidth(text);
    int textHeight = g.getFontMetrics().getHeight();
    g.drawString(text, (int) pos.x - textWidth / 2, (int) pos.y - 5 + textHeight / 2);
  }
}