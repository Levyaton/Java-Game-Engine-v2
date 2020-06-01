package engineFiles.ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.ArrayList;
import java.util.List;
import static engineFiles.main.models.WorldGenKeys.PlayerKeys.*;
public class Player {
    private Gson gson = new Gson();
    private String username;
    private List<Item> inventory = new ArrayList<>();
    public Player(JsonObject json){
        this.username = json.get(USERNAME_KEY).getAsString();
        for (JsonElement el : json.get(INVENTORY_KEY).getAsJsonArray()){
            this.inventory.add(gson.fromJson(el.getAsJsonObject(), Item.class));
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public String getUsername() {
        return username;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item){
        System.out.println("Added item named " + item.getName());
        this.inventory.add(item);
    }
}