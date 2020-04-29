package engineFiles.GUIs;

import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Player;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class testGui {
    public static void main(String[] args) throws IOException {

        String spritePathDyn = "project/src/gameFiles/models/sprites/static/";
        // BufferedImage test = null;
        String playerPath = spritePathDyn + "other/redSquare.png";
        String backgroundPath = spritePathDyn + "backgrounds/iceBlue.png";
        // test = ImageIO.read(new File(playerPath));

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                //new WorldEditGUI2();
                String spritePathDir = "project/src/gameFiles/models/sprites/static/";
                // BufferedImage test = null;
                String playerPath = spritePathDir + "other/redSquare.png";
                String backgroundPath = spritePathDir + "backgrounds/iceBlue.png";
                OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());

               // GameGUIArea area = new GameGUIArea(FolderOP.getSprites(spritePathDir), player);
               // GameGUIFrame frame = new GameGUIFrame(area);

            }

        });


    }
}
