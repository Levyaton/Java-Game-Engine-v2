package engineFiles.GUIs.listElementWithImg;

import engineFiles.ui.FolderOP;
import engineFiles.ui.Sprite;

import javax.swing.*;
import java.awt.*;

import static java.awt.GridBagConstraints.LINE_START;

public class RowListElement extends JLayeredPane {
    String textS;
    String imgPath;
    JLabel preview;
    JTextField textF;
    Sprite sprite;

    public RowListElement(String textS, String imgPath, int width, int height) {
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridBagLayout());
        this.textS = textS;
        this.imgPath = imgPath;
        this.sprite = FolderOP.getSprite(this.imgPath);
        generateIcon(height);
        generateText();
        this.add(preview, getConstraints(10, 2, 4, 4));
        this.add(textF, getConstraints(2, 2, 6, 6));
    }

    private void generateIcon(int height) {
        preview = new JLabel(new ImageIcon(imgPath));
        preview.setPreferredSize(new Dimension(height / 2, height / 2));
        preview.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    private void generateText() {
        textF = new JTextField();
        textF.setText(textS);
    }

    private GridBagConstraints getConstraints(int left, int right, int top, int down) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = LINE_START;
        gbc.anchor = LINE_START;
        gbc.insets = new Insets(top, left, down, right);
        return gbc;
    }


    public String getTextS() {
        return textS;
    }

    public void setTextS(String textS) {
        this.textS = textS;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public JLabel getPreview() {
        return preview;
    }

    public void setPreview(JLabel preview) {
        this.preview = preview;
    }

    public JTextField getTextF() {
        return textF;
    }

    public void setTextF(JTextField textF) {
        this.textF = textF;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}



