package engineFiles.main.models;

import com.google.gson.JsonObject;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

import static engineFiles.main.models.WorldGenKeys.CharacterSpriteSheetKeys.*;

//Class containing the spritesheet logic. Used to translate spritesheet information into json objects and vice versa
public class CharacterSpriteSheetModel {
    private static final Logger LOG = Logger.getLogger(CharacterSpriteSheetModel.class.getName());
    private int rowCount;
    private int columnCount;
    private int spriteWidth;
    private int spriteHeight;
    private int entityIndex;
    private String sheetPath;

    public CharacterSpriteSheetModel(int rowCount, int columnCount, int spriteWidth, int spriteHeight, int entityIndex,
            String sheetPath) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.entityIndex = entityIndex;
        this.sheetPath = sheetPath;
        LOG.config("CharacterSpriteSheetModel Initialized");
    }

    public CharacterSpriteSheetModel(JsonObject json) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.rowCount = json.get(ROW_COUNT_KEY).getAsInt();
        this.columnCount = json.get(COLUMN_COUNT_KEY).getAsInt();
        this.spriteHeight = json.get(SPRITE_HEIGHT_KEY).getAsInt();
        this.spriteWidth = json.get(SPRITE_WIDTH_KEY).getAsInt();
        this.entityIndex = json.get(ENTITY_INDEX_KEY).getAsInt();
        this.sheetPath = json.get(SHEET_PATH_KEY).getAsString();
        LOG.config("CharacterSpriteSheetModel Initialized");
    }

    /**
     * @return JsonObject
     */
    public JsonObject getCharacterSpriteSheetModelJson() {
        JsonObject model = new JsonObject();
        model.addProperty(ROW_COUNT_KEY, this.rowCount);
        model.addProperty(COLUMN_COUNT_KEY, this.columnCount);
        model.addProperty(ENTITY_INDEX_KEY, this.entityIndex);
        model.addProperty(SPRITE_HEIGHT_KEY, this.spriteHeight);
        model.addProperty(SPRITE_WIDTH_KEY, this.spriteWidth);
        model.addProperty(SHEET_PATH_KEY, this.sheetPath);
        return model;
    }

    /**
     * @return int
     */
    public int getRowCount() {
        return rowCount;
    }

    /**
     * @return int
     */
    public int getColumnCount() {
        return columnCount;
    }

    /**
     * @return int
     */
    public int getSpriteWidth() {
        return spriteWidth;
    }

    /**
     * @return int
     */
    public int getSpriteHeight() {
        return spriteHeight;
    }

    /**
     * @return int
     */
    public int getEntityIndex() {
        return entityIndex;
    }

    /**
     * @return String
     */
    public String getSheetPath() {
        return sheetPath;
    }

    /**
     * @param rowCount
     */
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    /**
     * @param columnCount
     */
    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    /**
     * @param spriteWidth
     */
    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    /**
     * @param spriteHeight
     */
    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    /**
     * @param entityIndex
     */
    public void setEntityIndex(int entityIndex) {
        this.entityIndex = entityIndex;
    }

    /**
     * @param sheetPath
     */
    public void setSheetPath(String sheetPath) {
        this.sheetPath = sheetPath;
    }
}
