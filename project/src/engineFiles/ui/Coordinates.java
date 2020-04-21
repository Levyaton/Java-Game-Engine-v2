package engineFiles.ui;

public class Coordinates {
    private int x_last;
    private int y_last;
    private int x;
    private int y;
    private int z;
    private int height;
    private int width;

    public Coordinates(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.x_last = x;
        this.y_last = y;
        this.width = width;
        this.height = height;
        this.z = 0;
    }

    public Coordinates(int x, int y, int z, int width, int height){
        this.x = x;
        this.y = y;
        this.x_last = x;
        this.y_last = y;
        this.width = width;
        this.height = height;
        this.z = z;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z){
        this.z = z;
    }

    public void setX(int x) {
        x_last = this.x;
        this.x = x;
    }

    public void setY(int y) {
        y_last = this.y;
        this.y = y;
    }

    public void moveUp(){
        y_last = this.y;
        this.y-= height;
    }

    public void moveUp(double mod){
        y_last = this.y;
        this.y-=(mod * height);
    }

    public void moveDown(){
        y_last = this.y;
        this.y+= height;
    }

    public void moveDown(double mod){
        y_last = this.y;
        this.y+=(mod*height);
    }

    public void moveLeft(){
        x_last = this.x;
        this.x-= width;
    }

    public void moveLeft(double mod) {
        x_last = this.x;
        this.x-=mod*width;
    }

    public void moveRight(){
        x_last = this.x;
        this.x+= width;
    }

    public void moveRight(double mod){
        x_last = this.x;
        this.x+=mod*width;
    }

}
