package semestralka.models.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.graphics.Animation;
import semestralka.models.Entity;
import semestralka.utils.AABB;
import semestralka.utils.Position;
import semestralka.world.World;

public abstract class Creature extends Entity {

  protected Animation animation;
  protected World world;
  protected boolean running = false;
  protected int direction = 0;
  protected int xMove, yMove;
  protected boolean up, down, left, right;

  public Creature(BufferedImage[][] character, Position pos, World world) {
    super(pos);
    this.world = world;
    animation = new Animation(character);
  }

  public abstract void update();

  public abstract void render(Graphics g);

  public void setRunning(boolean running) {
    this.running = running;
  }

  public void move() {
    System.out.println(pos.x / 48 + "," + pos.y / 48);
    if (xMove > 0) {
      if (!isColliding() && !world.getMap().isTileSolid(pos.x + xMove + bounds.x + bounds.width, pos.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x + xMove + bounds.x + bounds.width, pos.y)) {
        pos.x += xMove; // right
      }
    } else if (xMove < 0) {
      if (!isColliding() && !world.getMap().isTileSolid(pos.x + xMove, pos.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x + xMove, pos.y)) {
        pos.x += xMove; // left
      }
    }
    if (yMove > 0) {
      if (!isColliding() && !world.getMap().isTileSolid(pos.x + bounds.width, pos.y + yMove + bounds.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x, pos.y + yMove + bounds.y + bounds.width)) {
        pos.y += yMove; // down
      }
    } else if (yMove < 0) {
      if (!isColliding() && !world.getMap().isTileSolid(pos.x + bounds.width, pos.y + yMove)
          && !world.getMap().isTileSolid(pos.x, pos.y + yMove)) {
        pos.y += yMove; // up
      }
    }
  }

  public void animate() {
    if (xMove == 0 && yMove == 0) {
      running = false;
    } else {
      running = true;
    }
    animation.update();
  }

  public AABB getBounds() {
    return new AABB(pos.x, pos.y, 48, 48);
  }

  public AABB getNewBounds() {
    return new AABB(pos.x + xMove, pos.y + yMove, 48, 48);
  }

  public boolean isColliding() {
    for (Creature creature : world.getCreatureManager().getCreatures()) {
      if (creature.equals(this)) {
        continue;
      }
      if (creature.getBounds().isColliding(getNewBounds())) {
        return true;
      }
    }
    return false;
  }
}