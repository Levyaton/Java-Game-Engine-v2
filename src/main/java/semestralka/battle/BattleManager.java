package semestralka.battle;

import java.util.Stack;

public class BattleManager {

  private Battle battle;
  private AI ai;

  private boolean isPlayerTurn = true;
  private boolean waiting = true;
  private Stack<BattlePhase> battlePhases;
  private Move playerMove, opponentMove;

  public BattleManager(Battle battle) {
    this.battle = battle;
    this.ai = new AI();
    battlePhases = new Stack<BattlePhase>();
  }

  public void update() {

    // if (waiting) {
    // if (isPlayerTurn) {
    // playerMove = battle.getController().getMove();
    // if (playerMove == null) {
    // return;
    // }
    // System.out.println("player picked a move");
    // isPlayerTurn = false;
    // } else {
    // opponentMove = ai.getNextMove(battle.opponent.getName());
    // System.out.println("oponnent picked a move");
    // waiting = false;
    // initFight();
    // }
    // }
  }

  public void initFight() {
    System.out.println("fight executing");

    switch (playerMove.name) {
      case "heal":
        battle.player.setCurHealth(battle.player.getCurHealth() + playerMove.value);
        System.out.println("Player healed by: " + playerMove.value + " HP");
        break;
      case "attack":
        System.out.println("Player attacking with " + playerMove.value + " DMG");
        break;
      case "dodge":
        System.out.println("Player dodging attack");
        break;
    }

    switch (opponentMove.name) {
      case "heal":
        battle.opponent.setCurHealth(battle.opponent.getCurHealth() + opponentMove.value);
        System.out.println("Opponent healed by " + opponentMove.value + " HP");
        break;
      case "attack":
        System.out.println("Opponent attacking with " + opponentMove.value + " DMG");
        break;
      case "dodge":
        System.out.println("Oponnent dodging attack");
        break;
    }

    if (playerMove.name == "attack" && opponentMove.name != "dodge") {
      battle.opponent.setCurHealth(battle.opponent.getCurHealth() - playerMove.value);
    }
    if (opponentMove.name == "attack" && playerMove.name != "dodge") {
      battle.player.setCurHealth(battle.player.getCurHealth() - opponentMove.value);
    }

    battle.getController().resetMove();
    isPlayerTurn = true;
    waiting = true;
  }
}