package engineFiles.main.battle;

import engineFiles.GUIs.mainGameGui.BattlePanel;

import java.util.Stack;

//A class containing the battle logic
public class BattleManager {

  private BattlePanel battlePanel;
  private Stack<Move> moveStack;

  private AI ai;

  public BattleManager(BattlePanel battlePanel) {
    this.battlePanel = battlePanel;
    this.ai = new AI();
    moveStack = new Stack<Move>();
  }

  /**
   * @param move
   */
  public void push(Move move) {
    moveStack.push(move);
    moveStack.push(ai.getNextMove(battlePanel.opponent));
  }

  public void update() {
    if (moveStack.isEmpty()) {
      return;
    }
    executeMove(moveStack.peek());
  }

  /**
   * @param move
   */
  public void executeMove(Move move) {
    moveStack.pop();
    switch (move.type) {
      case ATTACK:
        if (!moveStack.isEmpty() && moveStack.peek().type == MoveEnum.DODGE) {
          battlePanel.pushDialog(
              move.entity.getCategoryName() + " attack was dodged by " + moveStack.peek().entity.getCategoryName());
          moveStack.pop();
        } else {
          battlePanel.pushDialog("Attack was effective");
          if (move.entity == battlePanel.player) {
            battlePanel.opponent.setCurHealth(battlePanel.opponent.getCurHealth() - move.value);
          } else {
            battlePanel.player.setCurHealth(battlePanel.player.getCurHealth() - move.value);
          }
        }
        battlePanel.pushDialog(move.entity.getCategoryName() + " attacks with " + move.value + " DMG");
        break;

      case ITEM:
        if (move.item.getAttackMod() != 0) {
          battlePanel.pushDialog("item increased attack by " + move.item.getAttackMod() + " DMG");
          move.entity.setBattleDamage(move.entity.getBattleDamage() + move.item.getAttackMod());
        }
        if (move.item.getHealthMod() != 0) {
          battlePanel.pushDialog("item increased health by " + move.item.getHealthMod() + " HP");
          move.entity.setCurHealth(move.entity.getCurHealth() + move.item.getHealthMod());
        }
        if (move.item.getSpeedMod() != 0) {
          battlePanel.pushDialog("item increased speed by " + move.item.getSpeedMod() + " MS");
        }
        battlePanel.pushDialog(move.entity.getCategoryName() + " uses " + move.item.getName());
        break;

      case DODGE:
        if (!moveStack.isEmpty() && moveStack.peek().type == MoveEnum.ATTACK) {
          battlePanel.pushDialog(
              move.entity.getCategoryName() + " dodged an attacked by " + moveStack.peek().entity.getCategoryName());
          moveStack.pop();
        }

        battlePanel.pushDialog(move.entity.getCategoryName() + " dodges attack");
        break;
    }
  }
}