package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.ui.Coordinates;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

// A custom JPanel inherited by all the different panels used in the game engine. It contains the necessary methods that the JFrame needs
// to update itself correctly
public abstract class GamePanel extends JPanel {
    private static final Logger LOG = Logger.getLogger(GamePanel.class.getName());
    protected String panelName;
    protected Area area;
    protected List<Entity> entities;
    protected Window window;

    protected List<JComponent> jComponents;

    /**
     * @param area
     * @param entities
     * @param panelName
     * @param window
     * 
     */
    public GamePanel(Area area, List<Entity> entities, String panelName, Window window) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        this.panelName = panelName;
        this.entities = entities;
        this.area = area;
        this.window = window;
        LOG.config("GamePanel Initialized");
    }

    /**
     * @param panelName
     * @param window
     * 
     */
    public GamePanel(String panelName, Window window) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        this.window = window;
        LOG.config("GameOverPanel Initialized");
    }

    /**
     * @param getArea(
     * @return Image
     */
    public abstract Image getRenderGraphics();

    /**
     * @param getArea(
     */
    public abstract void update();

    /**
     * @return Area
     */
    public Area getArea() {
        return area;
    }

    /**
     * @return List<Entity>
     */
    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * @return String
     */
    public String getPanelName() {
        return panelName;
    }

    /**
     * @return Coordinates
     */
    public Coordinates getOffset() {
        return new Coordinates(0, 0, 0, 0);
    }

    /**
     * @return List<JComponent>
     */
    public List<JComponent> getJComponents() {
        return this.jComponents;
    }

    /**
     * @param e
     */
    public void mousePressed(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseExited(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseMoved(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseDragged(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * @param e
     */
    public void keyTyped(KeyEvent e) {
    }

    /**
     * @param e
     */
    public void keyPressed(KeyEvent e) {
    }

    /**
     * @param e
     */
    public void keyReleased(KeyEvent e) {
    }
}
