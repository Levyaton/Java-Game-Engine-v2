package engineFiles.main.models.Entities;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class ControllableEntity extends Entity{


    public ControllableEntity(MovementAnimation animation, JSONObject json){
        super(animation, json);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    public ControllableEntity(MovementAnimation animation, File f){
        super(animation, f);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    @Override
    public int getMovement(){
        int choice = 666;
        if (KeyMap.isPressed(super.controlls.getRight())) {
            choice = super.controlls.getRight().get(0);
            super.coord.moveRight();
        }
        if (KeyMap.isPressed(super.controlls.getLeft())) {
            choice = super.controlls.getLeft().get(0);
            super.coord.moveLeft();

        }
        if (KeyMap.isPressed(super.controlls.getDown())) {
            choice = super.controlls.getDown().get(0);
            super.coord.moveDown();

        }
        if (KeyMap.isPressed(super.controlls.getUp())) {
            choice = super.controlls.getUp().get(0);
            super.coord.moveUp();

        }
        if(choice == 666){
            choice = super.lastMovementIndex;
            super.lastMovementIndex = choice;
            super.animation.resetStateIndex();
        }

        //System.out.println("NO KEY PRESSED");

        return choice;
    }


}
