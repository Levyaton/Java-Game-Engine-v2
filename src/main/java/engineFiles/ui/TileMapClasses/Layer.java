package engineFiles.ui.TileMapClasses;

import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.Sprites.SpriteCollection;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Logger;

//Class containing sprite sheet layer logic
public class Layer {
    private static final Logger LOG = Logger.getLogger(Layer.class.getName());
    int[] data;
    int height;
    int id;
    String name;
    int opacity;
    String type;
    boolean visible;
    int width;
    int x;
    int y;

    private int SPRITE_HEIGHT = 48;
    private int SPRITE_WIDTH = 48;
    private int SPRITE_MOD = 1;

    /**
     * @return int[]
     */
    public int[] getData() {
        return data;
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
    public int getId() {
        return id;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @return int
     */
    public int getOpacity() {
        return opacity;
    }

    /**
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * @return boolean
     */
    public boolean isVisible() {
        return visible;
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
    public int getX() {
        return x;
    }

    /**
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     * @param data
     */
    public void setData(int[] data) {
        this.data = data;
    }

    /**
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param opacity
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @param visible
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @param tileSet
     * @return SpriteCollection
     */
    public SpriteCollection getSpriteCollection(File tileSet) {

        BufferedImage[] tiles = TileParser.getTiles(tileSet);
        SpriteCollection spriteCollection = new SpriteCollection();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int id = this.data[x + (y * this.width)];
                if (id != 0) {
                    spriteCollection.add(buildSprite(tiles, tileSet, x, y, id));
                }
            }
        }

        // int id = 1;
        // spriteCollection.add(buildSprite(tiles, tileSet,0,0, id));
        return spriteCollection;
    }

    /**
     * @param tiles
     * @param tileSet
     * @param x
     * @param y
     * @param blockID
     * @return Sprite
     */
    private Sprite buildSprite(BufferedImage[] tiles, File tileSet, int x, int y, int blockID) {
        int z = this.id;
        BufferedImage current = tiles[blockID - 1];// Scalr.resize(tiles[id], SPRITE_MOD);
        boolean solid = (this.name.equals("solid") || this.name.equals("movable") || this.name.equals("characters"));
        boolean movable = this.name.equals("movable");
        return new Sprite(tileSet, current, SPRITE_MOD, x, y, z, blockID, SPRITE_WIDTH, SPRITE_HEIGHT, solid, movable);
    }
}
