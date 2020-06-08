package engineFiles.ui.TileMapClasses;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.SpriteCollection;
import engineFiles.ui.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Logger;

import static engineFiles.main.models.WorldGenKeys.TileMapKeys.*;

//Class containing the logic needed to use tilemaps

public class TileMap {
    private static final Logger LOG = Logger.getLogger(TileMap.class.getName());
    int compressionlevel;
    int height;
    int width;
    int tileheight;
    int tilewidth;
    int nextlayerid;
    int nextobjectid;

    String orientation;
    String renderorder;
    String tiledversion;
    String type;
    String version;

    boolean infinite;

    Tileset[] tilesets;
    Layer[] layers;
    EditorSettings editorsettings;
    JsonObject json;
    // private String PATH =
    // "src/main/java/resources/playerGameFiles/sprites/tilesets/";//"src/main/java/resources/playerGameFiles/sprites/tilesets/";
    private String tilesetPath;

    public TileMap() {
        LOG.config("TileMap Initialized");
    }

    public TileMap(JsonObject tileset, String tilesetPath) throws FileNotFoundException {
        this.tilesetPath = tilesetPath;
        this.json = tileset;
        Gson gson = new Gson();
        TileMap tm = gson.fromJson(tileset, TileMap.class);

        this.compressionlevel = tm.getCompressionlevel();
        this.height = tm.getHeight();
        this.width = tm.getWidth();
        this.tileheight = tm.getTileheight();
        this.tilewidth = tm.getTilewidth();
        this.nextlayerid = tm.getNextlayerid();
        this.nextobjectid = tm.getNextobjectid();

        this.orientation = tm.getOrientation();
        this.renderorder = tm.getRenderorder();
        this.tiledversion = tm.getTiledversion();
        this.type = tm.getType();
        this.version = tm.getVersion();

        this.infinite = tm.getIsInfinite();

        Gson eventGson = new Gson();
        // Type eventsType = new TypeToken<List<Event>>(){}.getType();

        JsonObject obj = json.getAsJsonObject();
        // Layer[] eventList = eventGson.fromJson(obj.get("layers"), Layer[].class);
        this.layers = gson.fromJson(json.getAsJsonArray(LAYERS_KEY), Layer[].class);
        this.tilesets = gson.fromJson(json.get(TILE_SETS_KEY), Tileset[].class);
        this.editorsettings = gson.fromJson(json.get(EDITOR_SETTINGS_KEY), EditorSettings.class);
        LOG.config("TileMap Initialized");
    }

    /**
     * @return int
     */
    public int getCompressionlevel() {
        return compressionlevel;
    }

    /**
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return int
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return int
     */
    public int getTileheight() {
        return tileheight;
    }

    /**
     * @return int
     */
    public int getTilewidth() {
        return tilewidth;
    }

    /**
     * @return int
     */
    public int getNextlayerid() {
        return nextlayerid;
    }

    /**
     * @return int
     */
    public int getNextobjectid() {
        return nextobjectid;
    }

    /**
     * @return String
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * @return String
     */
    public String getRenderorder() {
        return renderorder;
    }

    /**
     * @return String
     */
    public String getTiledversion() {
        return tiledversion;
    }

    /**
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * @return String
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return boolean
     */
    public boolean getIsInfinite() {
        return infinite;
    }

    /**
     * @return Tileset[]
     */
    public Tileset[] getTilesets() {
        return tilesets;
    }

    /**
     * @return Layer[]
     */
    public Layer[] getLayers() {
        return layers;
    }

    /**
     * @return EditorSettings
     */
    public EditorSettings getEditorsettings() {
        return editorsettings;
    }

    /**
     * @param compressionlevel
     */
    public void setCompressionlevel(int compressionlevel) {
        this.compressionlevel = compressionlevel;
    }

    /**
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param tileheight
     */
    public void setTileheight(int tileheight) {
        this.tileheight = tileheight;
    }

    /**
     * @param tilewidth
     */
    public void setTilewidth(int tilewidth) {
        this.tilewidth = tilewidth;
    }

    /**
     * @param nextlayerid
     */
    public void setNextlayerid(int nextlayerid) {
        this.nextlayerid = nextlayerid;
    }

    /**
     * @param nextobjectid
     */
    public void setNextobjectid(int nextobjectid) {
        this.nextobjectid = nextobjectid;
    }

    /**
     * @param orientation
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * @param renderorder
     */
    public void setRenderorder(String renderorder) {
        this.renderorder = renderorder;
    }

    /**
     * @param tiledversion
     */
    public void setTiledversion(String tiledversion) {
        this.tiledversion = tiledversion;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * @param infinite
     */
    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    /**
     * @param array
     */
    public void setTilesets(JsonArray array) {
        this.tilesets = Utils.arrToObj(array, Tileset.class);
    }

    /**
     * @param tilesets
     */
    public void setTilesets(Tileset[] tilesets) {
        this.tilesets = tilesets;
    }

    /**
     * @param layers
     */
    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    /**
     * @param layers
     */
    public void setLayers(JsonArray layers) {
        this.layers = Utils.arrToObj(layers, Layer.class);
    }

    /**
     * @param editorsettings
     */
    public void setEditorsettings(EditorSettings editorsettings) {
        this.editorsettings = editorsettings;
    }

    /**
     * @return Area
     */
    public Area getArea() {
        SpriteCollection s = new SpriteCollection();
        // MAKE SURE THE SOURCE IS CORRECT AND COMPATIBLE WITH CODE
        File areaTileset = new File(tilesetPath + tilesets[0].source);
        for (Layer l : layers) {
            s.addAll(l.getSpriteCollection(areaTileset));
        }
        return new Area(s, areaTileset.getName(), this.width, this.height);
    }

    /**
     * @return JsonObject
     */
    public JsonObject getJson() {
        return json;
    }
}
