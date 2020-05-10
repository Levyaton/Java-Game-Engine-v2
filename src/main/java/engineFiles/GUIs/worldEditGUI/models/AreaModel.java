package engineFiles.GUIs.worldEditGUI.models;

import com.google.gson.Gson;
import engineFiles.ui.SpriteCollection;
import org.json.JSONObject;

public class AreaModel extends GsonModel{

    protected SpriteModel[] Sprites;
    protected int Height;
    protected int Width;
    protected String Name;
    protected SpriteModel Player;

    public AreaModel(String Name, int Width, int Height){
        this.Name = Name;
        this.Width = Width;
        this.Height = Height;
    }

    public AreaModel(SpriteCollection sprites, String name, int width, int height, SpriteModel player){
        this(name,width,height);
        this.Sprites = (SpriteModel[]) sprites.toArray();
        this.Player = player;
    }

    public AreaModel(SpriteModel[] sprites, String name, int width, int height, SpriteModel player){
        this(name,width,height);
        this.Sprites = sprites;
        this.Player = player;
    }

    public AreaModel(JSONObject json){
       Gson gson = new Gson();
       AreaModel a = gson.fromJson(json.toString(), AreaModel.class);
       Sprites = a.getSprites();
       Name = a.Name;
       Width = a.Width;
       Height = a. Height;
       
    }

    public SpriteModel[] getSprites() {
        return Sprites;
    }

    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }

    public String getName() {
        return Name;
    }

    public void setSprites(SpriteModel[] sprites) {
        Sprites = sprites;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public void setName(String name) {
        Name = name;
    }


}
