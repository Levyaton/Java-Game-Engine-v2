package engineFiles.main.models.Sprites.Entities;

//Class containing the vector logic used for movement in the overworld by npc's
public class Vector {
    int x;
    int y;
    boolean startWithX = true;

    public Vector(int x, int y, boolean startWithX) {
        this.x = x;
        this.y = y;
        this.startWithX = startWithX;
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return boolean
     */
    public boolean startsWithX() {
        return startWithX;
    }

    /**
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * @return int
     */
    public int getAbsX() {
        return Math.abs(this.x);
    }

    /**
     * @return int
     */
    public int getAbsY() {
        return Math.abs(this.y);
    }

    /**
     * @param startWithX
     */
    public void setStartWithX(boolean startWithX) {
        this.startWithX = startWithX;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

}
