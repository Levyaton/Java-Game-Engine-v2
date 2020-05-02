package semestralka.screen;

import java.awt.Graphics;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;

public class ScreenManager {

  private Screen menu, play;
  private Screen current;

  private MouseManager mouseManager;
  private KeyManager keyManager;

  public ScreenManager(KeyManager keyManager, MouseManager mouseManager) {
    this.keyManager = keyManager;
    this.mouseManager = mouseManager;

    menu = new MenuScreen(this);
    play = new PlayScreen(this, keyManager);

    current = menu;
  }

  public void init(Graphics g) {
    current.input(keyManager, mouseManager);
    current.update();
    current.render(g);
  }

  public Screen getCurrent() {
    return current;
  }

  public void setCurrent(String screen) {
    if (screen == "menu") {
      current = menu;
    }
    if (screen == "play") {
      current = play;
    }
  }
}