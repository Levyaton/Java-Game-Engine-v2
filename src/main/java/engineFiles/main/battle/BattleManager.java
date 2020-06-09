package engineFiles.main.battle;

import engineFiles.GUIs.mainGameGui.BattlePanel;

import java.util.Stack;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * The object BattleManager deals with battle states by pushing the next moves
 * into move stack and executing the top one on the stack
 *
 */
public class BattleManager {
  private static final Logger LOG = Logger.getLogger(BattleManager.class.getName());
  private BattlePanel battlePanel;
  private Stack<Move> moveStack;

  private AI ai;

  /**
   * Initalize AI and move stack
   * 
   * @param battlePanel
   * 
   */
  public BattleManager(BattlePanel battlePanel) {
    this.battlePanel = battlePanel;
    this.ai = new AI();
    moveStack = new Stack<Move>();
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    LOG.config("BattleManager Initialized");
  }

  /**
   * Pushes a move to the move stack for player and gets a random move for the
   * oponnent
   * 
   * @param move
   * 
   */
  public void push(Move move) {
    LOG.info("Move pushed to stack");
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
   * Executes the move from the move stack. If one entity attacks and the other
   * uses dodge, the attack gets nullified. After each move a dialog is pushed to
   * the component stack and the appropiete update is executed
   * 
   * @param move
   * 
   */
  public void executeMove(Move move) {
    moveStack.pop();
    switch (move.type) {
      case ATTACK:
        LOG.info(move.entity.getName() + " attacking");
        if (!moveStack.isEmpty() && moveStack.peek().type == MoveEnum.DODGE) {
          LOG.info(move.entity.getName() + " dodged");
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
        LOG.info(move.entity.getName() + " uses an item");
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
        LOG.info(move.entity.getName() + " dodged");
        if (!moveStack.isEmpty() && moveStack.peek().type == MoveEnum.ATTACK) {
          battlePanel.pushDialog(
              move.entity.getCategoryName() + " dodged an attacked by " + moveStack.peek().entity.getCategoryName());
          moveStack.pop();
        }

        battlePanel.pushDialog(move.entity.getCategoryName() + " dodges attack");
        break;
    }

    LOG.info("Battle move executed");
  }
}