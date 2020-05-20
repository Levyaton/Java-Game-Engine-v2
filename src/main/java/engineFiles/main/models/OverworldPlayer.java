package engineFiles.main.models;

import engineFiles.main.models.Entities.ControllableEntity;
import engineFiles.ui.Player;
import org.json.JSONObject;

import java.io.File;

public class OverworldPlayer extends ControllableEntity {

    private Player player;

    public OverworldPlayer(MovementAnimation animation, JSONObject json, Player player, double speed) {
        super(animation, json, speed);
        super.categoryName = "player";
        this.player = player;
    }

    public OverworldPlayer(MovementAnimation animation, File f, Player player, double speed) {
        super(animation, f, speed);
        super.categoryName = "player";
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }



}