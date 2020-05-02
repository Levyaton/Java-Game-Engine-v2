package semestralka.models.dynamic;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.utils.KeyManager;
import semestralka.utils.Position;

public class Player extends Creature {

  public Player(BufferedImage[] stand, BufferedImage[] runUp, BufferedImage[] runDown, BufferedImage[] runLeft,
      BufferedImage[] runRight, Position pos) {
    super(stand, runUp, runDown, runLeft, runRight, pos);
  }

  public void input(KeyManager keyManager) {
    xMove = 0;
    yMove = 0;

    if (keyManager.keys[0]) {
      direction = 0;
      yMove -= 3;
    }
    if (keyManager.keys[1]) {
      direction = 1;
      yMove += 3;
    }
    if (keyManager.keys[2]) {
      direction = 2;
      xMove -= 3;
    }
    if (keyManager.keys[3]) {
      direction = 3;
      xMove += 3;
    }
  }

  @Override
  public void update() {
    animate();
    move();
  }

  @Override
  public void render(Graphics g) {
    drawZone(g);
    if (running) {
      g.drawImage(animation.getRunFrame(direction), pos.x, pos.y, 48, 48, null);
    } else {
      g.drawImage(animation.getStandFrame(direction), pos.x, pos.y, 48, 48, null);
    }
  }

  public void drawZone(Graphics g) {
    g.setColor(Color.RED);
    g.drawRect(pos.x, pos.y + 30, 48, 18);
  }
}