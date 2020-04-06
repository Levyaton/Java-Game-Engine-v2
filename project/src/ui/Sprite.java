package ui;

import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;


public class Sprite {
    private File ogFile;
    private BufferedImage img;
    private String path;
    private String name;
    private Coordinates coord;
    private int defaultHeight;
    private int defaultWidth;
    private int currentHeight;
    private int currentWidth;
    private JSONObject json;

    private int layer = 0;


    public Sprite(File file) {
        this.ogFile = file;
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0,0);
        json = updateJSON();
    }

    public Sprite(JSONObject json){
        this.ogFile = new File(json.get("Path").toString());
        initFromOGFile();
        currentHeight = JSONHelper.getIntParameter(json,"Height");
        currentWidth = JSONHelper.getIntParameter(json,"Width");
        int x = JSONHelper.getIntParameter(json,"XAxis");
        int y = JSONHelper.getIntParameter(json,"YAxis");
        coord = new Coordinates(x,y);
        layer = JSONHelper.getIntParameter(json,"Layer");
;    }

    private void initFromOGFile(){

        this.img = FolderOP.getImage(this.ogFile);
        this.path = this.ogFile.getPath();
        this.name = this.ogFile.getName();
        this.defaultHeight = this.img.getHeight();
        this.defaultWidth = this.img.getWidth();
    }

    public JSONObject updateJSON(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("Width", getCurrentWidth());
        jsonObject.append("Height", getCurrentHeight());
        jsonObject.append("XAxis", getCoord().getX());
        jsonObject.append("YAxis", getCoord().getY());
        jsonObject.append("Layer", getLayer());
        jsonObject.append("Path", getPath());
        return jsonObject;
    }


    public JSONObject getJSON() {
        return json;
    }


    public int getLayer(){
        return layer;
    };

    public File getOgFile() {
        return ogFile;
    }

    public BufferedImage getImg() {
        return img;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public int getCurrentWidth() {
        return currentWidth;
    }

    public int getDefaultHeight() {
        return defaultHeight;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public void setLayer(int layer){
        this.layer = layer;
    }

    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
    }

    public void setOgFile(File ogFile) {
        this.ogFile = ogFile;
    }
}
