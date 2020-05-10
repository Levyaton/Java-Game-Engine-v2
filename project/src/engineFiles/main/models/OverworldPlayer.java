package engineFiles.main.models;

import engineFiles.ui.Player;
import engineFiles.ui.Sprite;

import java.io.File;

public class OverworldPlayer extends Sprite {

    private Player player;

    public OverworldPlayer(File file, Player player) {
        super(file);
        this.movable = true;
        this.player = player;

    }

    public Player getPlayer() {
        return player;
    }


}