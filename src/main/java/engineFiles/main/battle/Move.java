package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * The Move object store the data required for battle execution in the move
 * stack.
 *
 */
public class Move {
  private static final Logger LOG = Logger.getLogger(Move.class.getName());
  public Entity entity;
  public MoveEnum type;
  public Item item;
  public int value;

  /**
   * @param entity
   * @param type
   * 
   */
  public Move(Entity entity, MoveEnum type) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    this.entity = entity;
    this.type = type;
    LOG.config("Move Initialized");
  }

  /**
   * @param entity
   * @param type
   * @param value
   * 
   */
  public Move(Entity entity, MoveEnum type, int value) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    this.entity = entity;
    this.type = type;
    this.value = value;
    LOG.config("Move Initialized");
  }

  /**
   * @param entity
   * @param type
   * @param item
   * 
   */
  public Move(Entity entity, MoveEnum type, Item item) {
    LOG.setUseParentHandlers(false);
    Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
      @Override
      public void publish(LogRecord record) {
        super.publish(record);
        flush();
      }
    };
    LOG.addHandler(stdout);
    this.entity = entity;
    this.type = type;
    this.item = item;
    LOG.config("Move Initialized");
  }
}