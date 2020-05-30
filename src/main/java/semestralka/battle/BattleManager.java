package semestralka.battle;

import java.util.Stack;

public class BattleManager {

  private Battle battle;
  private AI ai;
  private Stack<Move> moveStack;

  public BattleManager(Battle battle) {
    this.battle = battle;
    this.ai = new AI();
    moveStack = new Stack<Move>();
  }

  public void update() {
    if (moveStack.empty()) {
      return;
    }
    executeMove(moveStack.peek());
  }

  public void push(Move move) {
    moveStack.push(move);
    moveStack.push(ai.getNextMove(battle.opponent, "normal"));
  }

  public void executeMove(Move move) {
    moveStack.pop();
    switch (move.name) {
      case "attack":
        if (!moveStack.isEmpty() && moveStack.peek().name == "dodge") {
          battle.pushDialog(move.creature.getName() + " attack was dodged by " + moveStack.peek().creature.getName());
          moveStack.pop();
        } else {
          battle.pushDialog("Attack was effective");
          if (move.creature == battle.player) {
            battle.opponent.setCurHealth(battle.opponent.getCurHealth() - move.value);
          } else {
            battle.player.setCurHealth(battle.opponent.getCurHealth() - move.value);
          }
        }

        battle.pushDialog(move.creature.getName() + " attacks with " + move.value + " DMG");
        break;

      case "heal":
        move.creature.setCurHealth(move.creature.getCurHealth() + move.value);
        battle.pushDialog(move.creature.getName() + " heals " + move.value + " HP");
        break;

      case "dodge":
        if (!moveStack.isEmpty() && moveStack.peek().name == "attack") {
          battle.pushDialog(move.creature.getName() + " dodged an attacked by " + moveStack.peek().creature.getName());
          moveStack.pop();
        }

        battle.pushDialog(move.creature.getName() + " dodges attack");
        break;
    }
  }
}