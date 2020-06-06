package engineFiles.GUIs.worldEditGUI.models;

import com.google.gson.Gson;
import org.json.JSONObject;

public class SpriteModel extends GsonModel{
    protected String Name;
    protected String Path;
    protected int ZAxis;
    protected int XAxis;
    protected int YAxis;
    protected Boolean Solid;
    protected Boolean Movable;
    protected int Height;
    protected int Width;



   public SpriteModel(String Path, int ZAxis, int XAxis, int YAxis, Boolean Solid, Boolean Movable, int Height, int Width, String Name){
       this.Height = Height;
       this.Movable = Movable;
       this.Path = Path;
       this.Solid = Solid;
       this.Width = Width;
       this.YAxis = YAxis;
       this.XAxis = XAxis;
       this.ZAxis = ZAxis;
   }

    public SpriteModel(JSONObject json){
        SpriteModel s = new Gson().fromJson(json.toString(), SpriteModel.class);
        this.Height = s.getHeight();
        this.Movable = s.getMovable();
        this.Path = s.getPath();
        this.Solid = s.getSolid();
        this.Width = s.getWidth();
        this.YAxis = s.getYAxis();
        this.XAxis = s.getXAxis();
        this.ZAxis = s.getZAxis();
        this.Name = s.getName();
    }

    public String getName() {
        return Name;
    }

    public String getPath() {
        return Path;
    }

    public int getZAxis() {
        return ZAxis;
    }

    public int getXAxis() {
        return XAxis;
    }

    public int getYAxis() {
        return YAxis;
    }

    public Boolean getSolid() {
        return Solid;
    }

    public Boolean getMovable() {
        return Movable;
    }

    public int getHeight() {
        return Height;
    }

    public int getWidth() {
        return Width;
    }

    public void setPath(String path) {
        Path = path;
    }

    public void setZAxis(int ZAxis) {
        this.ZAxis = ZAxis;
    }

    public void setXAxis(int XAxis) {
        this.XAxis = XAxis;
    }

    public void setYAxis(int YAxis) {
        this.YAxis = YAxis;
    }

    public void setSolid(Boolean solid) {
        Solid = solid;
    }

    public void setMovable(Boolean movable) {
        Movable = movable;
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
