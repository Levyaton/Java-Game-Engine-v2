package engineFiles.ui;

import com.google.gson.Gson;
import engineFiles.main.models.Sprites.Sprite;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing generic static folder operations
public class FolderOP {
    private static final Logger LOG = Logger.getLogger(FolderOP.class.getName());
    /**
     * @param dir
     * @return File[]
     */
    public static File[] getFiles(String dir) {
        File folder = new File(dir);
        return Objects.requireNonNull(folder.listFiles());
    }

    /**
     * @param file
     * @return BufferedImage
     */
    public static BufferedImage getImage(File file) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        try {
            return ImageIO.read(file);
        } catch (IOException e) {

            LOG.severe("Image failed to load with message " + e.getMessage());
            System.out.println("From path " + file.getPath());
            return null;
        }
    }

    /**
     * @param file
     * @return Sprite
     */
    public static Sprite getSprite(File file) {
        return new Sprite(file);
    }

    /**
     * @param path
     * @return Sprite
     */
    public static Sprite getSprite(String path) {
        File f = new File(path);
        return new Sprite(f);
    }

    /**
     * @param dir
     * @return ArrayList<Sprite>
     */
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

    /**
     * @param path
     * @return JSONObject
     */
    public static JSONObject getJSON(String path) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        Gson gSon = new Gson();

        File f = new File(path);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = br.readLine();
            }
            LOG.config(sb.toString());
            return new JSONObject(sb.toString());
        } catch (IOException e) {
            LOG.severe("Failed to get JSON in FolderOP with message");
            LOG.severe(e.getMessage());
        }
        return null;
    }

    /**
     * @param file
     * @return JSONObject
     */
    public static JSONObject getJSON(File file) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        // create a reader

        try {
            return new JSONObject(new Scanner(file));
        } catch (FileNotFoundException e) {
            LOG.severe("Failed to get Json in FolderOP with message");
            LOG.severe(e.getMessage());
        }
        return null;
    }

}