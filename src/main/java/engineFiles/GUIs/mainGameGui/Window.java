package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.WorldGenModel;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.ui.Resolution;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//Custom JFrame that contains the game panels and controls the displaying of information
public class Window extends JFrame implements KeyListener, MouseListener, MouseMotionListener {
    int lastDrawX;
    int lastDrawY;
    private GameCanvas canvas;
    private PanelManager panelManager;

    public Window() {
        setTitle("zelda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBackground(new Color(53, 66, 74));
        canvas = new GameCanvas();
        add(canvas);
        pack();
        setLocationRelativeTo(null);
    }

    public Window(WorldGenModel model) {
        this();
        panelManager = new PanelManager(model, this);
        add(panelManager.getCurrent());
        addMouseListener(this);
        addKeyListener(this);
        addMouseMotionListener(this);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * @param name
     */
    public void setPanel(String name) {
        panelManager.setCurrent(name);
        remove(getContentPane());
        add(panelManager.getCurrent());
    }

    /**
     * @param player
     * @param opponent
     */
    public void initBattle(OverworldPlayer player, Entity opponent) {
        panelManager.initBattle(player, opponent);
        remove(getContentPane());
        add(panelManager.getCurrent());
    }

    /**
     * @return PanelManager
     */
    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void redraw() {
        getPanelManager().getCurrent().update();
        canvas.repaint();
    }

    /**
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        panelManager.getCurrent().keyTyped(e);
    }

    /**
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        panelManager.getCurrent().keyPressed(e);
    }

    /**
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        panelManager.getCurrent().keyReleased(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        panelManager.getCurrent().mouseClicked(e);
    }

    /**
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        panelManager.getCurrent().mousePressed(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        panelManager.getCurrent().mouseReleased(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        panelManager.getCurrent().mouseEntered(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {
        panelManager.getCurrent().mouseExited(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        panelManager.getCurrent().mouseDragged(e);
    }

    /**
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        panelManager.getCurrent().mouseMoved(e);
    }

    private class GameCanvas extends JPanel {

        GameCanvas() {
            setDoubleBuffered(true);
            Dimension dim = new Dimension(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
            setPreferredSize(dim);
            setMaximumSize(dim);
            setMinimumSize(dim);
            setLayout(null);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            GamePanel currentPanel = getPanelManager().getCurrent();
            g.drawImage(currentPanel.getRenderGraphics(), 0, 0, this);
            g.dispose();
        }
    }
}
