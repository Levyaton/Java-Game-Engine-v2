package engineFiles.main.models;

import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.ui.TileMapClasses.TileMap;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the world generation properties. Used to keep track of games structure
public class WorldGenModel {
    private static final Logger LOG = Logger.getLogger(WorldGenModel.class.getName());
    private TileMap tileMap;
    private ColorerModel colorerModel;
    private EntitiesModel entitiesModel;
    private String tilesetPath;
    private Area area;

    /**
     * @param tm
     * @param cm
     * @param em
     * @param tilesetPath
     * @param items
     * 
     */
    public WorldGenModel(TileMap tm, ColorerModel cm, EntitiesModel em, String tilesetPath, List<ItemSprite> items) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        this.tileMap = tm;
        this.colorerModel = cm;
        this.entitiesModel = em;
        this.tilesetPath = tilesetPath;
        area = tm.getArea();
        area.addItems(items);
        LOG.config("WorldGen Initialized");
    }

    /**
     * @return TileMap
     */
    public TileMap getTileMap() {
        return tileMap;
    }

    /**
     * @return ColorerModel
     */
    public ColorerModel getColorerModel() {
        return colorerModel;
    }

    /**
     * @return EntitiesModel
     */
    public EntitiesModel getEntitiesModel() {
        return entitiesModel;
    }

    /**
     * @return String
     */
    public String getTilesetPath() {
        return tilesetPath;
    }

    /**
     * @param tileMap
     */
    public void setTm(TileMap tileMap) {
        this.tileMap = tileMap;
    }

    /**
     * @param colorerModel
     */
    public void setColorerModel(ColorerModel colorerModel) {
        this.colorerModel = colorerModel;
    }

    /**
     * @param entitiesModel
     */
    public void setEntitiesModel(EntitiesModel entitiesModel) {
        this.entitiesModel = entitiesModel;
    }

    /**
     * @param tilesetPath
     */
    public void setTilesetPath(String tilesetPath) {
        this.tilesetPath = tilesetPath;
    }

    /**
     * @return Area
     */
    public Area getArea() {
        return area;
    }
}
