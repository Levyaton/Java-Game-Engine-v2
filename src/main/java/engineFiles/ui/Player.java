package engineFiles.ui;

import engineFiles.main.models.Sprites.Items.Item;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private List<Item> inventory = new ArrayList<>();


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
