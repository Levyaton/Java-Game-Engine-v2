package semestralka.battle;

import semestralka.models.creatures.Creature;

public class Move {
  public Creature creature;
  public String name;
  public int value;

  public Move(Creature creature, String name, int value) {
    this.creature = creature;
    this.name = name;
    this.value = value;
  }
}