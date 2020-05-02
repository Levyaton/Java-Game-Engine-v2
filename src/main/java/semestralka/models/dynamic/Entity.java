package semestralka.models.dynamic;

import semestralka.utils.Position;

public abstract class Entity {

  protected Position pos;

  public Entity(Position pos) {
    this.pos = pos;
  }
}