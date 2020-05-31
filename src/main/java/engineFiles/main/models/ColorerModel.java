package engineFiles.main.models;

import com.google.gson.JsonObject;

import java.util.Random;

import static engineFiles.main.models.WorldGenKeys.ColorerKeys.*;

public class ColorerModel {
    private String tilesetInputDir;
    private String tilesetOutputDir;
    private boolean recolor;
    private int redShift;
    private int greenShift;
    private int blueShift;


    public ColorerModel(JsonObject json){
        tilesetInputDir = json.get(TILESET_INPUT_DIR_KEY).toString();
        tilesetOutputDir = json.get(TILESET_OUTPUT_DIR_KEY).toString();
        recolor = json.get(RECOLOR_KEY).getAsBoolean();
        if(json.has(RED_SHIFT_KEY) && json.has(GREEN_SHIFT_KEY) && json.has(BLUE_SHIFT_KEY)){
            this.redShift = json.get(RED_SHIFT_KEY).getAsInt();
            this.greenShift = json.get(GREEN_SHIFT_KEY).getAsInt();
            this.blueShift = json.get(BLUE_SHIFT_KEY).getAsInt();
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

    public String getTilesetInputDir() {
        return tilesetInputDir;
    }

    public String getTilesetOutputDir() {
        return tilesetOutputDir;
    }

    public boolean isRecolor() {
        return recolor;
    }

    public int getRedShift() {
        return redShift;
    }

    public int getGreenShift() {
        return greenShift;
    }

    public int getBlueShift() {
        return blueShift;
    }
}
