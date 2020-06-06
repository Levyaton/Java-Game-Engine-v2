package engineFiles.main.game;

import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.Sprites.SpriteCollection;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel {
    Image canvas;
    SpriteCollection staticSprites;
    SpriteCollection dynamicSprites;
    OverworldPlayer player;
    private Image dbImage;
    private Graphics dbGraphics;

    public GameArea(SpriteCollection dynamicSprites, SpriteCollection staticSprites, OverworldPlayer player) {

    }


    public SpriteCollection getAllSprites() {
        SpriteCollection sprites = (SpriteCollection) staticSprites.clone();
        for (Sprite s : dynamicSprites) {
            sprites.add(s);
        }
        return sprites;
    }

    private void paint(Graphics g, Sprite sprite) {
        dbImage = createImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        dbGraphics = dbImage.getGraphics();
        for (Sprite s : getAllSprites()) {

        }
        paintComponent(dbGraphics, sprite);
        g.drawImage(dbImage, 0, 0, this);
    }

    protected void paintComponent(Graphics g, Sprite sprite) {
        // super.paintComponent(g);
        //  System.out.println("X: " + sprite.getCoord().getX());
        //System.out.println("Y: " + sprite.getCoord().getY());
        // ((JLabel)sprite.getComponent()).paintImmediately(sprite.getCoord().getX(), sprite.getCoord().getY(), sprite.getCurrentWidth(), sprite.getCurrentHeight());
        g.drawImage(sprite.getImg(), sprite.getCoord().getX(), sprite.getCoord().getY(), this);
        //revalidate();
//        repaint();
    }
}
