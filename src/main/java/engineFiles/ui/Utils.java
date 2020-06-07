package engineFiles.ui;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Type;
import java.util.ArrayList;


//Class containing generic static utility methods
public class Utils {

    public static <T> T[] arrToObj(JsonArray arr, Class<T> classOfT){

        Gson gson = new Gson();
        ArrayList<T> l = new ArrayList<>();
        for (Object json: arr) {
            l.add(gson.fromJson((JsonElement) json,(Type)classOfT));
        }
        return (T[]) l.toArray();
    }

    public static BufferedImage scale(BufferedImage img, int newW, int newH)
    {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    private static int entityIndex = 1;

    public static int getEntityIndex(){
        int index = entityIndex;
        entityIndex++;
        return index;

    }
}
