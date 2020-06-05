package engineFiles.main.battle;

import java.util.Stack;

import engineFiles.GUIs.mainGameGui.BattlePanel;

public class BattleManager {

  private BattlePanel battlePanel;
  private Stack<Move> moveStack;

  private AI ai;

  public BattleManager(BattlePanel battlePanel) {
    this.battlePanel = battlePanel;
    this.ai = new AI();
    moveStack = new Stack<Move>();
  }

  public void push(Move move) {
    moveStack.push(move);
    moveStack.push(ai.getNextMove(battlePanel.opponent, "normal"));
  }

  public void update() {
    if (moveStack.isEmpty()) {
      return;
    }
    executeMove(moveStack.peek());
  }

  public void executeMove(Move move) {
    moveStack.pop();
    switch (move.name) {
      case "attack":
        if (!moveStack.isEmpty() && moveStack.peek().name == "dodge") {
          battlePanel.pushDialog(
              move.entity.getCategoryName() + " attack was dodged by " + moveStack.peek().entity.getCategoryName());
          moveStack.pop();
        } else {
          battlePanel.pushDialog("Attack was effective");
          if (move.entity == battlePanel.player) {
            battlePanel.opponent.setCurHealth(battlePanel.opponent.getCurHealth() - move.value);
          } else {
            battlePanel.player.setCurHealth(battlePanel.opponent.getCurHealth() - move.value);
          }
        }

        battlePanel.pushDialog(move.entity.getCategoryName() + " attacks with " + move.value + " DMG");
        break;

      case "heal":
        move.entity.setCurHealth(move.entity.getCurHealth() + move.value);
        battlePanel.pushDialog(move.entity.getCategoryName() + " heals " + move.value + " HP");
        break;

      case "dodge":
        if (!moveStack.isEmpty() && moveStack.peek().name == "attack") {
          battlePanel.pushDialog(
              move.entity.getCategoryName() + " dodged an attacked by " + moveStack.peek().entity.getCategoryName());
          moveStack.pop();
        }

        battlePanel.pushDialog(move.entity.getCategoryName() + " dodges attack");
        break;
    }
  }
}