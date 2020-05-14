package engineFiles.main.models.Entities;

public class MovementVector {

    private int x_Movement;
    private int y_Movement;
    private int time;

    public MovementVector(int x_Movement, int y_Movement, int time){
        this.x_Movement = x_Movement;
        this.y_Movement = y_Movement;
        this.time = time;
    }

    public int getX_Movement() {
        return x_Movement;
    }

    public int getY_Movement() {
        return y_Movement;
    }

    public int getTime() {
        return time;
    }
}
