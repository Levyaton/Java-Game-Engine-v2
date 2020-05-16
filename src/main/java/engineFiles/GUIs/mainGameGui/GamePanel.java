package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Entities.Entity;
import engineFiles.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Set;

public abstract class GamePanel extends JPanel implements KeyListener,GamePanelInterface {

    protected  String panelName;
    protected Area area;
    protected List<Entity> entities;

    public GamePanel(Area area, List<Entity> entities, String panelName){
        this.panelName = panelName;
        this.entities = entities;
        this.area = area;
        this.addKeyListener(this);

    }

    public GamePanel(){
        this.addKeyListener(this);
    }
    //ADD CONSTRUCTORS IF NEEDED

    @Override
    public SpriteCollection getSprites() {
        return null;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public Void updateMovement(Void param){
        return null;
    }

    protected Area getArea(){
        return area;
    }

    protected List<Entity> getEntities(){
        return entities;
    }

    public String getPanelName() {
        return panelName;
    }

    public Coordinates getOffset(){
        return new Coordinates(0,0,0,0);
    }
}

