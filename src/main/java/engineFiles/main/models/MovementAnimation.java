package engineFiles.main.models;

import java.awt.image.BufferedImage;
import java.util.List;

public class MovementAnimation {

    private List<BufferedImage> down;
    private List<BufferedImage> up;
    private List<BufferedImage> left;
    private List<BufferedImage> right;

    private int movementIndex = 0;

    private int stateIndex = 0;

    private int animationSpeedMod = 10;

    private int animationCounter = 0;

    private int id;

    public MovementAnimation(){

    }

    public MovementAnimation(int id){
        this.id = id;
    }

    public MovementAnimation(int animationSpeedMod, int id){
        this(id);
        this.animationSpeedMod = animationSpeedMod;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, int id){
        this(id);
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, int animationSpeedMod, int id){
        this(down, up, left, right, id);
        this.animationSpeedMod = animationSpeedMod;
    }


    public int getAnimationSpeedMod() {
        return animationSpeedMod;
    }

    public void setAnimationSpeedMod(int animationSpeedMod) {
        this.animationSpeedMod = animationSpeedMod;
    }

    public List<BufferedImage> getDown() {
        return down;
    }

    public List<BufferedImage> getUp() {
        return up;
    }

    public List<BufferedImage> getLeft() {
        return left;
    }

    public List<BufferedImage> getRight() {
        return right;
    }



    public void setDown(List<BufferedImage> down) {
        this.down = down;
    }

    public void setUp(List<BufferedImage> up) {
        this.up = up;
    }

    public void setLeft(List<BufferedImage> left) {
        this.left = left;
    }

    public void setRight(List<BufferedImage> right) {
        this.right = right;
    }

    public BufferedImage getState(){
        return stateSelect(this.movementIndex).get(this.stateIndex);
    }

    public int getMovementIndex(){
        return movementIndex;
    }
    public void nextState(){
        if(changeState()){
            this.stateIndex = (stateIndex + 1) % stateSelect(movementIndex).size();
        }
    }

    private List<BufferedImage> stateSelect(int movementIndex){
        switch (movementIndex){
            case 0:
                return this.down;
            case 1:
                return this.up;
            case 2:
                return this.left;
            case 3:
                return this.right;
        }
        System.out.println(movementIndex);
        return null;
    }

    public BufferedImage move(int movementIndex){
        if(movementIndex != this.movementIndex){
            System.out.println("Change");
            this.movementIndex = movementIndex;
            resetStateIndex();
        }
        List<BufferedImage> currentMovement = stateSelect(movementIndex);
       // System.out.println(currentMovement.size());
        BufferedImage current = currentMovement.get(stateIndex);
        //System.out.println(movementIndex);
        nextState();
        return current;
    }

    private boolean changeState(){
        if(this.animationCounter == this.animationSpeedMod){
            this.animationCounter = 1;
            return true;
        }
        animationCounter++;
        return false;
    }

    public void resetStateIndex(){
        this.stateIndex = 1;
        this.animationCounter = 1;
    }

    public boolean addNextRow(List<BufferedImage> row){
        if(this.down == null){
            this.down = row;
            return true;
        }
        if(this.left == null){
            this.left = row;
            return true;
        }
        if(this.right == null){
            this.right = row;
            return true;
        }
        if(this.up == null){
            this.up = row;
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
