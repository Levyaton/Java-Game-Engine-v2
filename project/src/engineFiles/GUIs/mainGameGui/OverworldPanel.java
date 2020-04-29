package engineFiles.GUIs.mainGameGui;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Area;
import engineFiles.ui.Settings;
import engineFiles.ui.Sprite;
import engineFiles.ui.SpriteCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class OverworldPanel extends GamePanel {

    private ArrayList<Integer> up = Settings.controlls.getUp();
    private ArrayList<Integer> down = Settings.controlls.getDown();
    private ArrayList<Integer> left = Settings.controlls.getLeft();
    private ArrayList<Integer> right = Settings.controlls.getRight();


    private int frameCount = 0;
    private Image dbImage;
    private Graphics dbGraphics;


    public OverworldPanel(Area area, OverworldPlayer player) {
        super(area,player);
        setLayout(null);
        loadSprites(area.getSprites());
        loadPlayer();
        setDoubleBuffered(true);

    }

    private void loadSprites(ArrayList<Sprite> sprites) {
        for (Sprite s : sprites) {
            loadSprite(s);
        }
    }

    private void loadPlayer() {
        area.getSprites().add(player);
    }

    private JLabel loadSprite(Sprite s) {
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        image.setDoubleBuffered(true);
        add(image);
        image.setBounds(s.getCoord().getX(), s.getCoord().getY(), s.getCurrentWidth(), s.getCurrentHeight());
        s.setComponent(image);
        return image;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
    }


    @Override
    public SpriteCollection getSprites() {
        return area.getSprites();
    }


    public void setArea(Area area) {
        this.area = area;
    }


    private boolean colliding(Sprite checked){

        for(Sprite s:area.getSprites()){
            if(s.isSolid() && !s.equals(checked)) {
                Rectangle rect1 = checked.getBounds();
                Rectangle rect2 = s.getBounds();
                if (
                        rect1.intersects(rect2)
                ) {
                    s.defaultCollision(checked);
                    return true;
                }
            }
        }
        return false;
    }

    public Void updateMovement(Void param) {
        if (KeyMap.isPressed(right)) {
            player.getCoord().moveRight();
            if(colliding(player)){
                player.getCoord().moveLeft();
            }
        }
        if (KeyMap.isPressed(left)) {
            player.getCoord().moveLeft();
            if(colliding(player)){
                player.getCoord().moveRight();
            }
        }
        if (KeyMap.isPressed(down)) {
            player.getCoord().moveDown();
            if(colliding(player)){
                player.getCoord().moveUp();
            }
        }
        if (KeyMap.isPressed(up)) {
            player.getCoord().moveUp();
            if(colliding(player)){
                player.getCoord().moveDown();
            }
        }
        return null;
    }

}
