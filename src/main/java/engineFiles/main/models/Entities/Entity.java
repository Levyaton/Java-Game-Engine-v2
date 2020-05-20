package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Controlls;
import engineFiles.ui.Sprite;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class Entity extends Sprite {

    protected int lastMovementIndex = 0;
    protected MovementAnimation animation;
    protected Controlls controlls;
    protected List<Entity> others = new ArrayList<>();
    protected String categoryName;
    protected boolean still = false;
    protected int currentMovement;
    protected int health;
    protected int damage;

    int movementIndex;

    public Entity(MovementAnimation animation, JSONObject json, double speed){
        super(json);
        this.animation = animation;
        this.coord.setMOD(speed);
    }

    public Entity(MovementAnimation animation, File f, double speed){
        super(f);
        this.animation = animation;
        this.coord.setMOD(speed);
    }
    @Override
    public BufferedImage getImg(){
         int movementIndex = getMovementIndex();
         //System.out.println(movementIndex);
       // System.out.println("["+ this.getCoord().getX() + "," + getCoord().getY() + "]");
          return this.animation.move(movementIndex, still);
       // return this.animation.getDown().get(0);
    }

    protected int getMovementIndex(){
        int chosenMovement = currentMovement;
        //System.out.println("Movement is " + chosenMovement);
        //movementIndex = 0;
        if(this.controlls.getUp().contains(chosenMovement)){
            movementIndex = 1;
        }
        else if(this.controlls.getLeft().contains(chosenMovement)){
            movementIndex = 2;
        }
        else if(this.controlls.getRight().contains(chosenMovement)){
            movementIndex = 3;
        }
        else if(this.controlls.getDown().contains(chosenMovement)){
            movementIndex = 0;
        }
        //lastMovementIndex = movementIndex;
        return movementIndex;
    }

    public abstract int getMovement();

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

    public MovementAnimation getAnimation() {
        return animation;
    }
}
