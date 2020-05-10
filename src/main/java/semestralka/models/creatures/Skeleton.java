package semestralka.models.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.utils.Position;
import semestralka.world.World;

public class Skeleton extends Creature {

  BufferedImage frame;
  private int tick = 0;
  private int tickD = 0;

  public Skeleton(BufferedImage[][] character, Position pos, World world) {
    super(character, pos, world);
  }

  @Override
  public void update() {
    xMove = 0;
    yMove = 0;

    if (tickD == 0 && tick < 40) {
      direction = 2;
      xMove = 3;
    }
    if (tickD == 1 && tick < 40) {
      direction = 0;
      yMove = 3;
    }
    if (tickD == 2 && tick < 40) {
      direction = 1;
      xMove = -3;
    }
    if (tickD == 3 && tick < 40) {
      direction = 3;
      yMove = -3;
    }
    if (tick == 40) {
      if (tickD == 3) {
        tickD = 0;
      } else {
        tickD++;
      }
      tick = 0;
    }
    tick++;
    animate();
    move();
  }

  @Override
  public void render(Graphics g) {
    drawZone(g);
    if (running) {
      frame = animation.getRunFrame(direction);
    } else {
      frame = animation.getStandFrame(direction);
    }
    g.drawImage(frame, pos.x - world.getCamera().getWorldX(), pos.y - world.getCamera().getWorldY(), 48, 48, null);
  }

  public void drawZone(Graphics g) {
    g.setColor(Color.GREEN);
    g.drawRect(pos.x - world.getCamera().getWorldX(), pos.y - world.getCamera().getWorldY(), 48, 48);
  }
}