package engineFiles.main.models;

import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.Sprites.SpriteCollection;
import engineFiles.ui.FolderOP;
import org.json.JSONObject;

import java.io.File;
import java.util.Objects;

import static engineFiles.main.models.WorldGenKeys.AreaKeys.*;

public class Area {

    private SpriteCollection sprites = new SpriteCollection();
    private String name;
    private int width;
    private int height;
    private JSONObject json = new JSONObject();

    public Area(String name, int width, int height, String spritePath){
        this.name = name;
        this.width = width;
        this.height = height;

        buildJSON();
    }

    public Area(SpriteCollection sprites, String name, int width, int height){
        this.sprites = sprites;
        this.name = name;
        this.width = width;
        this.height = height;
        buildJSON();
    }

    public Area(JSONObject json){
        this.json = json;
        this.name = json.getString(NAME_KEY);
        this.width = json.getInt(WIDTH_KEY);
        this.height = json.getInt(HEIGHT_KEY);
        this.sprites = new SpriteCollection(json.getJSONArray(SPRITES_KEY));
    }

    public Area(File f){
        this(Objects.requireNonNull(FolderOP.getJSON(f)));
    }

    public Area(String pathToJSON){
        this(Objects.requireNonNull(FolderOP.getJSON(pathToJSON)));
    }

    public void addSprite(Sprite sprite){
        sprites.add(sprite);
    }

    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
        this.updateJSON(sprites);
    }

    public void setName(String name) {
        this.name = name;
        this.updateJSON(name);
    }

    public void setWidth(int width) {
        this.width = width;
        this.updateJSON(this.width,this.height);
    }

    public void setHeight(int height) {
        this.height = height;
        this.updateJSON(this.width,this.height);
    }

    public SpriteCollection getSprites() {
        return sprites;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JSONObject getJSONArea(){
        return json;
    }

    private void buildJSON(){
        this.json.put(NAME_KEY,name);
        this.json.put(WIDTH_KEY, width);
        this.json.put(HEIGHT_KEY, height);
        this.json.put(SPRITES_KEY, sprites.toJSONArray());
    }

    private void updateJSON(){
        this.json.remove(NAME_KEY);
        this.json.remove(WIDTH_KEY);
        this.json.remove(HEIGHT_KEY);
        this.json.remove(SPRITES_KEY);
        buildJSON();
    }
    private void updateJSON(String name){
        this.json.remove(NAME_KEY);
        this.json.put(NAME_KEY,name);
    }

    private void updateJSON(int width, int height){
        this.json.remove(WIDTH_KEY);
        this.json.remove(HEIGHT_KEY);
        this.json.put(WIDTH_KEY, width);
        this.json.put(HEIGHT_KEY, height);
        this.json.put(NAME_KEY,name);
    }

    private void updateJSON(SpriteCollection sprite){
        this.json.remove(SPRITES_KEY);
        this.json.put(SPRITES_KEY, sprites.toJSONArray());
    }






}
