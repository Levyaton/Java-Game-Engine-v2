package engineFiles.ui;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class Area {
    private String spritePath = "project/src/main.gameFiles/models/objects/areas/";
    private SpriteCollection sprites = new SpriteCollection();
    private String name;
    private int width;
    private int height;
    private JSONObject json = new JSONObject();

    public Area(String name, int width, int height){
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
        this.name = json.getString("Name");
        this.width = json.getInt("Width");
        this.height = json.getInt("Height");
        this.sprites = new SpriteCollection(json.getJSONArray("Sprites"));
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
        this.json.put("Name",name);
        this.json.put("Width", width);
        this.json.put("Height", height);
        this.json.put("Sprites", sprites.toJSONArray());
    }

    private void updateJSON(){
        this.json.remove("Name");
        this.json.remove("Width");
        this.json.remove("Height");
        this.json.remove("Sprites");
        buildJSON();
    }
    private void updateJSON(String name){
        this.json.remove("Name");
        this.json.put("Name",name);
    }

    private void updateJSON(int width, int height){
        this.json.remove("Width");
        this.json.remove("Height");
        this.json.put("Width", width);
        this.json.put("Height", height);
        this.json.put("Name",name);
    }

    private void updateJSON(SpriteCollection sprite){
        this.json.remove("Sprites");
        this.json.put("Sprites", sprites.toJSONArray());
    }

    public void storeJSON(){
        try {
            FileWriter f = new FileWriter(this.spritePath + this.name + ".json");
            f.write(getJSONArea().toString());
            f.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
