package semestralka.view;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import semestralka.engine.GameLoop;

public class GamePanel extends Canvas {

  public static int width = 720, height = 480;

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
    new GameLoop(this);
  }
}