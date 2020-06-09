package engineFiles.main.models;

import com.google.gson.JsonObject;
import engineFiles.ui.Settings;

import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

import static engineFiles.main.models.WorldGenKeys.ColorerKeys.*;

//Class containing the colorer model. Used to translate the color settings of the game to a json object, and vice-versa
public class ColorerModel {
    private static final Logger LOG = Logger.getLogger(ColorerModel.class.getName());
    private String tilesetInputDir;
    private String tilesetOutputDir;
    private boolean recolor;
    private int redShift;
    private int greenShift;
    private int blueShift;

    /**
     * @param json
     * 
     */
    public ColorerModel(JsonObject json) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        tilesetInputDir = json.get(TILESET_INPUT_DIR_KEY).getAsString();
        tilesetOutputDir = json.get(TILESET_OUTPUT_DIR_KEY).getAsString();
        recolor = json.get(RECOLOR_KEY).getAsBoolean();
        if (json.has(RED_SHIFT_KEY) && json.has(GREEN_SHIFT_KEY) && json.has(BLUE_SHIFT_KEY) && recolor) {
            this.redShift = json.get(RED_SHIFT_KEY).getAsInt();
            this.greenShift = json.get(GREEN_SHIFT_KEY).getAsInt();
            this.blueShift = json.get(BLUE_SHIFT_KEY).getAsInt();
        } else {
            this.redShift = new Random().nextInt(255);
            this.greenShift = new Random().nextInt(255);
            this.blueShift = new Random().nextInt(255);
        }

        updateColorerSettings();
        LOG.config("ColorerModel Initialized");
    }

    public void updateColorerSettings() {
        Settings.ColorerSettings.recolor = this.recolor;
        Settings.ColorerSettings.redShift = this.redShift;
        Settings.ColorerSettings.greenShift = this.greenShift;
        Settings.ColorerSettings.blueShift = this.blueShift;
        LOG.config("Colorer Setting updated");
    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor) {
        this.tilesetInputDir = tilesetInputDir;
        this.tilesetOutputDir = tilesetOutputDir;
        this.recolor = recolor;
        this.redShift = new Random().nextInt(255);
        this.greenShift = new Random().nextInt(255);
        this.blueShift = new Random().nextInt(255);
        updateColorerSettings();
        LOG.config("ColorerModel Initialized");
    }

    public ColorerModel(String tilesetInputDir, String tilesetOutputDir, boolean recolor, int redShift, int greenShift,
            int blueShift) {
        this(tilesetInputDir, tilesetOutputDir, recolor);
        this.redShift = redShift;
        this.greenShift = greenShift;
        this.blueShift = blueShift;
        updateColorerSettings();
        LOG.config("ColorerModel Initialized");
    }

    /**
     * @return String
     */
    public String getTilesetInputDir() {
        return tilesetInputDir;
    }

    /**
     * @return String
     */
    public String getTilesetOutputDir() {
        return tilesetOutputDir;
    }

    /**
     * @return boolean
     */
    public boolean isRecolor() {
        return recolor;
    }

    /**
     * @return int
     */
    public int getRedShift() {
        return redShift;
    }

    /**
     * @return int
     */
    public int getGreenShift() {
        return greenShift;
    }

    /**
     * @return int
     */
    public int getBlueShift() {
        return blueShift;
    }

    /**
     * @return JsonObject
     */
    public JsonObject getColorerModelJson() {
        JsonObject object = new JsonObject();
        object.addProperty(TILESET_INPUT_DIR_KEY, tilesetInputDir);
        object.addProperty(TILESET_OUTPUT_DIR_KEY, tilesetOutputDir);
        object.addProperty(RECOLOR_KEY, recolor);
        object.addProperty(RED_SHIFT_KEY, redShift);
        object.addProperty(GREEN_SHIFT_KEY, greenShift);
        object.addProperty(BLUE_SHIFT_KEY, blueShift);
        return object;
    }

    /**
     * @param recolor
     */
    public void setRecolor(boolean recolor) {
        this.recolor = recolor;
    }
}
