package engineFiles.main.models;

import ui.Player;
import ui.Sprite;

import java.io.File;

public class OverworldPlayer extends Sprite {

    private Player player;
    public OverworldPlayer(File file,Player player) {
        super(file);
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
