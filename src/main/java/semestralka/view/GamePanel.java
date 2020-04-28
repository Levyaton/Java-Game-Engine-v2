package semestralka.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import semestralka.engine.GameLoop;

public class GamePanel extends Canvas {

  public static int width = 600, height = 400;

  public GamePanel() {
    Dimension d = new Dimension(width, height);
    setBackground(new Color(53, 66, 74));
    setPreferredSize(d);
    setMaximumSize(d);
    setMinimumSize(d);
    requestFocus();
  }

  public void addNotify() {
    super.addNotify();
    createBufferStrategy(3);
    BufferStrategy bs = getBufferStrategy();
    new GameLoop(bs, this);
  }
}