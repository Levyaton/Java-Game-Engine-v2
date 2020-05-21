package engineFiles.main.models;

import engineFiles.main.models.Entities.ControllableEntity;
import engineFiles.ui.Player;
import org.json.JSONObject;

import java.io.File;

public class OverworldPlayer extends ControllableEntity {

    private Player player;

    public OverworldPlayer(MovementAnimation animation, JSONObject json, Player player, int speedCounter) {
        super(animation, json, speedCounter);
        super.categoryName = "player";
        this.player = player;
    }

    public OverworldPlayer(MovementAnimation animation, File f, Player player, int speedCounter) {
        super(animation, f, speedCounter);
        super.categoryName = "player";
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }



}