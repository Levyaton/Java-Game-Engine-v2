package engineFiles.GUIs.worldEditGUI;

import engineFiles.GUIs.listElementWithImg.ListElementWithImg;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class WorldEditGUI extends JFrame{
    private JButton bckImgButton;
    private JButton Save;
    private JList spriteList;
    private JPanel preview;
    private JTextField areaName;
    private JScrollPane spriteSelect;
    private JPanel body;

    private String spritePathDyn;
    private String spritePathStat;

    public WorldEditGUI(){
        this.spritePathDyn =  "project/src/gameFiles/models/sprites/static/";
        setSpriteList(spritePathDyn);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(body);
        this.pack();
    }

    private void setSpriteList(String spritePathDyn) {
        Image test = null;
        try {
            test = ImageIO.read(new File(spritePathDyn+"other/redSquare.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(spritePathStat+"other/redSquare.png");
        }
        spriteSelect.add(new ListElementWithImg("test",test));
    }
}
