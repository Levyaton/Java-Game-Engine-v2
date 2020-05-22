package engineFiles.GUIs.listElementWithImg;

import engineFiles.main.models.Sprites.Sprite;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_START;

public class SquareListElement extends JLayeredPane {
    Sprite sprite;
    JLabel img;

    public SquareListElement(Sprite sprite) {
        this.sprite = sprite;
        this.img = new JLabel(new ImageIcon(sprite.getImg()));
        this.add(img, getConstraints(3, 3, 3, 3));
    }

    private GridBagConstraints getConstraints(int left, int right, int top, int down) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = LINE_START;
        gbc.anchor = LINE_START;
        gbc.insets = new Insets(top, left, down, right);
        return gbc;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public JLabel getImg() {
        return img;
    }
}
