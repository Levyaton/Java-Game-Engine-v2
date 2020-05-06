package semestralka.models.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.utils.Position;
import semestralka.world.World;

public class Enemy extends Creature {

  BufferedImage frame;

  public Enemy(BufferedImage[][] character, Position pos, World world) {
    super(character, pos, world);
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