package engineFiles.main.models;

import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.Sprites.SpriteCollection;
import engineFiles.ui.FolderOP;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

import static engineFiles.main.models.WorldGenKeys.AreaKeys.*;

//Class containing the logic of a game area. It tracks which sprites are being used in a given area
public class Area {
    private static final Logger LOG = Logger.getLogger(Area.class.getName());
    private SpriteCollection sprites = new SpriteCollection();
    private List<ItemSprite> spritesItems = new CopyOnWriteArrayList<ItemSprite>();
    private String name;
    private int width;
    private int height;
    private JSONObject json = new JSONObject();

    public Area(String name, int width, int height, String spritePath) {
        this.name = name;
        this.width = width;
        this.height = height;

        buildJSON();
        LOG.config("Area Initialized");
    }

    public Area(SpriteCollection sprites, String name, int width, int height) {
        this.sprites = sprites;
        this.name = name;
        this.width = width;
        this.height = height;
        buildJSON();
        LOG.config("Area Initialized");
    }

    public Area(JSONObject json) {
        this.json = json;
        this.name = json.getString(NAME_KEY);
        this.width = json.getInt(WIDTH_KEY);
        this.height = json.getInt(HEIGHT_KEY);
        this.sprites = new SpriteCollection(json.getJSONArray(SPRITES_KEY));
        LOG.config("Area Initialized");
    }

    public Area(File f) {
        this(Objects.requireNonNull(FolderOP.getJSON(f)));
    }

    public Area(String pathToJSON) {
        this(Objects.requireNonNull(FolderOP.getJSON(pathToJSON)));
    }

    /**
     * @param sprite
     */
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    /**
     * @param sprites
     */
    public void setSprites(SpriteCollection sprites) {
        this.sprites = sprites;
        this.updateJSON(sprites);
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
        this.updateJSON(name);
    }

    /**
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
        this.updateJSON(this.width, this.height);
    }

    /**
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
        this.updateJSON(this.width, this.height);
    }

    /**
     * @return SpriteCollection
     */
    public SpriteCollection getSprites() {
        return sprites;
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
    public int getWidth() {
        return width;
    }

    /**
     * @return int
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return JSONObject
     */
    public JSONObject getJSONArea() {
        return json;
    }

    private void buildJSON() {
        this.json.put(NAME_KEY, name);
        this.json.put(WIDTH_KEY, width);
        this.json.put(HEIGHT_KEY, height);
        this.json.put(SPRITES_KEY, sprites.toJSONArray());
    }

    private void updateJSON() {
        this.json.remove(NAME_KEY);
        this.json.remove(WIDTH_KEY);
        this.json.remove(HEIGHT_KEY);
        this.json.remove(SPRITES_KEY);
        buildJSON();
    }

    /**
     * @param name
     */
    private void updateJSON(String name) {
        this.json.remove(NAME_KEY);
        this.json.put(NAME_KEY, name);
    }

    /**
     * @param width
     * @param height
     */
    private void updateJSON(int width, int height) {
        this.json.remove(WIDTH_KEY);
        this.json.remove(HEIGHT_KEY);
        this.json.put(WIDTH_KEY, width);
        this.json.put(HEIGHT_KEY, height);
        this.json.put(NAME_KEY, name);
    }

    /**
     * @param sprite
     */
    private void updateJSON(SpriteCollection sprite) {
        this.json.remove(SPRITES_KEY);
        this.json.put(SPRITES_KEY, sprites.toJSONArray());
    }

    /**
     * @return List<ItemSprite>
     */
    public List<ItemSprite> getItems() {
        List<ItemSprite> items = new ArrayList<>();
        for (Sprite s : sprites) {
            if (s.getCategoryName().equals("item")) {
                items.add((ItemSprite) s);
            }
        }
        return items;
    }

    /**
     * @param items
     */
    public void addItems(List<ItemSprite> items) {
        this.spritesItems.addAll(items);
    }

    /**
     * @param item
     */
    public void removeItem(ItemSprite item) {
        this.spritesItems.remove(item);
    }

    /**
     * @return List<ItemSprite>
     */
    public List<ItemSprite> getSpritesItems() {
        return spritesItems;
    }
}
