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
  protected int health = 10;
  protected int curHealth = 10;
  protected int damage = 2;
  protected String name;

  public Creature(String name, BufferedImage[][] character, Position pos, World world) {
    super(pos);
    this.world = world;
    this.name = name;
    animation = new Animation(character);
  }

  public abstract void update();

  public abstract void render(Graphics g);

  public void setRunning(boolean running) {
    this.running = running;
  }

  public void move() {
    if (xMove > 0) {
      if (!world.getMap().isTileSolid(pos.x + xMove + bounds.x + bounds.width, pos.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x + xMove + bounds.x + bounds.width, pos.y)) {
        xMove();
      }
    } else if (xMove < 0) {
      if (!world.getMap().isTileSolid(pos.x + xMove, pos.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x + xMove, pos.y)) {
        xMove();
      }
    }

    if (yMove > 0) {
      if (!world.getMap().isTileSolid(pos.x + bounds.width, pos.y + yMove + bounds.y + bounds.height)
          && !world.getMap().isTileSolid(pos.x, pos.y + yMove + bounds.y + bounds.width)) {
        yMove();
      }
    } else if (yMove < 0) {
      if (!world.getMap().isTileSolid(pos.x + bounds.width, pos.y + yMove)
          && !world.getMap().isTileSolid(pos.x, pos.y + yMove)) {
        yMove();
      }
    }
  }

  public void xMove() {
    if (isColliding()) {
      if (this instanceof Player == false) {
        world.getBattle().startBattle(this);
      }
      return;
    }
    pos.x += xMove;
  }

  public void yMove() {
    if (isColliding()) {
      if (this instanceof Player == false) {
        world.getBattle().startBattle(this);
      }
    }
    pos.y += yMove;
  }

  public void animate() {
    if (xMove == 0 && yMove == 0) {
      running = false;
    } else {
      running = true;
      animation.update();
    }
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

  public String getName() {
    return name;
  }

  public Animation getAnimation() {
    return animation;
  }

  public int getCurHealth() {
    return curHealth;
  }

  public void setCurHealth(int curHealth) {
    this.curHealth = curHealth;
  }

  public int getHealth() {
    return health;
  }

  public int getDamage() {
    return damage;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }
}