package semestralka.models;

import semestralka.utils.Position;
import semestralka.utils.AABB;

public abstract class Entity {

  protected Position pos;
  protected AABB bounds;

  public Entity(Position pos) {
    this.pos = pos;
    bounds = new AABB(5, 5, 40, 40);
  }

  public Position getPos() {
    return pos;
  }

  public AABB getBounds() {
    return bounds;
  }
}