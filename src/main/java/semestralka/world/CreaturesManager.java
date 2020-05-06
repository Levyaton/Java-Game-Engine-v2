package semestralka.world;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import semestralka.models.creatures.Creature;

public class CreaturesManager {

  private List<Creature> creatures;

  public CreaturesManager() {
    creatures = new ArrayList<>();
  }

  public void update() {
    for (Creature creature : creatures) {
      creature.update();
    }
  }

  public void render(Graphics g) {
    for (Creature creature : creatures) {
      creature.render(g);
    }
  }

  public void add(Creature creature) {
    creatures.add(creature);
  }

  public List<Creature> getCreatures() {
    return creatures;
  }
}