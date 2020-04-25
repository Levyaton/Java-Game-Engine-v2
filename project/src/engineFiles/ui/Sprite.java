package engineFiles.ui;

import org.imgscalr.Scalr;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;


public class Sprite implements Comparable {
    protected File ogFile;
    protected BufferedImage img;
    protected BufferedImage ogImg;
    protected String path;
    protected String name;
    protected Coordinates coord;
    protected int defaultHeight;
    protected int defaultWidth;
    protected int currentHeight;
    protected int currentWidth;
    protected JSONObject json;
    protected JComponent component;
    protected boolean movable = false;
    protected boolean physical = true;


    public Sprite(File file) {
        this.ogFile = file;
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0, 0, currentWidth, currentHeight);
        json = updateJSON();
    }

    public Sprite(String file) {
        this.ogFile = new File(file);
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0, 0, currentWidth, currentHeight);
        json = updateJSON();
    }


    public Sprite(JSONObject json) {
        this.ogFile = new File(json.get("Path").toString());
        initFromOGFile();
        currentHeight = json.getInt("Height");
        currentWidth = json.getInt("Width");
        int x = json.getInt("XAxis");
        int y = json.getInt("YAxis");
        int z = json.getInt("ZAxis");
        coord = new Coordinates(x, y, currentWidth, currentHeight);
        coord.setZ(z);
        movable = json.getBoolean("Movable");
    }

    private void initFromOGFile() {

        this.img = FolderOP.getImage(this.ogFile);
        this.ogImg = img;
        this.path = this.ogFile.getPath();
        this.name = this.ogFile.getName();
        this.defaultHeight = this.img.getHeight();
        this.defaultWidth = this.img.getWidth();
    }

    public JSONObject updateJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("Width", getCurrentWidth());
        jsonObject.append("Height", getCurrentHeight());
        jsonObject.append("XAxis", getCoord().getX());
        jsonObject.append("YAxis", getCoord().getY());
        jsonObject.append("ZAxis", getCoord().getZ());
        jsonObject.append("Path", getPath());
        jsonObject.append("Movable", this.movable);
        return jsonObject;
    }


    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public JSONObject getJSON() {
        return json;
    }

    public JComponent getComponent() {
        return component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    public File getOgFile() {
        return ogFile;
    }

    public void setOgFile(File ogFile) {
        this.ogFile = ogFile;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public boolean isPhysical() {
        return physical;
    }

    public JSONObject getJson() {
        return json;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public int getCurrentHeight() {
        return currentHeight;
    }

    public void resizeImg(double mod){
        this.currentWidth =  (int)(currentWidth * mod);
        this.currentHeight = (int)(currentHeight * mod);
        this.img = Scalr.resize( this.img, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, currentWidth, currentHeight, Scalr.OP_ANTIALIAS);
    }
    public void transformImg(int width, int height){
        //Cannot deform image, fix later
        Image image = this.img;
        this.currentWidth = width;
        this.currentHeight = height;
        image = image.getScaledInstance(width, height,Image.SCALE_DEFAULT);
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = temp.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        this.img = temp;
    }

    public int getCurrentWidth() {
        return currentWidth;
    }



    public int getDefaultHeight() {
        return defaultHeight;
    }

    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDefaultWidth() {
        return defaultWidth;
    }

    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    public void setJson(JSONObject json) {
        this.json = json;
    }

    public void setPhysical(boolean physical) {
        this.physical = physical;
    }

    //USE ONLY FOR COMPARING Z VALUES!!!!!!
    @Override
    public int compareTo(Object o) {
        int compareZOrder = ((Sprite) o).getCoord().getZ();
        return this.getCoord().getZ() - compareZOrder;
    }
}
