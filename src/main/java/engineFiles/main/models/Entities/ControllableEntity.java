package engineFiles.main.models.Entities;

import engineFiles.main.game.KeyMap;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class ControllableEntity extends Entity{

    public ControllableEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, JSONObject json){
        super(down, up, left, right, json);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    public ControllableEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, File f){
        super(down, up, left, right, f);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
    }

    @Override
    public int getMovement(){

        if (KeyMap.isPressed(super.controlls.getRight())) {
            super.coord.moveRight();
            return 3;
        }
        if (KeyMap.isPressed(super.controlls.getLeft())) {
            super.coord.moveLeft();
            return 2;
        }
        if (KeyMap.isPressed(super.controlls.getDown())) {
            super.coord.moveDown();
           return 0;
        }
        if (KeyMap.isPressed(super.controlls.getUp())) {
            super.coord.moveUp();
            return 1;
        }
        super.animation.resetStateIndex();
        return animation.getMovementIndex();
    }


}
