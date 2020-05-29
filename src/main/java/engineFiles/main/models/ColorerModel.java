package engineFiles.main.models;

import com.google.gson.JsonObject;

import java.util.Random;

public class ColorerModel {
    String tilesetInputDir;
    String tilesetOutputDir;
    boolean recolor;
    int redShift;
    int greenShift;
    int blueShift;


    public ColorerModel(JsonObject json){
        tilesetInputDir = json.get("tilesetInputDir").toString();
        tilesetOutputDir = json.get("tilesetOutputDir").toString();
        recolor = json.get("recolor").getAsBoolean();
        if(json.has("redShift") && json.has("greenShift") && json.has("blueShift")){
            this.redShift = json.get("redShift").getAsInt();
            this.greenShift = json.get("greenShift").getAsInt();
            this.blueShift = json.get("blueShift").getAsInt();
        }
        else{
            this.redShift = new Random().nextInt(255);
            this.greenShift = new Random().nextInt(255);
            this.blueShift = new Random().nextInt(255);
        }

    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor){
        this.tilesetInputDir = tilesetInputDir;
        this.tilesetOutputDir = tilesetOutputDir;
        this.recolor = recolor;
        this.redShift = new Random().nextInt(255);
        this.greenShift = new Random().nextInt(255);
        this.blueShift = new Random().nextInt(255);
    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor, int redShift, int greenShift, int blueShift){
        this(tilesetInputDir,tilesetOutputDir,recolor);
        this.redShift = redShift;
        this.greenShift = greenShift;
        this.blueShift = blueShift;
    }


}
