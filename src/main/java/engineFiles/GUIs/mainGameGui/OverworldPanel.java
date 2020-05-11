package engineFiles.GUIs.mainGameGui;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class OverworldPanel extends GamePanel{

    private ArrayList<Integer> up = Settings.controlls.getUp();
    private ArrayList<Integer> down = Settings.controlls.getDown();
    private ArrayList<Integer> left = Settings.controlls.getLeft();
    private ArrayList<Integer> right = Settings.controlls.getRight();


    private int frameCount = 0;
    private Image dbImage;
    private Graphics dbGraphics;


    public OverworldPanel(Area area, OverworldPlayer player, String panelName) {
        super(area,player,panelName);
        //Dimension d = new Dimension(800, 700);
        setLayout(null);
        loadSprites(area.getSprites());
        loadPlayer();
        setDoubleBuffered(true);
        requestFocus();
        //System.out.println(hasFocus());
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
        //System.out.println("Pressed " + e);
        KeyMap.setKey(e.getKeyCode(), false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
    }


    @Override
    public SpriteCollection getSprites() {
        Coordinates offest = this.getOffset();
        SpriteCollection result = new SpriteCollection();

       // System.out.println("width: " + rect.width + " height: " + rect.height);
        for(Sprite s: this.area.getSprites()) {
            if(offest.getBounds().intersects(s.getCoord().getBounds())){
                if(s.equals(player)){
                    //System.out.println("Player found");
                }
                result.add(s);
            }
        }
       // System.out.println(result.size());
        return result;
    }


    public void setArea(Area area) {
        this.area = area;
    }


    private boolean colliding(Sprite checked){

        for(Sprite s:area.getSprites()){
            if(s.isSolid() && !s.equals(checked)) {
                Rectangle rect1 = checked.getCoord().getBounds();
                Rectangle rect2 = s.getCoord().getBounds();
                if ( rect1.intersects(rect2) ) {
                    s.defaultCollision(checked);
                    System.out.println("Player Coords: [" + checked.getCoord().getX() + ", " + checked.getCoord().getY() + "]");
                    System.out.println("Sprite Coords: [" + s.getCoord().getX() + ", " + s.getCoord().getY() + "]");
                    return true;
                }
            }
        }
        return false;
    }

    public Void updateMovement(Void param) {


        if (KeyMap.isPressed(right)) {
            player.getCoord().moveRight();
            //System.out.println("Moving");
            if(colliding(player)){
                player.getCoord().moveLeft();
                //System.out.println("Failed");
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

    @Override
    public Coordinates getOffset(){
        int EXTRA_SPACE_MOD = 20;
        int width = Resolution.SCREEN_WIDTH + EXTRA_SPACE_MOD;
        int height = Resolution.SCREEN_HEIGHT + EXTRA_SPACE_MOD;
       // System.out.println(width);
        int offset_X = player.getCoord().getX() -  width/2;
        int offset_y = player.getCoord().getY() -  height/2;
        return new Coordinates(offset_X,offset_y,width,height);
    }

}
