package engineFiles.main.models;

import com.google.gson.JsonObject;

import static engineFiles.main.models.WorldGenKeys.CharacterSpriteSheetKeys.*;

public class CharacterSpriteSheetModel {

    private int rowCount;
    private int columnCount;
    private int spriteWidth;
    private int spriteHeight;
    private int entityIndex;
    private String sheetPath;

    public CharacterSpriteSheetModel(int rowCount, int columnCount, int spriteWidth, int spriteHeight, int entityIndex, String sheetPath){
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.entityIndex = entityIndex;
        this.sheetPath = sheetPath;
    }

    public CharacterSpriteSheetModel(JsonObject json){
        this.rowCount = json.get(ROW_COUNT_KEY).getAsInt();
        this.columnCount = json.get(COLUMN_COUNT_KEY).getAsInt();
        this.spriteHeight = json.get(SPRITE_HEIGHT_KEY).getAsInt();
        this.spriteWidth = json.get(SPRITE_WIDTH_KEY).getAsInt();
        this.entityIndex = json.get(ENTITY_INDEX_KEY).getAsInt();
        this.sheetPath = json.get(SHEET_PATH_KEY).getAsString();
    }

    public JsonObject getCharacterSpriteSheetModelJson(){
        JsonObject model = new JsonObject();
        model.addProperty(ROW_COUNT_KEY, this.rowCount);
        model.addProperty(COLUMN_COUNT_KEY, this.columnCount);
        model.addProperty(ENTITY_INDEX_KEY, this.entityIndex);
        model.addProperty(SPRITE_HEIGHT_KEY, this.spriteHeight);
        model.addProperty(SPRITE_WIDTH_KEY, this.spriteWidth);
        model.addProperty(SHEET_PATH_KEY, this.sheetPath);
        return model;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public int getEntityIndex() {
        return entityIndex;
    }

    public String getSheetPath() {
        return sheetPath;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    public void setEntityIndex(int entityIndex) {
        this.entityIndex = entityIndex;
    }

    public void setSheetPath(String sheetPath) {
        this.sheetPath = sheetPath;
    }
}
