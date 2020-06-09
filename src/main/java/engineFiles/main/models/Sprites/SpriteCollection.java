package engineFiles.main.models.Sprites;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

////Class containing a custom sprite array list, that has some modified methods, making it more useful for tracking used sprites
public class SpriteCollection extends ArrayList<Sprite> {
    private static final Logger LOG = Logger.getLogger(SpriteCollection.class.getName());

    public SpriteCollection(JSONArray sprites) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        for (Object json : sprites) {
            Sprite s = new Sprite((JSONObject) json);
            add(s);
        }

        LOG.config("SpriteCollection Initialized");
    }

    /**
     * @param arr
     * 
     */
    public SpriteCollection(ArrayList<Sprite> arr) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        addAll(arr);
        LOG.config("SpriteCollection Initialized");
    }

    public SpriteCollection() {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        LOG.config("SpriteCollection Initialized");
    }

    /**
     * @param sprite
     * @return boolean
     */
    @Override
    public boolean add(Sprite sprite) {

        int size = this.size();
        int given = sprite.getCoord().getZ();
        for (int x = 0; x < this.size(); x++) {
            for (Sprite s : this) {
                int current = s.getCoord().getZ();
                if (given < current) {
                    super.add(x, sprite);
                    return this.size() > size;
                }
            }
        }
        return super.add(sprite);
    }

    public void sort() {
        Comparator<Sprite> compareByZ = Sprite::compareTo;
        super.sort(compareByZ);
        LOG.info("SpriteCollection Sorted");
    }

    /**
     * @return JSONArray
     */
    public JSONArray toJSONArray() {
        JSONArray arr = new JSONArray();
        for (Sprite s : this) {
            arr.put(s.getJSON());
        }
        return arr;
    }

}
