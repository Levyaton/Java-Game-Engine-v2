package engineFiles.GUIs;

import engineFiles.GUIs.worldEditGUI.WorldEditGUI2;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class testGui {
    public static void main(String[] args) throws IOException {
        String spritePathDyn = "project/src/gameFiles/models/sprites/static/";
        BufferedImage test = null;
        String path = spritePathDyn+"other/redSquare.png";
        test = ImageIO.read(new File(path));

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new WorldEditGUI2();
            }

        });


    }
}
