package engineFiles.ui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Logger;

//Class containing generic static utility methods
public class Utils {
    private static final Logger LOG = Logger.getLogger(Utils.class.getName());
    /**
     * @param arr
     * @param classOfT
     * @return T[]
     */
    public static <T> T[] arrToObj(JsonArray arr, Class<T> classOfT) {

        Gson gson = new Gson();
        ArrayList<T> l = new ArrayList<>();
        for (Object json : arr) {
            l.add(gson.fromJson((JsonElement) json, (Type) classOfT));
        }
        return (T[]) l.toArray();
    }

    /**
     * @param img
     * @param newW
     * @param newH
     * @return BufferedImage
     */
    public static BufferedImage scale(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static int entityIndex = 1;

    /**
     * @return int
     */
    public static int getEntityIndex() {
        int index = entityIndex;
        entityIndex++;
        return index;

    }
}
