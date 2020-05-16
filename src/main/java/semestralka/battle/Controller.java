package semestralka.battle;

import java.awt.Color;
import java.awt.Graphics;
import semestralka.graphics.Resources;
import semestralka.utils.KeyManager;

public class Controller extends Component {

  private Battle battle;

  private int curIndex = 0;
  private boolean pressAllowed = true;
  private Move curMove;
  private String[] baseOptions;

  public Controller(Battle battle) {
    this.battle = battle;
    baseOptions = new String[] { "attack", "dodge", "items" };
  }

  @Override
  public void input(KeyManager keyManager) {
    if (keyManager.pressed && pressAllowed) {
      pressAllowed = false;

      if (keyManager.keys[0] && (curIndex != 0)) {
        curIndex--;
      }
      if (keyManager.keys[1] && (curIndex != 2)) {
        curIndex++;
      }
      if (keyManager.keys[4]) {
        System.out.println("enter");
        curMove = new Move(baseOptions[curIndex], 2);
      }
    }
    if (!keyManager.pressed) {
      pressAllowed = true;
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(Resources.battleOptions, 10, 200, 178, 144, null);
    g.setFont(Resources.zeldaFontMedium);
    if (curIndex == 0) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("attack", 30, 255);
    if (curIndex == 1) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("dodge", 30, 290);
    if (curIndex == 2) {
      g.setColor(Color.BLUE);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawString("items", 30, 325);
  }

  public Move getMove() {
    return curMove;
  }

  public void resetMove() {
    curMove = null;
  }
}