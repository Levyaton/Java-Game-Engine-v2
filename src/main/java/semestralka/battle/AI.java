package semestralka.battle;

import semestralka.models.creatures.Creature;

public class AI {

  public Move getNextMove(Creature creature, String kind) {
    return new Move(creature, "attack", 1);
  }
}