package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.WorldGenModel;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.ui.Resolution;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
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
        panelManager.getCurrent().setRequestFocusEnabled(true);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void setPanel(String name) {
        panelManager.getCurrent().setRequestFocusEnabled(false);
        panelManager.setCurrent(name);
        remove(getContentPane());
        add(panelManager.getCurrent());
        panelManager.getCurrent().setRequestFocusEnabled(true);
    }

    public void initBattle(OverworldPlayer player, Entity opponent) {
        // panelManager.initBattle(player, opponent);
        panelManager.setCurrent("menu");
        remove(getContentPane());
        add(panelManager.getCurrent());
        panelManager.getCurrent().requestFocus();
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public void redraw() {
        getPanelManager().getCurrent().update();
        canvas.repaint();
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
