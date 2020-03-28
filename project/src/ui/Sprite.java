package ui;

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

    public Sprite(File file) {
        this.ogFile = file;
        this.img = FolderOP.getImage(file);
        this.path = file.getPath();
        this.name = file.getName();
        this.defaultHeight = this.img.getHeight();
        this.defaultWidth = this.img.getWidth();
        this.currentHeight = this.defaultHeight;
        this.currentWidth = this.defaultWidth;
    }


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
