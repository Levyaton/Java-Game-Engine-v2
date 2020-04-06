package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.OverworldPlayer;
import ui.Sprite;

import javax.swing.*;
import java.awt.*;

public class GameGUIArea extends JPanel {

    Sprite background;
    Sprite[] sprites;
    OverworldPlayer player;
    JLabel playerL;

    public GameGUIArea(Sprite[] sprites, OverworldPlayer player, Sprite background){
        this.sprites = sprites;
        this.player = player;
        this.background = background;
        setLayout(null);
        loadSprites();
        loadBackground();
        loadPlayer();

        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());

    }

    private void loadSprites(){
        for (Sprite s: sprites) {
           loadSprite(s);
        }
    }

    private void loadPlayer(){
        playerL = loadSprite(player);
    }

    private JLabel loadSprite(Sprite s){
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        add(image);
        image.setBounds(s.getCoord().getX(),s.getCoord().getY(),s.getCurrentWidth(),s.getCurrentHeight());
        return image;
    }

    private void loadBackground(){
        ImageIcon icon = new ImageIcon(background.getPath());
        JLabel thumb = new JLabel();
        add(thumb);
        thumb.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        thumb.setIcon(icon);

    }








}
