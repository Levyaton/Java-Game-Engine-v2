package semestralka.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Screen {

  public abstract void render(Graphics g);

  public abstract void update();

  public abstract void keyPressed(KeyEvent e);

  public abstract void mouseMoved(MouseEvent e);

  public abstract void mouseReleased(MouseEvent e);
}