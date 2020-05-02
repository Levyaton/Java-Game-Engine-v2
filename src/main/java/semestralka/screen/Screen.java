package semestralka.screen;

import java.awt.Graphics;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;

public abstract class Screen {

  public abstract void input(KeyManager keymanager, MouseManager mouseManager);

  public abstract void update();

  public abstract void render(Graphics g);
}