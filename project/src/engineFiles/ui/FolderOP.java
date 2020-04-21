package engineFiles.ui;

import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class FolderOP {

    public static File[] getFiles(String dir){
        File folder = new File(dir);
        return Objects.requireNonNull(folder.listFiles());
    }

    public static BufferedImage getImage(File file){
        try {
            return ImageIO.read(file);
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

    public static ArrayList<Sprite> getSprites(String dir){
        ArrayList<Sprite> sprites = new ArrayList<>();
        File[] files = FolderOP.getFiles(dir);
        for (File f: files) {
            if(f.getPath().contains(".png")){
                sprites.add(new Sprite(f));
            }

        }
        return sprites;
    }

    public static JSONObject getJSON(String path){
        JSONParser parser = new JSONParser();
        try {
           return (JSONObject) parser.parse(new FileReader(path));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONObject getJSON(File file){
        JSONParser parser = new JSONParser();
        try {
            return (JSONObject) parser.parse(new FileReader(file));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
