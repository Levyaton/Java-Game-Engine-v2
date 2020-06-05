package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;

public class Move {

  public Entity entity;
  public String name;
  public int value;

  public Move(Entity entity, String name, int value) {
    this.entity = entity;
    this.name = name;
    this.value = value;
  }
}