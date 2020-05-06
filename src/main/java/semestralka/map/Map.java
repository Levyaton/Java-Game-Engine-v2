package semestralka.map;

import java.awt.Graphics;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import semestralka.view.GamePanel;
import semestralka.world.CreaturesManager;
import semestralka.world.World;

public class Map {

  private TileMap[] map;
  int width, height;
  int pixWidth, pixHeight;
  private World world;

  private CreaturesManager creaturesManager;

  public Map(String path, World world) {
    map = new TileMap[2];
    creaturesManager = world.getCreatureManager();
    this.world = world;
    loadMap(path);
  }

  public void update() {
    creaturesManager.update();
  }

  public void render(Graphics g) {
    int worldX = world.getCamera().getWorldX();
    int worldY = world.getCamera().getWorldY();

    int startX = Math.max(worldX / 48, 0);
    int endX = Math.min(((worldX + GamePanel.width) / 48) + 1, width);
    int startY = Math.max(worldY / 48, 0);
    int endY = Math.min(((worldY + GamePanel.height) / 48) + 1, height);

    for (int x = startX; x < endX; x++) {
      for (int y = startY; y < endY; y++) {
        Tile block = map[0].getTile(x, y);
        if (block != null) {
          g.drawImage(block.tile, (x * 48) - worldX, (y * 48) - worldY, 48, 48, null);
        }
      }
    }

    creaturesManager.render(g);

    for (int x = startX; x < endX; x++) {
      for (int y = startY; y < endY; y++) {
        Tile block = map[1].getTile(x, y);
        if (block != null) {
          g.drawImage(block.tile, (x * 48) - worldX, (y * 48) - worldY, 48, 48, null);
        }
      }
    }
  }

  public void loadMap(String path) {

    JSONObject jsonObj = new JSONLoader().load(path);
    width = ((Long) jsonObj.get("width")).intValue();
    height = ((Long) jsonObj.get("height")).intValue();
    pixWidth = width * 48;
    pixHeight = height * 48;

    JSONArray layers = (JSONArray) jsonObj.get("layers");
    Iterator arr = layers.iterator();
    int i = 0;

    while (arr.hasNext()) {
      JSONObject layer = (JSONObject) arr.next();
      JSONArray data = (JSONArray) layer.get("data");
      map[i] = new TileMap(data, width, height, i);
      i++;
    }
  }

  public boolean isTileSolid(int x, int y) {
    if (x >= pixWidth || x < 0 || y >= pixHeight || y < 0) {
      return false;
    }
    return (map[1].getTile(x / 48, y / 48) != null);
  }
}