package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Resolution;
import engineFiles.ui.components.Button;
import engineFiles.ui.components.Component;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// Menu screen for game start
public class MenuPanel extends GamePanel {
  private static final Logger LOG = Logger.getLogger(MenuPanel.class.getName());
  private boolean clicked = false;
  private int mouseX, mouseY = 0;
  private List<Component> components;

  public MenuPanel(String panelName, Window window) {
    super(panelName, window);
    LOG.setUseParentHandlers(true);
    components = new ArrayList<>();
    components.add(new Button("start", Resolution.SCREEN_WIDTH / 2, Resolution.SCREEN_HEIGHT / 2, 180, 60,
        () -> window.setPanel("overworld")));
    LOG.config("MenuPanel Initialized");
  }

  /**
   * @return Image
   */
  @Override
  public Image getRenderGraphics() {
    Image img = createImage(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    Graphics g = img.getGraphics();
    g.clearRect(0, 0, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    for (Component component : components) {
      component.input(mouseX, mouseY, clicked);
      component.render(g);
    }
    return img;
  }

  @Override
  public void update() {
  }

  /**
   * @param e
   */
  @Override
  public void mousePressed(MouseEvent e) {
    clicked = true;
  }

  /**
   * @param e
   */
  @Override
  public void mouseReleased(MouseEvent e) {
    clicked = false;
  }

  /**
   * @param e
   */
  @Override
  public void mouseMoved(MouseEvent e) {
    mouseX = e.getX();
    mouseY = e.getY();
  }
}