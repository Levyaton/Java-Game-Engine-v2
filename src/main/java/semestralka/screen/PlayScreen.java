package semestralka.screen;

import java.awt.Graphics;
import semestralka.graphics.Resources;
import semestralka.models.TileMap;
import semestralka.models.dynamic.Player;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.utils.Position;
import semestralka.view.GamePanel;

public class PlayScreen extends Screen {

  private Player player;

  private TileMap map;

  public PlayScreen(ScreenManager screenManager, KeyManager keyManager) {
    map = new TileMap();
    player = new Player(Resources.player, Resources.player_run_up, Resources.player_run_down, Resources.player_run_left,
        Resources.player_run_right, new Position(0, 0));
  }

  @Override
  public void input(KeyManager keymanager, MouseManager mouseManager) {
    player.input(keymanager);
  }

  @Override
  public void update() {
    player.update();
  }

  @Override
  public void render(Graphics g) {
    for (int x = 0; x < (GamePanel.width / 48); x++) {
      for (int y = 0; y < (GamePanel.height / 48); y++) {
        g.drawImage(map.getTile(x, y), x * 48, y * 48, 48, 48, null);
      }
    }
    // drawGrid(g);
    player.render(g);
  }
}