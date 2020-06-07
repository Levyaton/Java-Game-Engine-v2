package engineFiles.main.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import engineFiles.main.models.Sprites.Items.Item;
import engineFiles.main.models.Sprites.Items.ItemSprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static engineFiles.main.models.WorldGenKeys.InventoryKeys.*;
import static engineFiles.main.models.WorldGenKeys.OverworldItemsKeys.*;

//Class containing the item model. Used to translate items to a json objects, and vice-versa
public class ItemsModel {

    public static List<ItemSprite> generateOverworldItems(JsonArray items) {
        List<ItemSprite> itemSprites = new ArrayList<>();
        for (JsonElement el : items) {
            JsonObject item = el.getAsJsonObject();
            String name = item.get(ITEM_NAME_KEY).getAsString();
            File file = new File(item.get(FILE_PATH_KEY).getAsString());
            int dimMod = item.get(DIM_MOD_KEY).getAsInt();
            int x = item.get(X_KEY).getAsInt();
            int y = item.get(Y_KEY).getAsInt();
            int z = item.get(Z_KEY).getAsInt();
            int cost = item.get(COST_KEY).getAsInt();
            int healthMod = item.get(HEALTH_MOD_KEY).getAsInt();
            int speedMod = item.get(SPEED_MOD_KEY).getAsInt();
            int attackMod = item.get(ATTACK_MOD_KEY).getAsInt();
            String description = item.get(DESCRIPTION_KEY).getAsString();
            itemSprites
                    .add(new ItemSprite(file, dimMod, x, y, z, new Item(name, healthMod, speedMod, attackMod, description)));
        }
        return itemSprites;
    }

    public static JsonArray generateOverworldItemsJson(List<ItemSprite> items) {
        JsonArray array = new JsonArray();
        for (ItemSprite item : items) {
            JsonObject obj = new JsonObject();
            obj.addProperty(ITEM_NAME_KEY, item.getItem().getName());
            obj.addProperty(FILE_PATH_KEY, (item.getOgFile().getPath()).replace("\\", "/"));
            obj.addProperty(DIM_MOD_KEY, item.getDimMod());
            obj.addProperty(X_KEY, item.getCoord().getX());
            obj.addProperty(Y_KEY, item.getCoord().getY());
            obj.addProperty(Z_KEY, item.getCoord().getZ());
            obj.addProperty(COST_KEY, item.getItem().getCost());
            obj.addProperty(HEALTH_MOD_KEY, item.getItem().getHealthMod());
            obj.addProperty(SPEED_MOD_KEY, item.getItem().getSpeedMod());
            obj.addProperty(ATTACK_MOD_KEY, item.getItem().getAttackMod());
            obj.addProperty(DESCRIPTION_KEY, item.getItem().getDescription());
            array.add(obj);
        }
        return array;
    }
}
