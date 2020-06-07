package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.ui.Coordinates;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

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
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public GamePanel(String panelName, Window window) {
        this.window = window;
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public abstract Image getRenderGraphics();

    public abstract void update();

    protected Area getArea() {
        return area;
    }

    protected List<Entity> getEntities() {
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
}
