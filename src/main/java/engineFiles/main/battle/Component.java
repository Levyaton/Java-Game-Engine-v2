package engineFiles.main.battle;

import java.awt.Graphics;

//A custom definition of a Component object
public abstract class Component {

  public abstract void input();

  public abstract void render(Graphics g);
}