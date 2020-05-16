package semestralka.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import semestralka.view.GamePanel;

public class KeyManager implements KeyListener {

  public boolean pressed = false;
  public boolean[] keys;

  public KeyManager(GamePanel gamePanel) {
    keys = new boolean[5];
    gamePanel.addKeyListener(this);
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    pressed = true;

    if (keyCode == KeyEvent.VK_W) {
      keys[0] = true;
    }
    if (keyCode == KeyEvent.VK_S) {
      keys[1] = true;
    }
    if (keyCode == KeyEvent.VK_A) {
      keys[2] = true;
    }
    if (keyCode == KeyEvent.VK_D) {
      keys[3] = true;
    }
    if (keyCode == KeyEvent.VK_SPACE) {
      keys[4] = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    int keyCode = e.getKeyCode();
    pressed = false;

    if (keyCode == KeyEvent.VK_W) {
      keys[0] = false;
    }
    if (keyCode == KeyEvent.VK_S) {
      keys[1] = false;
    }
    if (keyCode == KeyEvent.VK_A) {
      keys[2] = false;
    }
    if (keyCode == KeyEvent.VK_D) {
      keys[3] = false;
    }
    if (keyCode == KeyEvent.VK_SPACE) {
      keys[4] = false;
    }
  }
}