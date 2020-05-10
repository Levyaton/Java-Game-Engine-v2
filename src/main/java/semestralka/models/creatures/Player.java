package semestralka.models.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import semestralka.utils.KeyManager;
import semestralka.utils.Position;
import semestralka.world.World;

public class Player extends Creature {

  BufferedImage frame;

  public Player(BufferedImage[][] character, Position pos, World world) {
    super(character, pos, world);
  }

  public void input(KeyManager keyManager) {
    xMove = 0;
    yMove = 0;

    if (keyManager.keys[0]) {
      direction = 3;
      yMove -= 3;// up
    } else if (keyManager.keys[1]) {
      direction = 0;
      yMove += 3; // down
    } else if (keyManager.keys[2]) {
      direction = 1;
      xMove -= 3; // left
    } else if (keyManager.keys[3]) {
      direction = 2;
      xMove += 3; // right
    }
  }

  @Override
  public void update() {
    animate();
    move();
    world.getCamera().centerPlayer(pos.x, pos.y);
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