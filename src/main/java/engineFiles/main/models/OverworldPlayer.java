package engineFiles.main.models;

import engineFiles.main.models.Entities.ControllableEntity;
import engineFiles.ui.Player;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

public class OverworldPlayer extends ControllableEntity {

    private Player player;

    public OverworldPlayer(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, JSONObject json, Player player) {
        super(down, up, left, right, json);
        super.categoryName = "player";
        this.player = player;
    }

    public OverworldPlayer(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left, List<BufferedImage> right, File f, Player player) {
        super(down, up, left, right, f);
        super.categoryName = "player";
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }


}