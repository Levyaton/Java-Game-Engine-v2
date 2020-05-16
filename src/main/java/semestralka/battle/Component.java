package semestralka.battle;

import java.awt.Graphics;
import semestralka.utils.KeyManager;

public abstract class Component {

  public abstract void input(KeyManager keyManager);

  public abstract void update();

  public abstract void render(Graphics g);
}