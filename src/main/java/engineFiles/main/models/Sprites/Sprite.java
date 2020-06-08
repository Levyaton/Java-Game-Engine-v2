package engineFiles.main.models.Sprites;

import engineFiles.ui.Coordinates;
import engineFiles.ui.FolderOP;
import org.imgscalr.Scalr;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

//Class containing the sprite logic, mainly used in the overworld
public class Sprite implements Comparable {
    private static final Logger LOG = Logger.getLogger(Sprite.class.getName());
    protected File ogFile;
    protected BufferedImage img;
    protected BufferedImage ogImg;
    protected String path;
    protected String name;
    protected Coordinates ogCoord;
    protected Coordinates coord;
    protected int defaultHeight;
    protected int defaultWidth;
    protected int currentHeight;
    protected int currentWidth;
    protected JSONObject json;
    protected JComponent component;
    protected boolean movable = false;
    protected boolean solid = true;
    protected int id;
    protected String categoryName = "tile";

    public Sprite() {
        LOG.config("Sprite Initialized");
    }

    public Sprite(File file) {
        this.ogFile = file;
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0, 0, currentWidth, currentHeight);
        this.ogCoord = this.coord;
        json = updateJSON();
        LOG.config("Sprite Initialized");
    }

    public Sprite(File file, BufferedImage img, int dimMod, int x, int y, int z, int blockID, int width, int height,
            boolean solid, boolean movable) {
        this.ogFile = file;
        this.img = img;
        this.ogImg = img;
        this.path = this.ogFile.getPath();
        this.name = String.valueOf(blockID);
        this.defaultHeight = height;
        this.defaultWidth = width;
        this.currentHeight = height * dimMod;
        this.currentWidth = width * dimMod;
        this.coord = new Coordinates(x * width, y * height, z, width * dimMod, height * dimMod);
        this.ogCoord = this.coord;
        json = updateJSON();
        this.solid = solid;
        this.movable = movable;
        LOG.config("Sprite Initialized");
    }

    public Sprite(String file) {
        this.ogFile = new File(file);
        initFromOGFile();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
        this.coord = new Coordinates(0, 0, currentWidth, currentHeight);
        this.ogCoord = this.coord;
        json = updateJSON();
        LOG.config("Sprite Initialized");
    }

    public Sprite(JSONObject json) {
        String path = json.get("Path").toString();
        // path = path.substring(2,path.length()-2);
        this.ogFile = new File(path);
        initFromOGFile();
        currentHeight = json.getInt("Height");
        currentWidth = json.getInt("Width");
        int x = json.getInt("XAxis");
        int y = json.getInt("YAxis");
        int z = json.getInt("ZAxis");
        coord = new Coordinates(x, y, currentWidth, currentHeight);
        coord.setZ(z);
        this.ogCoord = this.coord;
        solid = json.getBoolean("Solid");
        movable = json.getBoolean("Movable");
        LOG.config("Sprite Initialized");
    }

    protected void initFromOGFile() {

        this.img = FolderOP.getImage(this.ogFile);
        this.ogImg = img;
        this.path = this.ogFile.getPath();
        this.name = this.ogFile.getName();
        this.defaultHeight = this.img.getHeight();
        this.defaultWidth = this.img.getWidth();
    }

    /**
     * @return JSONObject
     */
    public JSONObject updateJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("Width", getCurrentWidth());
        jsonObject.append("Height", getCurrentHeight());
        jsonObject.append("XAxis", getCoord().getX());
        jsonObject.append("YAxis", getCoord().getY());
        jsonObject.append("ZAxis", getCoord().getZ());
        jsonObject.append("Path", getPath());
        jsonObject.append("Solid", solid);
        jsonObject.append("Movable", this.movable);
        return jsonObject;
    }

    /**
     * @return Coordinates
     */
    public Coordinates getOgCoord() {
        return ogCoord;
    }

    /**
     * @param ogCoord
     */
    public void setOgCoord(Coordinates ogCoord) {
        this.ogCoord = ogCoord;
    }

    /**
     * @param ogImg
     */
    public void setOgImg(BufferedImage ogImg) {
        this.ogImg = ogImg;
    }

    /**
     * @param currentHeight
     */
    public void setCurrentHeight(int currentHeight) {
        this.currentHeight = currentHeight;
    }

    /**
     * @param currentWidth
     */
    public void setCurrentWidth(int currentWidth) {
        this.currentWidth = currentWidth;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getOgImg() {
        return ogImg;
    }

    /**
     * @return JSONObject
     */
    public JSONObject getJson() {
        return json;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @return boolean
     */
    public boolean isMovable() {
        return movable;
    }

    /**
     * @param movable
     */
    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    /**
     * @return JSONObject
     */
    public JSONObject getJSON() {
        return json;
    }

    /**
     * @return JComponent
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * @param component
     */
    public void setComponent(JComponent component) {
        this.component = component;
    }

    /**
     * @return File
     */
    public File getOgFile() {
        return ogFile;
    }

    /**
     * @param ogFile
     */
    public void setOgFile(File ogFile) {
        this.ogFile = ogFile;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getImg() {
        return img;
    }

    /**
     * @param img
     */
    public void setImg(BufferedImage img) {
        this.img = img;
    }

    /**
     * @return Coordinates
     */
    public Coordinates getCoord() {
        return coord;
    }

    /**
     * @return boolean
     */
    public boolean isSolid() {
        return solid;
    }

    /**
     * @param coord
     */
    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    /**
     * @return int
     */
    public int getCurrentHeight() {
        return currentHeight;
    }

    /**
     * @param mod
     */
    public void resizeImg(double mod) {
        this.currentWidth = (int) (currentWidth * mod);
        this.currentHeight = (int) (currentHeight * mod);
        this.img = Scalr.resize(this.img, Scalr.Method.QUALITY, Scalr.Mode.FIT_TO_WIDTH, currentWidth, currentHeight,
                Scalr.OP_ANTIALIAS);
    }

    /**
     * @param width
     * @param height
     */
    public void transformImg(int width, int height) {
        // Cannot deform image, fix later
        Image image = this.img;
        this.currentWidth = width;
        this.currentHeight = height;
        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage temp = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = temp.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        this.img = temp;
    }

    /**
     * @return int
     */
    public int getCurrentWidth() {
        return currentWidth;
    }

    /**
     * @return int
     */
    public int getDefaultHeight() {
        return defaultHeight;
    }

    /**
     * @param defaultHeight
     */
    public void setDefaultHeight(int defaultHeight) {
        this.defaultHeight = defaultHeight;
    }

    /**
     * @return String
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getDefaultWidth() {
        return defaultWidth;
    }

    /**
     * @param defaultWidth
     */
    public void setDefaultWidth(int defaultWidth) {
        this.defaultWidth = defaultWidth;
    }

    /**
     * @param json
     */
    public void setJson(JSONObject json) {
        this.json = json;
    }

    /**
     * @param solid
     */
    public void setSolid(boolean solid) {
        this.solid = solid;
    }

    /**
     * @param o
     * @return int
     */
    // USE ONLY FOR COMPARING Z VALUES!!!!!!
    @Override
    public int compareTo(Object o) {
        int compareZOrder = ((Sprite) o).getCoord().getZ();
        return this.getCoord().getZ() - compareZOrder;
    }

    /**
     * @param s
     * @return boolean
     */
    public boolean onCollision(Sprite s) {
        return true;
    }

    /**
     * @return String
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
