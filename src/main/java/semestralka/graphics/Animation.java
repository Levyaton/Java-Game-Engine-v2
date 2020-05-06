package semestralka.graphics;

import java.awt.image.BufferedImage;

public class Animation {

  private BufferedImage[][] frames;
  private int current;
  private int tick = 0;

  public Animation(BufferedImage[][] frames) {
    this.frames = frames;
  }

  public void update() {
    tick++;
    if (tick == 15) {
      current += 2;
      tick = 0;
      if (current >= 4) {
        current = 0;
      }
    }
  }

  public BufferedImage getStandFrame(int direction) {
    return frames[1][direction];
  }

  public BufferedImage getRunFrame(int direction) {
    return frames[current][direction];
  }
}