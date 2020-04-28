package semestralka.utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import semestralka.screen.ScreenManager;
import semestralka.view.GamePanel;

public class MouseManager implements MouseListener, MouseMotionListener {

  ScreenManager screenManager;

  public MouseManager(GamePanel gamePanel, ScreenManager screenManager) {
    gamePanel.addMouseListener(this);
    gamePanel.addMouseMotionListener(this);

    this.screenManager = screenManager;
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    ;
  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

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
    screenManager.getCurrent().mouseMoved(e);
  }
}