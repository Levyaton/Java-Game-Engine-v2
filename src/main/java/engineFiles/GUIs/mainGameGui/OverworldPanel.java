package engineFiles.GUIs.mainGameGui;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Entities.Entity;
import engineFiles.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class OverworldPanel extends GamePanel{

    private ArrayList<Integer> up = Settings.controlls.getUp();
    private ArrayList<Integer> down = Settings.controlls.getDown();
    private ArrayList<Integer> left = Settings.controlls.getLeft();
    private ArrayList<Integer> right = Settings.controlls.getRight();




    private int frameCount = 0;
    private Image dbImage;
    private Graphics dbGraphics;


    public OverworldPanel(Area area, List<Entity> entities, String panelName) {
        super(area,entities,panelName);
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
        area.getSprites().addAll(entities);
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
                result.add(s);
            }
        }
        for(Entity e: this.entities){
            if(offest.getBounds().intersects(e.getCoord().getBounds())){
                result.add(e);
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

        for(Entity e: this.entities){
            e.getMovement();
            if(colliding(e)){
                e.movementBlocked();
                //System.out.println("Failed");
            }

        }
        return null;
    }

    @Override
    public Coordinates getOffset(){
        Entity player = this.getPlayer();
        int offset_X = 0;
        int offset_y = 0;

        int EXTRA_SPACE_MOD = 20;
        int width = Resolution.SCREEN_WIDTH + EXTRA_SPACE_MOD;
        int height = Resolution.SCREEN_HEIGHT + EXTRA_SPACE_MOD;

        if(player != null){
            offset_X = player.getCoord().getX() -  width/2;
            offset_y = player.getCoord().getY() -  height/2;
        }

       // System.out.println(width);

        return new Coordinates(offset_X,offset_y,width,height);
    }

    private Entity getPlayer(){
        for (Entity e: entities){
            if(e.getCategoryName().equals("player")){
                return e;
            }
        }

        return null;
    }

}
