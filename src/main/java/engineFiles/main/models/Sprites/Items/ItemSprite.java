package engineFiles.main.models.Sprites.Items;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.ui.Coordinates;
import engineFiles.ui.FolderOP;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;

public class ItemSprite extends Sprite {
    private Item item;
    private int dimMod;
    // private BufferedImage temp;

    public ItemSprite(JSONObject json) {
        super(json);
        categoryName = "item";
        item = new Item(super.name);
        item.setAttackMod(json.getInt("attackMod"));
        item.setSpeedMod(json.getInt("speedMod"));
        item.setHealthMod(json.getInt("healthMod"));
        item.setCost(json.getInt("costMod"));
    }
    // File file, BufferedImage img, int dimMod, int x, int y, int z,int blockID,
    // int width, int height, boolean solid, boolean movable

    public ItemSprite(File file, int dimMod, int x, int y, int z, Item item) {
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
    }

    public ItemSprite(File file, int dimMod, int x, int y, int z, String name, int healthMod, int speedMod, int defMod,
            int attackMod) {
        this(file, dimMod, x, y, z, new Item(name, healthMod, speedMod, defMod, attackMod));
    }

    public void pickItem() {

    }

    public Item getItem() {
        return item;
    }

    public int getDimMod() {
        return dimMod;
    }
}
