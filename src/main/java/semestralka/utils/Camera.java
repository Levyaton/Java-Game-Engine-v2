package semestralka.utils;

import semestralka.view.GamePanel;

public class Camera {

  private int worldX, worldY;

  public void centerPlayer(int x, int y) {
    worldX = x - (GamePanel.width / 2) + 24;
    worldY = y - (GamePanel.height / 2) + 24;
  }

  public int getWorldX() {
    return worldX;
  }

  public int getWorldY() {
    return worldY;
  }
}