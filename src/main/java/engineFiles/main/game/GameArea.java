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

    protected void paintComponent(Graphics g, Sprite sprite) {
        g.drawImage(sprite.getImg(), sprite.getCoord().getX(), sprite.getCoord().getY(), this);
    }
}
