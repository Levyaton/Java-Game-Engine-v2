package engineFiles.main.models.Sprites.Items;

import engineFiles.main.models.Sprites.Sprite;
import engineFiles.ui.Coordinates;
import engineFiles.ui.FolderOP;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the overworld logic of items. They are displayed as sprites
public class ItemSprite extends Sprite {
    private static final Logger LOG = Logger.getLogger(ItemSprite.class.getName());
    private Item item;
    private int dimMod;

    // private BufferedImage temp;
    public ItemSprite(JSONObject json) {
        super(json);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        categoryName = "item";
        item = new Item(super.name);
        item.setAttackMod(json.getInt("attackMod"));
        item.setSpeedMod(json.getInt("speedMod"));
        item.setHealthMod(json.getInt("healthMod"));
        item.setCost(json.getInt("costMod"));
        LOG.config("ItemSprite Initialized");
    }
    // File file, BufferedImage img, int dimMod, int x, int y, int z,int blockID,
    // int width, int height, boolean solid, boolean movable

    public ItemSprite(File file, int dimMod, int x, int y, int z, Item item) {
        LOG.setUseParentHandlers(false);
        this.dimMod = dimMod;
        this.ogFile = file;
        this.img = FolderOP.getImage(file);
        this.ogImg = img;
        this.categoryName = "item";
        this.path = this.ogFile.getPath();
        this.name = item.getName();
        this.defaultHeight = ogImg.getHeight();
        this.defaultWidth = ogImg.getWidth();
        this.currentHeight = defaultHeight * dimMod;
        this.currentWidth = defaultWidth * dimMod;
        this.coord = new Coordinates(x, y, z, currentWidth * dimMod, currentHeight * dimMod);
        json = updateJSON();
        this.solid = true;
        this.movable = false;
        this.item = item;
        LOG.config("ItemSprite Initialized");
    }

    public ItemSprite(File file, int dimMod, int x, int y, int z, String name, int healthMod, int speedMod, int defMod,
            int attackMod) {
        this(file, dimMod, x, y, z, new Item(name, healthMod, speedMod, defMod, attackMod));
        LOG.config("ItemSprite Initialized");

    }

    public void pickItem() {

    }

    /**
     * @return Item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return int
     */
    public int getDimMod() {
        return dimMod;
    }
}
