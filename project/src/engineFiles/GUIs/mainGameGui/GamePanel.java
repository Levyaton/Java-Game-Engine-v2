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
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GamePanel extends JPanel implements KeyListener {

    private ArrayList<Integer> up = Settings.controlls.getUp();
    private ArrayList<Integer> down = Settings.controlls.getDown();
    private ArrayList<Integer> left = Settings.controlls.getLeft();
    private ArrayList<Integer> right = Settings.controlls.getRight();

    private OverworldPlayer player;
    private int frameCount = 0;
    //DoubleBuffer
    private Image dbImage;
    private Graphics dbGraphics;
    private Area area;

    public GamePanel(Area area, OverworldPlayer player) {
        this.player = player;
        this.area = area;
        setLayout(null);
        loadSprites(area.getSprites());
        loadPlayer();

        //setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        //requestFocus();

        setDoubleBuffered(true);
        this.addKeyListener(this);

    }

    public OverworldPlayer getPlayer() {
        return player;
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
        /*
        if(s.isMovable()){
            movableSprites.add(s);
        }
        else{
            staticSprites.add(s);
        }

         */
        //area.getSprites().add(s);
        return image;
    }


    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e);
//        if(up.contains(e.getKeyCode())){
//            KeyMap.isUp = true;
//        }
//        if(down.contains(e.getKeyCode())){
//            KeyMap.isDown = true;
//        }
//        if(left.contains(e.getKeyCode())){
//            KeyMap.isLeft = true;
//        }
//        if(right.contains(e.getKeyCode())){
//            KeyMap.isRight = true;
//        }
    }

    public void keyPressed(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), false);
    }


    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
    }

    public void moveUp() {
        // System.out.println("up");
        this.player.getCoord().moveUp();
    }

    public void moveDown() {
        //System.out.println("down");
        this.player.getCoord().moveDown();
    }

    public void moveLeft() {
        //  System.out.println("left");
        this.player.getCoord().moveLeft();
    }

    public void moveRight() {
        // System.out.println("right");
        this.player.getCoord().moveRight();
    }

    public SpriteCollection getSprites() {
        return area.getSprites();
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    private boolean collision(int x, int y, int width, int height){
        System.out.println("Player: [" + x + ";" + y + "]");
        for(Sprite s:area.getSprites()){
            if(s.isSolid() && !s.equals(player)) {
                int spriteX = s.getCoord().getX();
                int spriteY = s.getCoord().getY();
                System.out.println("Sprite: [" + spriteX + ";" + spriteY + "]");
                if (
                    x >= spriteX &&
                    x <= spriteX + s.getCurrentWidth() &&
                    y >= spriteY &&
                    y <= spriteY + s.getCurrentHeight()
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    public Void updateMovement(Void param) {
        int x = player.getCoord().getX();
        int y = player.getCoord().getY();
        int playerMovementMod = player.getCoord().getMOD();
        int width = player.getCurrentWidth();
        int height = player.getCurrentHeight();
        //Pressing two keys results in faster movement, fix later
        if (KeyMap.isPressed(right) && !collision(x + playerMovementMod, y, width, height)) {
            moveRight();
            x = player.getCoord().getX();

        }
        if (KeyMap.isPressed(left) && !collision(x - playerMovementMod, y, width, height)) {
            moveLeft();
            x = player.getCoord().getX();

        }
        if (KeyMap.isPressed(down) && !collision(x, y  + playerMovementMod, width , height)) {
           moveDown();
            y = player.getCoord().getY();
        }
        if (KeyMap.isPressed(up)  && !collision(x, y - playerMovementMod, width, height)) {
            moveUp();
            y = player.getCoord().getY();
        }
        return null;
    }

}

