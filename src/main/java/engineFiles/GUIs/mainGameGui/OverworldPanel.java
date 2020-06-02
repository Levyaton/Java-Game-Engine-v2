package engineFiles.GUIs.mainGameGui;

import engineFiles.GUIs.ColorSwitchGui;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Area;
import engineFiles.main.models.SaveGame;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.WorldGenModel;
import engineFiles.ui.Coordinates;
import engineFiles.ui.Resolution;
import engineFiles.ui.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class OverworldPanel extends GamePanel{

    private List<Integer> up = Settings.controlls.getUp();
    private List<Integer> down = Settings.controlls.getDown();
    private List<Integer> left = Settings.controlls.getLeft();
    private List<Integer> right = Settings.controlls.getRight();

    int SPACE_MOD1 = 100;
    int SPACE_MOD2 = 50;

    ColorSwitchGui colorSwitchGui;
    private WorldGenModel worldGenModel;

    private int frameCount = 0;
    private Image dbImage;
    private Graphics dbGraphics;

    private boolean paused = false;

    public OverworldPanel(WorldGenModel worldGenModel, String panelName) {
        super(worldGenModel.getArea(),worldGenModel.getEntitiesModel().getAllEntities(),panelName);
        //Dimension d = new Dimension(800, 700);s
        setLayout(new BorderLayout());
        this.colorSwitchGui = new ColorSwitchGui(worldGenModel.getColorerModel().isRecolor());
        loadSprites(area.getSprites());
        loadPlayer();
        setDoubleBuffered(true);
        requestFocus();
        this.worldGenModel = worldGenModel;
        //System.out.println(hasFocus());
    }

    private void loadSprites(ArrayList<Sprite> sprites) {
        for (Sprite s : sprites) {
            loadSprite(s);
        }
    }

    private void loadPlayer() {
        //area.getSprites().addAll(entities);
    }

    private JLabel loadSprite(Sprite s) {
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        image.setDoubleBuffered(true);
        add(image);
        image.setBounds(s.getCoord().getX(), s.getCoord().getY(), s.getCurrentWidth(), s.getCurrentHeight());
        s.setComponent(image);
        return image;
    }

    private void saveGame(){
        System.out.println("Saving game");
        this.worldGenModel.getColorerModel().setRecolor(Boolean.getBoolean(this.colorSwitchGui.getColorChangeButton().getText()));
        SaveGame.save(this.worldGenModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Pressed " + e);
        if(e.getKeyCode() == Settings.controlls.getGameSave()){
            saveGame();
        }
        if(e.getKeyCode()==KeyEvent.VK_C){
            colorSwitchGui.setVisible(!colorSwitchGui.isVisible());
        }
        KeyMap.setKey(e.getKeyCode(), false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
        System.out.println("released");
    }


    @Override
    public Image getRenderGraphics() {
        Coordinates offest = this.getOffset();

        Graphics offgc;
        Image offscreen = null;


        // create the offscreen buffer and associated Graphics
        //System.out.println(area.getHeight());
        Dimension dimension = new Dimension(offest.getBounds().width, offest.getBounds().height);
        offscreen = createImage(dimension.width,dimension.height);
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.clearRect(0,0,dimension.width,dimension.height);
        offgc.setColor(getBackground());
        for(Sprite s: area.getSprites()){
            if(offest.getBounds().contains(s.getCoord().getBounds())){
                offgc.drawImage(s.getImg(),s.getCoord().getX() - offest.getX() - this.SPACE_MOD2, s.getCoord().getY() - offest.getY() -this.SPACE_MOD2, s.getCurrentWidth(), s.getCurrentHeight(), this);
            }

        }
        for(Entity e: entities){
            if(offest.getBounds().contains(e.getCoord().getBounds())){
                offgc.drawImage(e.getImg(),e.getCoord().getX() - offest.getX(), e.getCoord().getY() - offest.getY(), e.getCurrentWidth(), e.getCurrentHeight(), this);
            }
        }
        //System.out.println(entities.size());
        offgc.setColor(getForeground());

        //img.getSubimage(0,0, offest.getBounds().width, offest.getBounds().height);
        // do normal redraw
        //paint(offgc);
        // transfer offscreen to window
        return offscreen;
       // System.out.println("width: " + rect.width + " height: " + rect.height);
        /*
        for(Sprite s: this.area.getSprites()) {

            if(offest.getBounds().intersects(s.getCoord().getBounds())){
                result.add(s);
            }
        }
        */
        //result.add(area.getSprites().get(1));
//        for (int x = 0; x < 500; x++){
//            result.add(area.getSprites().get(x));
//
//        }
        /*
        for(Entity e: this.entities){
           if(offest.getBounds().intersects(e.getCoord().getBounds())){
                result.add(e);
           }
        }

        Entity p = getPlayer();
        ControllableEntity nextP = new ControllableEntity(p.getAnimation(), p.getOgFile());
        nextP.setName("JIGGLY");
        nextP.setCoord(new Coordinates(p.getCoord().getX() + 30, p.getCoord().getY() + 30, p.getCoord().getZ(), p.getCurrentWidth(), p.getCurrentHeight()));
        result.add(nextP);
        Entity p2 = getPlayer();
        ControllableEntity nextP2 = new ControllableEntity(p.getAnimation(), p.getOgFile());
        nextP2.setName("JIGGLY");
        nextP2.setCoord(new Coordinates(p2.getCoord().getX() + 60, p2.getCoord().getY() + 15, p2.getCoord().getZ(), p2.getCurrentWidth(), p2.getCurrentHeight()));
        result.add(nextP2);
       // System.out.println(result.size());

       */
    }


    public void setArea(Area area) {
        this.area = area;
    }


    private boolean colliding(Sprite checked){
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();

        List<Sprite> toBeRemoved = new ArrayList<>();
        rect1.setBounds(checked.getCoord().getX() - this.SPACE_MOD2, checked.getCoord().getY() - this.SPACE_MOD2, checked.getCurrentWidth(), checked.getCurrentHeight());
        for(Sprite s:area.getSprites()){
            if(s.isSolid() && !s.equals(checked)) {
                rect2.setBounds(s.getCoord().getX() - this.SPACE_MOD1, s.getCoord().getY() - this.SPACE_MOD1, s.getCurrentWidth(), s.getCurrentHeight());
               // s.getCoord().setX(s.getCoord().getX() - 50);
              //  s.getCoord().setY(s.getCoord().getY() - 50);
                if ( rect1.intersects(rect2) ) {
                    if(!s.onCollision(checked)){
                        area.getSprites().remove(s);
                        System.out.println("Got here");
                        toBeRemoved.add(s);
                    }
                    System.out.println("Player Coords: [" + checked.getCoord().getX() + ", " + checked.getCoord().getY() + "]");
                    System.out.println("Sprite Coords: [" + s.getCoord().getX() + ", " + s.getCoord().getY() + "]");
                    return true;
                }
            }
        }

        rect1.setBounds(checked.getCoord().getX(), checked.getCoord().getY(), checked.getCurrentWidth(), checked.getCurrentHeight());
        for(Entity e: entities){
            if(!e.equals(checked)){
                rect2.setBounds(e.getCoord().getX() /* - this.SPACE_MOD1*/, e.getCoord().getY() /*- this.SPACE_MOD1*/, e.getCurrentWidth(), e.getCurrentHeight());
                if ( rect1.intersects(rect2) ) {
                    e.onCollision(checked);
                    System.out.println("Player Coords: [" + checked.getCoord().getX() + ", " + checked.getCoord().getY() + "]");
                    System.out.println("Entity Coords: [" + e.getCoord().getX() + ", " + e.getCoord().getY() + "]");
                    return true;
                }
            }
        }
        return false;
    }

    public Void updateMovement(Void param) {

        if(!isGamePaused()){
            for(Entity e: this.entities){

                // System.out.println("Check");
                if(e.timeToMove()){
                    e.getMovement();

                    if(colliding(e)){
                        e.movementBlocked();
                        //System.out.println("Failed");
                    }
                }


            }
        }
        //System.out.println(entities.size());

    return null;
    }

    @Override
    public Coordinates getOffset(){
        Entity player = this.getPlayer();
        int offset_X = 0;
        int offset_y = 0;


        int width = Resolution.SCREEN_WIDTH + this.SPACE_MOD1;
        int height = Resolution.SCREEN_HEIGHT  + this.SPACE_MOD1;

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

    private boolean isGamePaused(){
         return colorSwitchGui.isVisible();
    }

    @Override
    public List<JComponent> getJComponents() {
        List<JComponent> components = new ArrayList<>();
        components.add(colorSwitchGui);
        return components;

    }
}
