package engineFiles.main.battle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import engineFiles.GUIs.mainGameGui.BattlePanel;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Battle.battleGUI;
import engineFiles.ui.fonts.FontLibrary;

public class Controller extends Component {

  private BattlePanel battlePanel;
  private String[] baseOptions;

  private int curIndex = 0;

  public Controller(BattlePanel battlePanel) {
    this.battlePanel = battlePanel;
    baseOptions = new String[] { "attack", "dodge", "items" };
  }

  @Override
  public void input() {
    if (KeyMap.isPressed()) {
      KeyMap.setPressed(false);
      if (KeyMap.isPressed(KeyEvent.VK_W) && (curIndex != 0)) {
        curIndex--;
      }
      if (KeyMap.isPressed(KeyEvent.VK_S) && (curIndex != 2)) {
        curIndex++;
      }
      if (KeyMap.isPressed(KeyEvent.VK_SPACE)) {
        battlePanel.getBattleManager().push(new Move(battlePanel.player, baseOptions[curIndex], 2));
      }
    }
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(battleGUI.battleOptions, 25, 250, 198, 164, null);
    g.setFont(FontLibrary.zeldaFontMedium);
    if (curIndex == 0) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("attack", 55, 320);
    if (curIndex == 1) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("dodge", 55, 355);
    if (curIndex == 2) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("items", 55, 390);
  }
}