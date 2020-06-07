package engineFiles.main.battle;

import engineFiles.main.models.Sprites.Entities.Entity;


//Class containing the ai behaviour
public class AI {

  public Move getNextMove(Entity entity, String kind) {
    return new Move(entity, MoveEnum.ATTACK, entity.getDamage());
  }
}