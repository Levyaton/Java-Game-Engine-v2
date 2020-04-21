package engineFiles.ui;

import org.json.JSONObject;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Sprite implements Comparable {
    protected File ogFile;
    protected BufferedImage img;
    protected String path;
    protected String name;
    protected Coordinates coord;
    protected int defaultHeight;
    protected int defaultWidth;
    protected int currentHeight;
    protected int currentWidth;
    protected JSONObject json;
    protected JComponent component;

    private int layer = 0;

    protected boolean movable = false;


    public Sprite(File file) {
        this.ogFile = file;
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0,0, currentWidth, currentHeight);
        json = updateJSON();
    }
    public Sprite(String file) {
        this.ogFile = new File(file);
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0,0, currentWidth, currentHeight);
        json = updateJSON();
    }

    

    public Sprite(JSONObject json){
        this.ogFile = new File(json.get("Path").toString());
        initFromOGFile();
        currentHeight = json.getInt("Height");
        currentWidth = json.getInt("Width");
        int x = json.getInt("XAxis");
        int y = json.getInt("YAxis");
        coord = new Coordinates(x,y, currentWidth, currentHeight);
        layer = json.getInt("Layer");
        movable = json.getBoolean("Movable");
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
        jsonObject.append("Movable", this.movable);
        return jsonObject;
    }



    public boolean isMovable() {
        return movable;
    }

    public JSONObject getJSON() {
        return json;
    }


    public JComponent getComponent() {
        return component;
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

    public void setMovable(boolean movable) {
        this.movable = movable;
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

    public void setComponent(JComponent component) {
        this.component = component;
    }


    //USE ONLY FOR COMPARING Z VALUES!!!!!!
    @Override
    public int compareTo(Object o) {
        int compareZOrder = ((Sprite) o).getCoord().getZ();
        return this.getCoord().getZ() - compareZOrder;
    }
}
