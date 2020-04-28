package semestralka.screen;

import java.awt.Graphics;
import java.util.HashMap;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;

public class ScreenManager {

  private HashMap<String, Screen> screens;
  private String current = "menu";

  public ScreenManager() {
    screens = new HashMap<>();

    screens.put("menu", new MenuScreen());
  }

  public void init(Graphics g, KeyManager keyManager, MouseManager mouseManager) {
    Screen currentScreen = screens.get(current);

    currentScreen.update();
    currentScreen.render(g);
  }

  public Screen getCurrent() {
    return screens.get(current);
  }

  public void setCurrent(String current) {
    this.current = current;
  }
}