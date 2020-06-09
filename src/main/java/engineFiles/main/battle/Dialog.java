package engineFiles.main.battle;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Battle.battleGUI;
import engineFiles.ui.components.CallbackFunc;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//A class containing methods of displaying text
public class Dialog extends Component {
  private static final Logger LOG = Logger.getLogger(Dialog.class.getName());
  private String text;
  private CallbackFunc func;

  /**
   * @param text
   * @param func
   * 
   */
  public Dialog(String text, CallbackFunc func) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    this.text = text;
    this.func = func;
    LOG.config("Dialog Initialized");
  }

  /**
   * If enter key is pressed the callback func is invoked
   * 
   */
  @Override
  public void input() {
    if (KeyMap.isPressed() && KeyMap.isPressed(KeyEvent.VK_SPACE)) {
      KeyMap.setPressed(false);
      func.apply();
    }
  }

  /**
   * @param g
   * 
   *          Renders the dialog component with text
   */
  @Override
  public void render(Graphics g) {
    g.drawImage(battleGUI.battleDialog, 170, 400, 450, 100, null);
    g.setColor(Color.BLACK);
    g.drawString(text, 190, 450);
  }
}