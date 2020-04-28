package semestralka.utils;

public class AABB {

  private int x, y;
  private int width, height;

  public AABB(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean isColliding(int x, int y) {
    return (x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height);
  }
}