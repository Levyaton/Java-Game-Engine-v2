package semestralka.ui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import semestralka.utils.Vector2;

public abstract class UIComponent {

  protected int width, height;
  protected Vector2 pos;

  public UIComponent(Vector2 pos, int width, int height) {
    this.pos = pos;
    this.width = width;
    this.height = height;
  }

  public abstract void update();

  public abstract void render(Graphics g);

  public abstract void keyPressed(KeyEvent e);

  public abstract void mouseMoved(MouseEvent e);
}