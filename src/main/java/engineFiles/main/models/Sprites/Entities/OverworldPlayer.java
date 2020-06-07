package engineFiles.main.models.Sprites.Entities;

import engineFiles.ui.Player;
import org.json.JSONObject;

import java.io.File;
//Class containing the logic for the overworld player entity. 
public class OverworldPlayer extends ControllableEntity {

    private Player player;

    public OverworldPlayer(MovementAnimation animation, JSONObject json, Player player, int speedCounter) {
        super(animation, json, speedCounter);
        super.categoryName = "player";
        this.health = 10;
        this.curHealth = 10;
        this.damage = 2;
        this.player = player;
    }

    public OverworldPlayer(MovementAnimation animation, File f, Player player, int speedCounter) {
        super(animation, f, speedCounter);
        this.categoryName = "player";
        this.health = 10;
        this.curHealth = 10;
        this.damage = 2;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

}