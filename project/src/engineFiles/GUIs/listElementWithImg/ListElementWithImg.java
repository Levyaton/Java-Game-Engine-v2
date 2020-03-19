package engineFiles.GUIs.listElementWithImg;

import javax.swing.*;
import java.awt.*;

public class ListElementWithImg extends JPanel{
    private JTextField elementName;
    private JPanel elementPreview;
    private String name;
    private Image sprite;

    private class 

    public ListElementWithImg(String name, Image sprite){
        this.sprite = sprite;
        this.name = name;
        this.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.drawImage(sprite, 0, 0, null);
    }
}
