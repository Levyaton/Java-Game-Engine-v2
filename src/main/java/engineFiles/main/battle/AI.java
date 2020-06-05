package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;

public class AI {

  public Move getNextMove(Entity entity, String kind) {
    return new Move(entity, "attack", 1);
  }
}