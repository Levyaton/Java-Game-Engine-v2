package semestralka.map;

import java.awt.Graphics;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import semestralka.world.World;

public class Map {

  private TileMap[] map;
  int width, height;
  int pixWidth, pixHeight;
  private World world;

  public Map(String path, World world) {
    map = new TileMap[2];
    this.world = world;
    loadMap(path);
  }

  public void update() {

  }

  public void groundRender(Graphics g) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (map[0].getTile(x, y) != null) {
          g.drawImage(map[0].getTile(x, y).tile, (x * 48) - world.getCamera().getWorldX(),
              (y * 48) - world.getCamera().getWorldY(), 48, 48, null);
        }
      }
    }
  }

  public void solidRender(Graphics g) {
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (map[1].getTile(x, y) != null) {
          g.drawImage(map[1].getTile(x, y).tile, (x * 48) - world.getCamera().getWorldX(),
              (y * 48) - world.getCamera().getWorldY(), 48, 48, null);
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
    System.out.println("x:" + x / 48 + ",y:" + y / 48);
    return (map[1].getTile(x / 48, y / 48) != null);
  }
}