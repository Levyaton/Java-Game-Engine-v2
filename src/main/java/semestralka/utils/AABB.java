package semestralka.utils;

public class AABB {

  public int x, y;
  public int width, height;

  public AABB(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public boolean isColliding(int x, int y) {
    return (x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.height);
  }

  public boolean isColliding(AABB bound) {
    return (((x + width) > bound.x) && (x < (bound.x + bound.width)) && ((y + height) > bound.y)
        && (y < (bound.y + bound.height)));
  }
}