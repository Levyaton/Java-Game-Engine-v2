package engineFiles.main.battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Battle.battleGUI;
import engineFiles.ui.components.CallbackFunc;

public class Dialog extends Component {

  private String text;
  private CallbackFunc func;

  public Dialog(String text, CallbackFunc func) {
    this.text = text;
    this.func = func;
  }

  @Override
  public void input() {
    if (KeyMap.isPressed() && KeyMap.isPressed(KeyEvent.VK_SPACE)) {
      KeyMap.setPressed(false);
      func.apply();
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(battleGUI.battleDialog, 170, 400, 450, 100, null);
    g.setColor(Color.BLACK);
    g.drawString(text, 190, 450);
  }
}