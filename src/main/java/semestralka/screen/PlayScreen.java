package semestralka.screen;

import java.awt.Graphics;

import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.world.World;

public class PlayScreen extends Screen {

  private World world;

  public PlayScreen(ScreenManager screenManager) {
    world = new World();
  }

  @Override
  public void input(KeyManager keymanager, MouseManager mouseManager) {
    world.input(keymanager, mouseManager);
  }

  @Override
  public void update() {
    world.update();
  }

  @Override
  public void render(Graphics g) {
    world.render(g);
  }
}