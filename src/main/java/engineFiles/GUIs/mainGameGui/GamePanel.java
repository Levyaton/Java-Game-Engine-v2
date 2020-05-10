package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Area;
import engineFiles.ui.SpriteCollection;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GamePanel extends JPanel implements KeyListener,GamePanelInterface {

    protected  String panelName;
    protected Area area;
    protected OverworldPlayer player;

    public GamePanel(Area area, OverworldPlayer player, String panelName){
        this.panelName = panelName;
        this.player = player;
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

    protected OverworldPlayer getPlayer(){
        return player;
    }

    public String getPanelName() {
        return panelName;
    }
}

