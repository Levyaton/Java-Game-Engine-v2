package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class HomingEntity extends Entity {
    Entity target;

    public HomingEntity(MovementAnimation animation, JSONObject json, Entity target){
        super(animation, json);
        super.controlls = Settings.controlls;
        this.categoryName = "homing";
        super.others.add(target);
        this.target = target;
    }

    public HomingEntity(MovementAnimation animation, File f, Entity target){
        super(animation, f);
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
