package semestralka.screen;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import semestralka.ui.UIButton;
import semestralka.ui.UIComponent;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;
import semestralka.view.GamePanel;

public class MenuScreen extends Screen {

  private List<UIComponent> components;

  public MenuScreen(ScreenManager screenManager) {
    components = new ArrayList<>();
    components.add(new UIButton("start", new Position(GamePanel.width / 2, GamePanel.height / 2), 180, 60,
        () -> screenManager.setCurrent("play")));
  }

  @Override
  public void input(KeyManager keyManager, MouseManager mouseManager) {
    for (UIComponent component : components) {
      component.input(mouseManager);
    }
  }

  @Override
  public void update() {
  }

  @Override
  public void render(Graphics g) {
    for (UIComponent component : components) {
      component.render(g);
    }
  }
}