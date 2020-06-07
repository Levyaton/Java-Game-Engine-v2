package engineFiles.main.models;

import com.google.gson.JsonObject;
import engineFiles.ui.Settings;

import java.util.Random;

import static engineFiles.main.models.WorldGenKeys.ColorerKeys.*;

//Class containing the colorer model. Used to translate the color settings of the game to a json object, and vice-versa
public class ColorerModel {
    private String tilesetInputDir;
    private String tilesetOutputDir;
    private boolean recolor;
    private int redShift;
    private int greenShift;
    private int blueShift;


    public ColorerModel(JsonObject json){
        tilesetInputDir = json.get(TILESET_INPUT_DIR_KEY).getAsString();
        tilesetOutputDir = json.get(TILESET_OUTPUT_DIR_KEY).getAsString();
        recolor = json.get(RECOLOR_KEY).getAsBoolean();
        if(json.has(RED_SHIFT_KEY) && json.has(GREEN_SHIFT_KEY) && json.has(BLUE_SHIFT_KEY) && recolor){
            this.redShift = json.get(RED_SHIFT_KEY).getAsInt();
            this.greenShift = json.get(GREEN_SHIFT_KEY).getAsInt();
            this.blueShift = json.get(BLUE_SHIFT_KEY).getAsInt();
        }
        else{
            this.redShift = new Random().nextInt(255);
            this.greenShift = new Random().nextInt(255);
            this.blueShift = new Random().nextInt(255);
        }

        updateColorerSettings();
    }

    public void updateColorerSettings(){
        Settings.ColorerSettings.recolor = this.recolor;
        Settings.ColorerSettings.redShift = this.redShift;
        Settings.ColorerSettings.greenShift = this.greenShift;
        Settings.ColorerSettings.blueShift = this.blueShift;
    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor){
        this.tilesetInputDir = tilesetInputDir;
        this.tilesetOutputDir = tilesetOutputDir;
        this.recolor = recolor;
        this.redShift = new Random().nextInt(255);
        this.greenShift = new Random().nextInt(255);
        this.blueShift = new Random().nextInt(255);
        updateColorerSettings();
    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor, int redShift, int greenShift, int blueShift){
        this(tilesetInputDir,tilesetOutputDir,recolor);
        this.redShift = redShift;
        this.greenShift = greenShift;
        this.blueShift = blueShift;
        updateColorerSettings();
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

    public JsonObject getColorerModelJson(){
        JsonObject object = new JsonObject();
        object.addProperty(TILESET_INPUT_DIR_KEY, tilesetInputDir);
        object.addProperty(TILESET_OUTPUT_DIR_KEY, tilesetOutputDir);
        object.addProperty(RECOLOR_KEY, recolor);
        object.addProperty(RED_SHIFT_KEY, redShift);
        object.addProperty(GREEN_SHIFT_KEY, greenShift);
        object.addProperty(BLUE_SHIFT_KEY, blueShift);
        return object;
    }

    public void setRecolor(boolean recolor) {
        this.recolor = recolor;
    }
}
