package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Items.Item;


//A class containing
public class Move {

  public Entity entity;
  public MoveEnum type;
  public Item item;
  public int value;

  public Move(Entity entity, MoveEnum type) {
    this.entity = entity;
    this.type = type;
  }

  public Move(Entity entity, MoveEnum type, int value) {
    this.entity = entity;
    this.type = type;
    this.value = value;
  }

  public Move(Entity entity, MoveEnum type, Item item) {
    this.entity = entity;
    this.type = type;
    this.item = item;
  }
}