package semestralka.graphics;

import java.awt.image.BufferedImage;

public class Animation {

  private BufferedImage[] stand;
  private BufferedImage[] runUp;
  private BufferedImage[] runDown;
  private BufferedImage[] runLeft;
  private BufferedImage[] runRight;
  private int current;
  private int tick = 0;

  public Animation(BufferedImage[] stand, BufferedImage[] runUp, BufferedImage[] runDown, BufferedImage[] runLeft,
      BufferedImage[] runRight) {
    this.stand = stand;
    this.runUp = runUp;
    this.runDown = runDown;
    this.runLeft = runLeft;
    this.runRight = runRight;
  }

  public void update() {
    tick++;
    if (tick == 15) {
      current++;
      tick = 0;
      if (current >= 2) {
        current = 0;
      }
    }
  }

  public BufferedImage getStandFrame(int direction) {
    return stand[direction];
  }

  public BufferedImage getRunFrame(int direction) {
    if (direction == 0) {
      return runUp[current];
    }
    if (direction == 1) {
      return runDown[current];
    }
    if (direction == 2) {
      return runLeft[current];
    } else {
      return runRight[current];
    }
  }
}