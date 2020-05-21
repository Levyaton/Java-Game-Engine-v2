package engineFiles.main.models.Entities;

import engineFiles.main.models.MovementAnimation;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class VectorEntity extends Entity{


    public VectorEntity(MovementAnimation animation, JSONObject json, int speedCounter){
        super(animation, json, speedCounter);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    public VectorEntity(MovementAnimation animation, File f, int speedCounter){
        super(animation, f, speedCounter);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    @Override
    public int getMovement(){
        //IMPLEMENT MOVEMENT METHOD
        return 0;
    }

}
