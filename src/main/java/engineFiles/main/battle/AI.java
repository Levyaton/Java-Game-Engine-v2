package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * The AI object goal is to randomly pick a move. AI can have an attack / dodge
 * / item move. The items which it picks from is fixly created on object
 * initliaziation.
 *
 */
public class AI {
  private static final Logger LOG = Logger.getLogger(AI.class.getName());
  private Random rand;
  private Item[] randomItems;

  /**
   * Creates a list of items to choose from randomize choice
   * 
   */
  public AI() {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    rand = new Random();
    randomItems = new Item[4];
    randomItems[0] = new Item("Fist of Odin", 0, 0, 2, "+2 DMG");
    randomItems[1] = new Item("Hunter Dagger", 0, 0, 1, "+1 DMG");
    randomItems[2] = new Item("Spirit stone", 3, 0, 0, "+3 HP");
    randomItems[3] = new Item("Health potion", 5, 0, 0, "+5 DMG");
    LOG.config("AI Initialized");
  }

  /**
   * @param entity
   * @return Move
   * 
   *         Generates a random number and based on probability it gets the next
   *         move. Item move has a 10% chance of picking
   */
  public Move getNextMove(Entity entity) {
    int randomInt = rand.nextInt(10);
    if (randomInt > 5) {
      LOG.info("AI will attack");
      return new Move(entity, MoveEnum.ATTACK, entity.getDamage() + entity.getBattleDamage());
    } else if (randomInt == 0) {
      LOG.info("AI will use item");
      return new Move(entity, MoveEnum.ITEM, randomItems[rand.nextInt(4)]);
    } else {
      LOG.info("AI will dodge");
      return new Move(entity, MoveEnum.DODGE, 0);
    }
  }
}