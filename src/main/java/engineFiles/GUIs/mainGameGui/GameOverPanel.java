package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Resolution;
import engineFiles.ui.components.Button;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

// Gameover screen after loosing, closes the window after click
public class GameOverPanel extends GamePanel {
  private static final Logger LOG = Logger.getLogger(GameOverPanel.class.getName());
  private boolean clicked = false;
  private int mouseX, mouseY = 0;
  private Button endButton;

  public GameOverPanel(String panelName, Window window) {
    super(panelName, window);
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
    LOG.addHandler(stdout);
    endButton = new Button("End", Resolution.SCREEN_WIDTH / 2, Resolution.SCREEN_HEIGHT / 2, 180, 60, () -> {
      System.exit(0);
      window.dispose();
      window.setVisible(false);
      LOG.config("GameOverPanel Initialized");
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