package engineFiles.main.models;

import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.ui.TileMapClasses.TileMap;

import java.util.List;

//Class containing the world generation properties. Used to keep track of games structure
public class WorldGenModel {
    private TileMap tileMap;
    private ColorerModel colorerModel;
    private EntitiesModel entitiesModel;
    private String tilesetPath;
    private Area area;

    public WorldGenModel(TileMap tm, ColorerModel cm, EntitiesModel em, String tilesetPath, List<ItemSprite> items) {
        this.tileMap = tm;
        this.colorerModel = cm;
        this.entitiesModel = em;
        this.tilesetPath = tilesetPath;
        area = tm.getArea();
        area.addItems(items);
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    public ColorerModel getColorerModel() {
        return colorerModel;
    }

    public EntitiesModel getEntitiesModel() {
        return entitiesModel;
    }

    public String getTilesetPath() {
        return tilesetPath;
    }

    public void setTm(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    public void setColorerModel(ColorerModel colorerModel) {
        this.colorerModel = colorerModel;
    }

    public void setEntitiesModel(EntitiesModel entitiesModel) {
        this.entitiesModel = entitiesModel;
    }

    public void setTilesetPath(String tilesetPath) {
        this.tilesetPath = tilesetPath;
    }

    public Area getArea() {
        return area;
    }
}
