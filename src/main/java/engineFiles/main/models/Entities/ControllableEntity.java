package engineFiles.main.models.Entities;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class ControllableEntity extends Entity{

    public int getTEST_COUNTER() {
        return TEST_COUNTER;
    }

    public int TEST_COUNTER = 0;

    public ControllableEntity(MovementAnimation animation, JSONObject json, double speed){
        super(animation, json, speed);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    public ControllableEntity(MovementAnimation animation, File f, double speed){
        super(animation, f, speed);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    @Override
    public int getMovement(){
        this.TEST_COUNTER++;
        super.still = false;
        int choice;
        if (KeyMap.isPressed(super.controlls.getRight())) {
            choice = super.controlls.getRight().get(0);
            super.coord.moveRight();
        }
        else if (KeyMap.isPressed(super.controlls.getLeft())) {
            choice = super.controlls.getLeft().get(0);
            super.coord.moveLeft();

        }
        else if (KeyMap.isPressed(super.controlls.getDown())) {
            choice = super.controlls.getDown().get(0);
            super.coord.moveDown();

        }
        else if (KeyMap.isPressed(super.controlls.getUp())) {
            choice = super.controlls.getUp().get(0);
            super.coord.moveUp();
            //System.out.println(coord.getY());
        }
        else {
            //System.out.println("standing");
            choice = super.lastMovementIndex;
            super.lastMovementIndex = choice;
            super.still = true;
            super.animation.resetStateIndex();
        }

       // System.out.println(this.TEST_COUNTER);

        //System.out.println("NO KEY PRESSED");
        super.currentMovement = choice;
        return choice;
        //return super.controlls.getLeft().get(0);
    }


}
