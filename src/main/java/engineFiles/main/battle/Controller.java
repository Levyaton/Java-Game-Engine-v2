package engineFiles.main.battle;

import engineFiles.GUIs.mainGameGui.BattlePanel;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Battle.battleGUI;
import engineFiles.main.models.Sprites.Items.Item;
import engineFiles.ui.fonts.FontLibrary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

//A class containing the controls that are used in battles
public class Controller extends Component {

  private BattlePanel battlePanel;

  private String[] baseOptions;
  private List<Item> inventory;

  private int curIndex, itemIndex = 0;
  private boolean showItemsOption = false;

  public Controller(BattlePanel battlePanel) {
    this.battlePanel = battlePanel;
    baseOptions = new String[] { "attack", "dodge" };
    inventory = battlePanel.player.getPlayer().getInventory();
  }

  @Override
  public void input() {
    if (KeyMap.isPressed()) {
      KeyMap.setPressed(false);
      if (KeyMap.isPressed(KeyEvent.VK_W)) {
        if (showItemsOption && (curIndex != 0)) {
          if ((curIndex % 3) == 0) {
            itemIndex = itemIndex - 3;
          }
          curIndex--;
        } else if (!showItemsOption && curIndex != 0) {
          curIndex--;
        }
      }
      if (KeyMap.isPressed(KeyEvent.VK_S)) {
        if (showItemsOption && ((curIndex + 1) < inventory.size())) {
          curIndex++;
          if ((curIndex % 3) == 0) {
            itemIndex = itemIndex + 3;
          }
        } else if (!showItemsOption && curIndex != 2) {
          curIndex++;
        }
      }
      if (KeyMap.isPressed(KeyEvent.VK_D) && (curIndex == 2) && !showItemsOption) {
        showItemsOption = true;
        curIndex = 0;
        itemIndex = 0;
      }
      if (KeyMap.isPressed(KeyEvent.VK_A) && showItemsOption) {
        showItemsOption = false;
        curIndex = 2;
      }
      if (KeyMap.isPressed(KeyEvent.VK_SPACE)) {
        if (!showItemsOption && (curIndex != 2)) {
          switch (baseOptions[curIndex]) {
            case "attack":
              battlePanel.getBattleManager().push(new Move(battlePanel.player, MoveEnum.ATTACK,
                  battlePanel.player.getDamage() + battlePanel.player.getBattleDamage()));
              break;
            case "dodge":
              battlePanel.getBattleManager().push(new Move(battlePanel.player, MoveEnum.DODGE));
              break;
          }
          curIndex = 0;
        } else if (showItemsOption) {
          showItemsOption = false;
          Item item = inventory.get(curIndex);
          inventory.remove(item);
          battlePanel.getBattleManager().push(new Move(battlePanel.player, MoveEnum.ITEM, item));
          battlePanel.player.getPlayer().setInventory(inventory);
          curIndex = 0;
          itemIndex = 0;
        }
      }
    }
  }

  @Override
  public void render(Graphics g) {
    if (showItemsOption) {
      g.drawImage(battleGUI.battleOptions, 25, 250, 350, 300, null);
      drawItemsOptions(g);
    } else {
      g.drawImage(battleGUI.battleOptions, 25, 250, 198, 164, null);
      drawBasicOptions(g);
    }
  }

  public void drawBasicOptions(Graphics g) {
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
    g.drawString("items -", 55, 390);
  }

  public void drawItemsOptions(Graphics g) {
    for (int i = itemIndex; i < itemIndex + 3; i++) {
      if (i >= inventory.size()) {
        continue;
      }
      if (curIndex == i) {
        g.setColor(Color.BLUE);
      } else {
        g.setColor(Color.BLACK);

      }
      g.setFont(FontLibrary.zeldaFontMedium);
      g.drawString(inventory.get(i).getName(), 55, 330 + ((i - itemIndex) * 80));
      g.setFont(FontLibrary.zeldaFontSmall);
      g.drawString(inventory.get(i).getDescription(), 55, 355 + ((i - itemIndex) * 80));
    }
  }
}