package ui;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FolderOP {

    public static File[] getFiles(String dir){
        File folder = new File(dir);
        File[] files = Objects.requireNonNull(folder.listFiles());
        return files;
    }

    public static BufferedImage getImage(File file){
        try {
            BufferedImage img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println(file.getPath());
            return null;
        }
    }

    public static Sprite getSprite(File file){
        return new Sprite(file);
    }


    public static Sprite getSprite(String path){
        File f = new File(path);
        return new Sprite(f);
    }

}
