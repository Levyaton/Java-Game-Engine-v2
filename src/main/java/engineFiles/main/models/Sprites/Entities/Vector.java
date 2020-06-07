package engineFiles.main.models.Sprites.Entities;


//Class containing the vector logic used for movement in the overworld by npc's
public class Vector {
    int x;
    int y;
    boolean startWithX = true;

    public Vector(int x, int y, boolean startWithX){
        this.x = x;
        this.y = y;
        this.startWithX = startWithX;
    }

    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean startsWithX() {
        return startWithX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAbsX(){
        return Math.abs(this.x);
    }

    public int getAbsY(){
        return Math.abs(this.y);
    }

    public void setStartWithX(boolean startWithX) {
        this.startWithX = startWithX;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


}
