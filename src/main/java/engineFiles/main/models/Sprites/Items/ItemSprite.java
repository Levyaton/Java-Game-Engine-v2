package engineFiles.main.models.Sprites.Items;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.ui.Coordinates;
import engineFiles.ui.FolderOP;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ItemSprite extends Sprite {
    private Item item;
    //private BufferedImage temp;

    public ItemSprite(JSONObject json){
        super(json);
        categoryName = "item";
        item = new Item(super.name);
        item.setAttackMod(json.getInt("attackMod"));
        item.setDefMod(json.getInt("defMod"));
        item.setSpeedMod(json.getInt("speedMod"));
        item.setHealthMod(json.getInt("healthMod"));
        item.setCost(json.getInt("costMod"));
    }
    //File file, BufferedImage img, int dimMod, int x, int y, int z,int blockID, int width, int height, boolean solid, boolean movable

    public ItemSprite(File file, int dimMod, int x, int y, int z, Item item){
        this.ogFile = file;
        this.img = FolderOP.getImage(file);
        this.ogImg = img;
        this.categoryName = "item";
        this.path = this.ogFile.getPath();
        this.name = item.getName();
        this.defaultHeight = ogImg.getHeight();
        this.defaultWidth = ogImg.getWidth();
        this.currentHeight = defaultHeight*dimMod;
        this.currentWidth = defaultWidth*dimMod;
        this.coord = new Coordinates(x, y, z, currentWidth*dimMod, currentHeight*dimMod);
        json = updateJSON();
        this.solid = true;
        this.movable = false;
        this.item = item;
    }

    public ItemSprite(File file, int dimMod, int x, int y, int z, String name, int healthMod, int speedMod, int defMod, int attackMod){
        this(file,dimMod,x,y,z,new Item(name, healthMod, speedMod, defMod, attackMod));
    }


    @Override
    public boolean onCollision(Sprite s){
        System.out.println("Space pressed: " + KeyMap.isPressed(Settings.controlls.getInteraction()));
        if(KeyMap.isPressed(Settings.controlls.getInteraction()) && (s.getCategoryName().equals("player"))){
            ((OverworldPlayer) s).getPlayer().addItem(this.item);
            return false;
        }
        return true;


    }


}