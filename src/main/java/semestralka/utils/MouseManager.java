package semestralka.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import semestralka.view.GamePanel;

public class MouseManager implements MouseListener, MouseMotionListener {

  private int x, y;
  private boolean clicked;

  public MouseManager(GamePanel gamePanel) {
    gamePanel.addMouseListener(this);
    gamePanel.addMouseMotionListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    clicked = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    clicked = false;
  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    x = e.getX();
    y = e.getY();
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public boolean isClicked() {
    return clicked;
  }
}