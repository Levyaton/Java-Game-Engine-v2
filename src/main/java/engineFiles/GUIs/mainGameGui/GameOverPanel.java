package engineFiles.GUIs.mainGameGui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import engineFiles.ui.components.Button;
import engineFiles.ui.Resolution;

// Gameover screen after loosing, closes the window after click
public class GameOverPanel extends GamePanel {

  private boolean clicked = false;
  private int mouseX, mouseY = 0;
  private Button endButton;

  public GameOverPanel(String panelName, Window window) {
    super(panelName, window);
    endButton = new Button("End", Resolution.SCREEN_WIDTH / 2, Resolution.SCREEN_HEIGHT / 2, 180, 60, () -> {
      System.exit(0);
      window.dispose();
      window.setVisible(false);
    });
  }

  /**
   * @return Image
   */
  @Override
  public Image getRenderGraphics() {
    Image img = createImage(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    Graphics g = img.getGraphics();
    endButton.input(mouseX, mouseY, clicked);
    endButton.render(g);
    g.setColor(Color.WHITE);
    g.drawString("GAME OVER", (Resolution.SCREEN_WIDTH / 2) - 120, (Resolution.SCREEN_HEIGHT / 2) - 80);
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