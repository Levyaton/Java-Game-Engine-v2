package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Controlls;
import engineFiles.ui.Coordinates;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomingEntity extends Entity {
    Entity target;
    int range;


    private Controlls controllsInit(){
        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        up.add(1);
        down.add(0);
        left.add(2);
        right.add(3);
        return new Controlls(up,down,left,right);
    }

    public HomingEntity(MovementAnimation animation, JSONObject json, Entity target, int range, int speedCounter){
        super(animation, json, speedCounter);
        super.controlls = controllsInit();
        this.categoryName = "homing";
        super.others.add(target);
        this.target = target;
        this.range = range;
    }

    public HomingEntity(MovementAnimation animation, File f, Entity target, int range, int speedCounter){
        super(animation, f,speedCounter);
        super.controlls = controllsInit();
        this.categoryName = "homing";
        super.others.add(target);
        this.range = range;
        this.target = target;
    }

    @Override
    public int getMovement(){
        Rectangle scope = new Rectangle();
        scope.setBounds(this.getCoord().getX() - this.range/2, this.getCoord().getY() - this.range/2, range, range);
        //IMPLEMENT MOVEMENT METHOD
        Coordinates target = this.target.getCoord();
        super.still = false;
        int choice;

        int selfY = this.getCoord().getY();
        int selfX = this.getCoord().getX();

        int targetY = target.getY();
        int targetX = target.getX();

        int y = Math.abs(Math.abs(selfY) - Math.abs(targetY));
        int x = Math.abs(Math.abs(selfX) - Math.abs(targetX));
        if(!scope.contains(this.target.getCoord().getBounds())){
            choice = super.lastMovementIndex;
            super.lastMovementIndex = choice;
            super.still = true;
            super.animation.resetStateIndex();
        }
        else{

            if(y == x){
                int random = (int) Math.round( Math.random());
                choice = makeChoice(random, selfY, selfX, targetY,targetX);
            }
            else{

                int decider;
                if(y > x){

                    System.out.println("vertical: Y = " +y + " X = " + x);
                   decider = 0;
                }
                else{
                    System.out.println("horizontal: Y = " +y + " X = " + x);
                    decider = 1;
                }
                choice = makeChoice(decider, selfY, selfX, targetY, targetX);
            }
        }
       // System.out.println(choice);
        super.currentMovement = choice;
        return choice;
    }

    private int makeChoice(int decider, int selfY, int selfX, int targetY, int targetX){
        int choice = 0;
        switch (decider){
            case 0:
                if(selfY > targetY){
                    choice = super.controlls.getUp().get(0);
                    super.coord.moveUp();

                }
                else{
                    choice = super.controlls.getDown().get(0);
                    super.coord.moveDown();
                }
                break;
            case 1:
                if(selfX > targetX){
                    choice = super.controlls.getLeft().get(0);
                    super.coord.moveLeft();
                }
                else{
                    choice = super.controlls.getRight().get(0);
                    super.coord.moveRight();
                }
                break;
        }
        return choice;
    }

}
