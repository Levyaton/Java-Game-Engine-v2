package engineFiles.ui;

import java.awt.*;

//Class containing coordinate logic
public class Coordinates {
    private int x_last;
    private int y_last;
    private int x;
    private int y;
    private int z;
    private int height;
    private int width;
    private double MOD = Settings.MOVEMENT_SPEED;

    /**
     * @param MOD
     */
    public void setMOD(double MOD) {
        this.MOD = MOD;
    }

    public Coordinates(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.x_last = x;
        this.y_last = y;
        this.width = width;
        this.height = height;
        this.z = 0;
    }

    public Coordinates(int x, int y, int z, int width, int height) {
        this.x = x;
        this.y = y;
        this.x_last = x;
        this.y_last = y;
        this.width = width;
        this.height = height;
        this.z = z;
    }

    /**
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        x_last = this.x;
        this.x = x;
    }

    /**
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        y_last = this.y;
        this.y = y;
    }

    /**
     * @return int
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z
     */
    public void setZ(int z) {
        this.z = z;
    }

    public void moveUp() {
        y_last = this.y;
        // this.y-=
        this.y -= MOD;

    }

    /**
     * @param mod
     */
    public void moveUp(double mod) {
        y_last = this.y;
        this.y -= (mod * height);
    }

    public void moveDown() {
        y_last = this.y;
        // this.y+= height;
        this.y += MOD;
    }

    /**
     * @param mod
     */
    public void moveDown(double mod) {
        y_last = this.y;
        this.y += (mod * height);
    }

    public void moveLeft() {
        x_last = this.x;
        // this.x-= width;
        this.x -= MOD;
    }

    /**
     * @param mod
     */
    public void moveLeft(double mod) {
        x_last = this.x;
        this.x -= mod * width;
    }

    public void moveRight() {
        x_last = this.x;
        // this.x+= width;
        this.x += MOD;
    }

    /**
     * @param mod
     */
    public void moveRight(double mod) {
        x_last = this.x;
        this.x += mod * width;
    }

    /**
     * @return double
     */
    public double getMOD() {
        return MOD;
    }

    /**
     * @return Rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
}
