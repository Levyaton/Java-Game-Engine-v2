package engineFiles.ui.TileMapClasses;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.SpriteCollection;
import engineFiles.ui.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TileMap {

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

    private String PATH = "src/main/java/engineFiles/colorer/testFiles/";//"src/main/java/resources/playerGameFiles/sprites/tilesets/";
    public TileMap(){

    }

    public TileMap(String path) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        TileMap tm = gson.fromJson(br, TileMap.class);

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
        JsonObject json = gson.fromJson(new BufferedReader(new FileReader(path)), JsonObject.class);

        Gson eventGson = new Gson();
        //Type eventsType = new TypeToken<List<Event>>(){}.getType();

        JsonObject obj = json.getAsJsonObject();
        //Layer[] eventList = eventGson.fromJson(obj.get("layers"), Layer[].class);
        this.layers =   gson.fromJson(json.getAsJsonArray("layers"), Layer[].class);
        this.tilesets = gson.fromJson(json.get("tilesets"), Tileset[].class);
        this.editorsettings = gson.fromJson(json.get("editorsettings"), EditorSettings.class);
    }

    public String getPATH() {
        return PATH;
    }

    public void setPATH(String PATH) {
        this.PATH = PATH;
    }

    public int getCompressionlevel() {
        return compressionlevel;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getTileheight() {
        return tileheight;
    }

    public int getTilewidth() {
        return tilewidth;
    }

    public int getNextlayerid() {
        return nextlayerid;
    }

    public int getNextobjectid() {
        return nextobjectid;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getRenderorder() {
        return renderorder;
    }

    public String getTiledversion() {
        return tiledversion;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public boolean getIsInfinite() {
        return infinite;
    }

    public Tileset[] getTilesets() {
        return tilesets;
    }

    public Layer[] getLayers() {
        return layers;
    }

    public EditorSettings getEditorsettings() {
        return editorsettings;
    }

    public void setCompressionlevel(int compressionlevel) {
        this.compressionlevel = compressionlevel;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setTileheight(int tileheight) {
        this.tileheight = tileheight;
    }

    public void setTilewidth(int tilewidth) {
        this.tilewidth = tilewidth;
    }

    public void setNextlayerid(int nextlayerid) {
        this.nextlayerid = nextlayerid;
    }

    public void setNextobjectid(int nextobjectid) {
        this.nextobjectid = nextobjectid;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public void setRenderorder(String renderorder) {
        this.renderorder = renderorder;
    }

    public void setTiledversion(String tiledversion) {
        this.tiledversion = tiledversion;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public void setTilesets(JsonArray array) {
        this.tilesets = Utils.arrToObj(array, Tileset.class);
    }

    public void setTilesets(Tileset[] tilesets) {
        this.tilesets = tilesets;
    }

    public void setLayers(Layer[] layers) {
        this.layers = layers;
    }

    public void setLayers(JsonArray layers) {
        this.layers = Utils.arrToObj(layers, Layer.class);
    }

    public void setEditorsettings(EditorSettings editorsettings) {
        this.editorsettings = editorsettings;
    }

    public Area getArea(){
        SpriteCollection s = new SpriteCollection();
        //MAKE SURE THE SOURCE IS CORRECT AND COMPATIBLE WITH CODE
        File areaTileset = new File(PATH + tilesets[0].source);
        for (Layer l: layers) {
            s.addAll(l.getSpriteCollection(areaTileset));
            System.out.println(l.name);
        }
       return new Area(s, areaTileset.getName(),this.width, this.height);
    }




}
