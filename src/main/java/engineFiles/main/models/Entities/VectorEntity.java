package engineFiles.main.models.Entities;

import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class VectorEntity extends Entity{


    public VectorEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, JSONObject json){
        super(down, up, left, right, json);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    public VectorEntity(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, File f){
        super(down, up, left, right, f);
        super.controlls = Settings.controlls;
        this.categoryName = "vector";
    }

    @Override
    public int getMovement(){
        //IMPLEMENT MOVEMENT METHOD
        return 0;
    }

}
