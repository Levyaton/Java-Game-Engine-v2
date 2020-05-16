package semestralka.battle;

import java.awt.Color;
import java.awt.Graphics;

import semestralka.graphics.Resources;
import semestralka.ui.CallbackFunc;
import semestralka.utils.KeyManager;

public class Dialog extends Component {

  private String text;
  private CallbackFunc func;

  public Dialog(String text, CallbackFunc func) {
    this.text = text;
    this.func = func;
  }

  @Override
  public void input(KeyManager keyManager) {
    if (keyManager.keys[4]) {
      System.out.println("enter");
      keyManager.pressed = false;
      func.apply();
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(Resources.battleDialog, 180, 350, 400, 100, null);
    g.setColor(Color.BLACK);
    g.drawString(text, 210, 400);
  }
}