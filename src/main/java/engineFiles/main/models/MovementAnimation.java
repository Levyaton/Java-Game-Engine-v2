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

    public MovementAnimation(){

    }

    public MovementAnimation(int animationSpeedMod){
        this.animationSpeedMod = animationSpeedMod;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right){
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, int animationSpeedMod){
        this(down, up, left, right);
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
        return null;
    }

    public BufferedImage move(int movementIndex){
        if(movementIndex != this.movementIndex){
            this.movementIndex = movementIndex;
            resetStateIndex();
        }
        BufferedImage current = stateSelect(movementIndex).get(stateIndex);
        nextState();
        return current;
    }

    private boolean changeState(){
        if(this.animationCounter == this.animationSpeedMod){
            this.animationCounter = 0;
            return true;
        }
        animationCounter++;
        return false;
    }

    public void resetStateIndex(){
        this.stateIndex = 0;
        this.animationCounter = 0;
    }
}
