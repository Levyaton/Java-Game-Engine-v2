package engineFiles.ui;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import engineFiles.main.models.Sprites.Items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

import static engineFiles.main.models.WorldGenKeys.PlayerKeys.INVENTORY_KEY;
import static engineFiles.main.models.WorldGenKeys.PlayerKeys.USERNAME_KEY;

public class Player {
    private static final Logger LOG = Logger.getLogger(Player.class.getName());
    private Gson gson = new Gson();
    private String username;
    private List<Item> inventory = new ArrayList<>();

    public Player(JsonObject json) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.username = json.get(USERNAME_KEY).getAsString();
        for (JsonElement el : json.get(INVENTORY_KEY).getAsJsonArray()) {
            this.inventory.add(gson.fromJson(el.getAsJsonObject(), Item.class));
        }
        LOG.config("Player Initialized");
    }

    public Player(String username, List<Item> inventory) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.username = username;
        this.inventory = inventory;
        LOG.config("Player Initialized");
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @param inventory
     */
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    /**
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return List<Item>
     */
    public List<Item> getInventory() {
        return inventory;
    }

    /**
     * @param item
     */
    public void addItem(Item item) {
        LOG.info("Added item named " + item.getName());
        this.inventory.add(item);
    }
}
