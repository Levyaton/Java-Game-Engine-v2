package engineFiles.main.models;

import engineFiles.main.models.Sprites.Sprite;
import engineFiles.ui.TileMapClasses.TileMap;

import java.util.ArrayList;
import java.util.List;

public class WorldGenModel {
    private TileMap tm;
    private ColorerModel cm;
    private EntitiesModel em;
    private String tilesetPath;
    private List<Sprite> items;

    public WorldGenModel(TileMap tm, ColorerModel cm, EntitiesModel em, String tilesetPath, List<Sprite> items) {
        this.tm = tm;
        this.cm = cm;
        this.em = em;
        this.tilesetPath = tilesetPath;
        this.items = items;
    }

    public WorldGenModel(TileMap tm, ColorerModel cm, EntitiesModel em, String tilesetPath) {
        this.tm = tm;
        this.cm = cm;
        this.em = em;
        this.tilesetPath = tilesetPath;
        this.items = new ArrayList<>();
    }


}
