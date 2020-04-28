package semestralka.screen;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import semestralka.ui.UIButton;
import semestralka.ui.UIComponent;
import semestralka.utils.Vector2;
import semestralka.view.GamePanel;

public class MenuScreen extends Screen {

  private List<UIComponent> components;

  public MenuScreen() {
    components = new ArrayList<>();
    components.add(new UIButton("start", new Vector2(GamePanel.width / 2, GamePanel.height / 2), 180, 60));
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

  @Override
  public void keyPressed(KeyEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {
    for (UIComponent component : components) {
      component.mouseMoved(e);
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }
}