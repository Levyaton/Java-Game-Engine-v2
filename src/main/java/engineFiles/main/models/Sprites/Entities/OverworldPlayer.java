package engineFiles.main.models.Sprites.Entities;

import engineFiles.ui.Player;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the logic for the overworld player entity. 
public class OverworldPlayer extends ControllableEntity {
    private static final Logger LOG = Logger.getLogger(OverworldPlayer.class.getName());
    private Player player;

    public OverworldPlayer(MovementAnimation animation, JSONObject json, Player player, int speedCounter) {
        super(animation, json, speedCounter);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        super.categoryName = "player";
        this.health = 10;
        this.curHealth = 10;
        this.damage = 2;
        this.player = player;
        LOG.config("OverworldPlayer Initialized");
    }

    public OverworldPlayer(MovementAnimation animation, File f, Player player, int speedCounter) {
        super(animation, f, speedCounter);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.categoryName = "player";
        this.health = 10;
        this.curHealth = 10;
        this.damage = 2;
        this.player = player;
        LOG.config("OverworldPlayer Initialized");
    }

    /**
     * @return Player
     */
    public Player getPlayer() {
        return player;
    }

}