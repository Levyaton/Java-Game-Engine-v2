package engineFiles.main.models;

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
