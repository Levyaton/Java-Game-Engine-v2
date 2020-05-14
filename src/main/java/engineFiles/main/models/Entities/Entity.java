package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Controlls;
import engineFiles.ui.Sprite;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public abstract class Entity extends Sprite {

    protected MovementAnimation animation;
    protected Controlls controlls;
    protected List<Entity> others;
    protected String categoryName;

    int movementIndex;

    public Entity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, JSONObject json){
        super(json);
        this.animation = new MovementAnimation(down,up, left,right);
    }

    public Entity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, File f){
        super(f);
        this.animation = new MovementAnimation(down,up, left,right);
    }
    @Override
    public BufferedImage getImg(){
        return this.animation.move(getMovementIndex());

    }

    protected int getMovementIndex(){
        int chosenMovement = getMovement();
        movementIndex = 0;
        if(this.controlls.getUp().contains(chosenMovement)){
            movementIndex = 1;
        }
        else if(this.controlls.getLeft().contains(chosenMovement)){
            movementIndex = 2;
        }
        else if(this.controlls.getRight().contains(chosenMovement)){
            movementIndex = 3;
        }
        return movementIndex;
    }

    public int getMovement(){
        //IMPLEMENT MOVEMENT METHOD
        return 0;
    }

    public void addOtherEntity(Entity other){
        this.others.add(other);
    }


    public void movementBlocked(){
        switch (getMovementIndex()){
            case 0:
                super.getCoord().moveUp();
                break;
            case 1:
                super.getCoord().moveDown();
                break;
            case 2:
                super.getCoord().moveRight();
                break;
            case 3:
                super.getCoord().moveLeft();
                break;
        }
    }

    public String getCategoryName() {
        return categoryName;
    }
}
