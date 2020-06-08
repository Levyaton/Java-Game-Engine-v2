package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.logging.Logger;


//A class containing the move logic used in battles
public class Move {
  private static final Logger LOG = Logger.getLogger(Move.class.getName());
  public Entity entity;
  public MoveEnum type;
  public Item item;
  public int value;

  public Move(Entity entity, MoveEnum type) {
    this.entity = entity;
    this.type = type;
    LOG.config("Move Initialized");
  }

  public Move(Entity entity, MoveEnum type, int value) {
    this.entity = entity;
    this.type = type;
    this.value = value;
    LOG.config("Move Initialized");
  }

  public Move(Entity entity, MoveEnum type, Item item) {
    this.entity = entity;
    this.type = type;
    this.item = item;
    LOG.config("Move Initialized");
  }
}