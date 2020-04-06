package engineFiles.GUIs;

import engineFiles.GUIs.mainGameGui.GameGUIArea;
import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.models.OverworldPlayer;
import ui.Player;
import ui.Sprite;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class testGui {
    public static void main(String[] args) throws IOException {

        String spritePathDyn = "project/src/gameFiles/models/sprites/static/";
       // BufferedImage test = null;
        String playerPath = spritePathDyn+"other/redSquare.png";
        String backgroundPath = spritePathDyn+"backgrounds/iceBlue.png";
       // test = ImageIO.read(new File(playerPath));

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //new WorldEditGUI2();
                OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());
                player.setCurrentWidth(10);
                player.setCurrentHeight(10);
                GameGUIArea area = new GameGUIArea(new Sprite[0], player, new Sprite(new File((backgroundPath))));
                GameGUIFrame frame = new GameGUIFrame(area);


            }

        });




    }
}
