package ui;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveUp(){
        this.y++;
    }

    public void moveUp(int mod){
        this.y+=mod;
    }

    public void moveDown(){
        this.y--;
    }

    public void moveDown(int mod){
        this.y-=mod;
    }

}
