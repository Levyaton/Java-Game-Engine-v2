package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.ui.Coordinates;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

//A custom JPanel inherited by all the different panels used in the game engine. It contains the necessary methods that the JFrame needs
//to update itself correctly
public abstract class GamePanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

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
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public GamePanel(String panelName, Window window) {
        this.window = window;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    // ADD CONSTRUCTORS IF NEEDED

    public abstract Image getRenderGraphics();

    public abstract void update();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public List<JComponent> getJComponents() {
        return this.jComponents;
    }


}
