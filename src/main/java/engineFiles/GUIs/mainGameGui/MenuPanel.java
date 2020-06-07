package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Resolution;
import engineFiles.ui.components.Button;
import engineFiles.ui.components.Component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


//Class that is used to get the information needed to display for the main menu
public class MenuPanel extends GamePanel {

  private boolean clicked = false;
  private int mouseX, mouseY = 0;
  private List<Component> components;

  public MenuPanel(String panelName, Window window) {
    super(panelName, window);
    components = new ArrayList<>();
    components.add(new Button("start", Resolution.SCREEN_WIDTH / 2, Resolution.SCREEN_HEIGHT / 2, 180, 60,
        () -> window.setPanel("overworld")));
  }

  @Override
  public Image getRenderGraphics() {
    Image img = createImage(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    Graphics g = img.getGraphics();
    g.clearRect(0, 0, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    for (Component component : components) {
      component.input(mouseX, mouseY, clicked);
      component.update();
      component.render(g);
    }
    return img;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    clicked = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    clicked = false;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
    System.out.println("menu");
  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void update() {
  }
}