package semestralka.models.dynamic;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.graphics.Animation;
import semestralka.utils.Position;

public abstract class Creature extends Entity {

  protected Animation animation;
  protected boolean running = false;
  protected int direction = 1;
  protected int xMove, yMove;
  protected boolean up, down, left, right;

  public Creature(BufferedImage[] stand, BufferedImage[] runUp, BufferedImage[] runDown, BufferedImage[] runLeft,
      BufferedImage[] runRight, Position pos) {
    super(pos);
    animation = new Animation(stand, runUp, runDown, runLeft, runRight);
  }

  public abstract void update();

  public abstract void render(Graphics g);

  public void setRunning(boolean running) {
    this.running = running;
  }

  public void move() {
    pos.x += xMove;
    pos.y += yMove;
  }

  public void animate() {
    if (xMove == 0 && yMove == 0) {
      running = false;
    } else {
      running = true;
    }
    animation.update();
  }
}