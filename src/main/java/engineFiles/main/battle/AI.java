package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.Random;
import java.util.logging.Logger;

//Class containing the ai behaviour
public class AI {
  private static final Logger LOG = Logger.getLogger(AI.class.getName());
  private Random rand;
  private Item[] randomItems;

  public AI() {
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
   */
  public Move getNextMove(Entity entity) {
    int randomInt = rand.nextInt(10);
    if (randomInt > 5) {
      return new Move(entity, MoveEnum.ATTACK, entity.getDamage() + entity.getBattleDamage());
    } else if (randomInt == 0) {
      return new Move(entity, MoveEnum.ITEM, randomItems[rand.nextInt(4)]);
    } else {
      return new Move(entity, MoveEnum.DODGE, 0);
    }
  }
}