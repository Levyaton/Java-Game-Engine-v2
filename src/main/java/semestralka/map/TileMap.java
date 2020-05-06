package semestralka.map;

import org.json.simple.JSONArray;

public class TileMap {

  private Tile[][] tiles;

  public TileMap(JSONArray data, int width, int height, int index) {
    tiles = new Tile[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        int id = ((Long) data.get(x + (y * width))).intValue();
        if (id != 0) {
          tiles[x][y] = new Tile(id - 1);
        }
      }
    }
  }

  public Tile getTile(int x, int y) {
    return tiles[x][y];
  }
}