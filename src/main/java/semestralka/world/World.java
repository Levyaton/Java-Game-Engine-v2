package semestralka.world;

import java.awt.Graphics;
import semestralka.battle.Battle;
import semestralka.graphics.Resources;
import semestralka.map.Map;
import semestralka.models.creatures.Skeleton;
import semestralka.models.creatures.Player;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;

public class World {

  private Player player;
  private CreaturesManager creatureManager;
  private Map map;
  private Camera camera;
  private Inventory inventory;
  private Battle battle;

  public World() {
    creatureManager = new CreaturesManager();
    camera = new Camera();
    inventory = new Inventory();
    battle = new Battle(this);
    player = new Player("alex", Resources.player, new Position(200, 250), this);
    Skeleton skeleton = new Skeleton("skeleton joe", Resources.skeleton, new Position(96, 96), this);
    creatureManager.add(player);
    creatureManager.add(skeleton);
    map = new Map("/maps/tilemap_1.json", this);
  }

  public void input(KeyManager keyManager, MouseManager mouseManager) {
    if (battle.isInBattle()) {
      battle.input(keyManager);
    } else {
      player.input(keyManager);
    }
  }

  public void update() {
    if (battle.isInBattle()) {
      battle.update();
    } else {
      map.update();
    }
  }

  public void render(Graphics g) {
    if (battle.isInBattle()) {
      battle.render(g);
    } else {
      map.render(g);
    }
  }

  public CreaturesManager getCreatureManager() {
    return creatureManager;
  }

  public Map getMap() {
    return map;
  }

  public Camera getCamera() {
    return camera;
  }

  public Inventory getInventory() {
    return inventory;
  }

  public Battle getBattle() {
    return battle;
  }

  public Player getPlayer() {
    return player;
  }
}