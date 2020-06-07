package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.ui.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

//A custom JPanel inherited by all the different panels used in the game engine. It contains the necessary methods that the JFrame needs
//to update itself correctly
public abstract class GamePanel extends JPanel {

    protected String panelName;
    protected Area area;
    protected List<Entity> entities;
    protected Window window;

    protected List<JComponent> jComponents;

    public GamePanel(Area area, List<Entity> entities, String panelName, Window window) {
        this.panelName = panelName;
        this.entities = entities;
        this.area = area;
        this.window = window;
    }

    public GamePanel(String panelName, Window window) {
        this.window = window;
    }
    // ADD CONSTRUCTORS IF NEEDED

    public abstract Image getRenderGraphics();

    public abstract void update();


    public Area getArea() {
        return area;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public String getPanelName() {
        return panelName;
    }

    public Coordinates getOffset() {
        return new Coordinates(0, 0, 0, 0);
    }

    public List<JComponent> getJComponents() {
        return this.jComponents;
    }



    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseEntered(MouseEvent e) {

    }


    public void mouseExited(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e){

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public  void keyTyped(KeyEvent e){}


    public  void keyPressed(KeyEvent e){}


    public  void keyReleased(KeyEvent e){}


}
