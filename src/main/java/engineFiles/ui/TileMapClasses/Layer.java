package engineFiles.ui.TileMapClasses;

import engineFiles.ui.Coordinates;
import engineFiles.ui.Sprite;
import engineFiles.ui.SpriteCollection;

import java.awt.image.BufferedImage;
import java.io.File;

public class Layer {
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
    public int[] getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getOpacity() {
        return opacity;
    }

    public String getType() {
        return type;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public SpriteCollection getSpriteCollection(File tileSet){
        BufferedImage[] tiles = TileParser.getTiles(tileSet);
        SpriteCollection spriteCollection = new SpriteCollection();

        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                int id = this.data[x + (y * this.width)];
                if(id != 0){
                    spriteCollection.add(buildSprite(tiles,tileSet,x,y, id));
                }
            }
        }

        //int id = 1;
        //spriteCollection.add(buildSprite(tiles, tileSet,0,0, id));
        return spriteCollection;
    }

    private Sprite buildSprite(BufferedImage[] tiles, File tileSet, int x, int y, int blockID){
        int z = this.id;
        BufferedImage current = tiles[blockID - 1];//Scalr.resize(tiles[id], SPRITE_MOD);
        boolean solid = (this.name.equals("solid") || this.name.equals("movable") || this.name.equals("characters"));
        boolean movable = this.name.equals("movable");
        return new Sprite(tileSet,current,SPRITE_MOD,x,y,z,blockID,SPRITE_WIDTH,SPRITE_HEIGHT, solid, movable);
    }
}
