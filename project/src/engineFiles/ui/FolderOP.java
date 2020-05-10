package engineFiles.ui;


import com.google.gson.Gson;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class FolderOP {

    public static File[] getFiles(String dir) {
        File folder = new File(dir);
        return Objects.requireNonNull(folder.listFiles());
    }

    public static BufferedImage getImage(File file) {
        try {
            return ImageIO.read(file);
        } catch (IOException e) {

            System.out.println(e.getMessage());
            System.out.println(file.getPath());
            return null;
        }
    }

    public static Sprite getSprite(File file) {
        return new Sprite(file);
    }


    public static Sprite getSprite(String path) {
        File f = new File(path);
        return new Sprite(f);
    }

    public static ArrayList<Sprite> getSprites(String dir) {
        ArrayList<Sprite> sprites = new ArrayList<>();
        File[] files = FolderOP.getFiles(dir);
        for (File f : files) {
            if (f.getPath().contains(".png")) {
                sprites.add(new Sprite(f));
            }

        }
        return sprites;
    }

    public static JSONObject getJSON(String path) {
        Gson gSon = new Gson();

        File f = new File(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while(line != null){
                sb.append(line).append("\n");
                line = br.readLine();
            }
            System.out.println(sb.toString());
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject getJSON(File file) {


        // create a reader

        try {
            return new JSONObject(new Scanner(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}