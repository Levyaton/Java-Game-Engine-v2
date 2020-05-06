package semestralka.world;

import java.awt.Graphics;

import semestralka.graphics.Resources;
import semestralka.map.Map;
import semestralka.models.creatures.Enemy;
import semestralka.models.creatures.Player;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;
import semestralka.utils.Camera;

public class World {

  private Player player;
  private CreaturesManager creatureManager;
  private Map map;
  private Camera camera;

  public World() {
    creatureManager = new CreaturesManager();
    camera = new Camera();
    player = new Player(Resources.player, new Position(200, 250), this);
    Enemy skeleton = new Enemy(Resources.skeleton, new Position(96, 96), this);
    creatureManager.add(player);
    creatureManager.add(skeleton);
    map = new Map("/maps/tilemap_1.json", this);
  }

  public void input(KeyManager keyManager, MouseManager mouseManager) {
    player.input(keyManager);
  }

  public void update() {
    map.update();
  }

  public void render(Graphics g) {
    map.render(g);
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
}