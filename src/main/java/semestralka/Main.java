package semestralka;

import semestralka.view.GamePanel;
import semestralka.view.Window;

public class Main {

  private Window window;
  private GamePanel gamePanel;

  public Main() {
    gamePanel = new GamePanel();
    window = new Window(gamePanel);
  }

  public static void main(String[] args) {
    new Main();
  }
}