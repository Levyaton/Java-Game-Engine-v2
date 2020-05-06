package semestralka.utils;

import semestralka.models.creatures.Player;
import semestralka.view.GamePanel;

public class Camera {

  private int worldX, worldY;

  public void centerPlayer(Player player) {
    worldX = player.getPos().x - (GamePanel.width / 2) + 24;
    worldY = player.getPos().y - (GamePanel.height / 2) + 24;
  }

  public int getWorldX() {
    return worldX;
  }

  public int getWorldY() {
    return worldY;
  }
}