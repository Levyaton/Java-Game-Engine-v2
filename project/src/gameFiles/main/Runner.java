package gameFiles.main;

import engineFiles.GUIs.mainGameGui.GameGUIArea;
import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.game.GameContainer;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.FolderOP;
import engineFiles.ui.Player;
import engineFiles.ui.Sprite;

import java.io.File;

public class Runner {
    public static void main(String[] args) {
        String spritePathDir = "project/src/gameFiles/models/sprites/static/";
        // BufferedImage test = null;
        String playerPath = spritePathDir+"other/redSquare.png";
        String backgroundPath = spritePathDir+"backgrounds/iceBlue.png";
        OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());

        GameGUIArea area = new GameGUIArea(FolderOP.getSprites(spritePathDir), player, new Sprite(backgroundPath));
        GameGUIFrame frame = new GameGUIFrame(area);
        new GameContainer(frame).run();
    }
}
