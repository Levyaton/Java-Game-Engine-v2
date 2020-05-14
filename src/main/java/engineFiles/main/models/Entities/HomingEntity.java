package engineFiles.main.models.Entities;

import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class HomingEntity extends Entity {
    Entity target;

    public HomingEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, JSONObject json, Entity target){
        super(down, up, left, right, json);
        super.controlls = Settings.controlls;
        this.categoryName = "homing";
        super.others.add(target);
        this.target = target;
    }

    public HomingEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, File f, Entity target){
        super(down, up, left, right, f);
        super.controlls = Settings.controlls;
        this.categoryName = "homing";
        super.others.add(target);
        this.target = target;
    }

    @Override
    public int getMovement(){
        //IMPLEMENT MOVEMENT METHOD
        return 0;
    }

}
