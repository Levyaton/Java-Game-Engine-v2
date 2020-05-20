package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class VectorEntity extends Entity{


    public VectorEntity(MovementAnimation animation, JSONObject json, double speed){
        super(animation, json, speed);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    public VectorEntity(MovementAnimation animation, File f, double speed){
        super(animation, f, speed);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    @Override
    public int getMovement(){
        //IMPLEMENT MOVEMENT METHOD
        return 0;
    }

}
