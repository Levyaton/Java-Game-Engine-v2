package semestralka.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import semestralka.screen.ScreenManager;
import semestralka.view.GamePanel;

public class KeyManager implements KeyListener {

  public KeyManager(GamePanel gamePanel, ScreenManager screenManager) {
    gamePanel.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void keyReleased(KeyEvent e) {

  }
}