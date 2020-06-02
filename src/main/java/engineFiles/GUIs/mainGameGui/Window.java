package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Resolution;
import engineFiles.ui.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
    int lastDrawX;
    int lastDrawY;
    private BufferStrategy bs;
    private String activePanelKey;
    private JLayeredPane layers;
    private JLayeredPane overlay;
    private GameCanvas canvas;

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Dimension dim = new Dimension(800, 600);

        // setPreferredSize(dim);
        setVisible(true);
        layers = new JLayeredPane();
        layers.setLayout(new BorderLayout());
        overlay = new JLayeredPane();
        Dimension dimension = new Dimension(Settings.screenWidth, Settings.screenHeight);
        overlay.setPreferredSize(dimension);
        overlay.setBackground(Color.red);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        bs = getBufferStrategy();
        canvas = new GameCanvas();
        layers.add(overlay, 0);
        layers.add(canvas, 1);

        this.getContentPane().add(layers, BorderLayout.CENTER);
        pack();
    }

    public Window(String panelKey, GamePanel activePanel) {
        this();
        PanelManager.panels.put(panelKey, activePanel);
        PanelManager.current = panelKey;
        resetActivePanel();
    }

    public Window(String key) {
        PanelManager.current = key;
        resetActivePanel();
    }

    private void confirmActivePanel() {
        if (!this.activePanelKey.equals(PanelManager.current)) {
            resetActivePanel();
        }
        if (!this.getActiveGamePanel().hasFocus()) {
            this.getActiveGamePanel().requestFocus();
        }
    }

    public void resetActivePanel() {
        try {
            this.remove(PanelManager.panels.get(activePanelKey));
            for (JComponent component : PanelManager.panels.get(activePanelKey).getJComponents()) {
                overlay.remove(component);
            }
        } catch (Exception ignored) {
        }
        this.activePanelKey = PanelManager.current;
        GamePanel activePanel = PanelManager.panels.get(activePanelKey);
        this.add(activePanel, BorderLayout.CENTER);
        for (JComponent component : activePanel.getJComponents()) {
            overlay.add(component);
        }
        System.out.println("Number of jcomponents is " + activePanel.getJComponents().size());
        activePanel.requestFocus();

    }

    public GamePanel getActiveGamePanel() {
        return PanelManager.panels.get(activePanelKey);
    }

    public void redraw() {
        /*
         * //System.out.println(getActiveGamePanel().getPanelName() +
         * " is the active panel, does it have focuse? " +
         * getActiveGamePanel().hasFocus()); confirmActivePanel(); //
         * super.paintComponents(bs.getDrawGraphics()); Graphics2D g2d = (Graphics2D)
         * bs.getDrawGraphics(); //super.paintComponents(g2d);
         * 
         * g2d.clearRect(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.
         * getDefaultToolkit().getScreenSize().height); for (Sprite s :
         * getActiveGamePanel().getSprites()) { g2d.drawImage(s.getImg(),
         * s.getCoord().getX(), s.getCoord().getY(),
         * s.getCurrentWidth(),s.getCurrentHeight(),this); }
         * 
         * // super.paintComponents(g2d); g2d.dispose();
         * 
         * bs.show();
         */
        canvas.repaint();

    }

    private class GameCanvas extends JPanel {

        GameCanvas() {
            setDoubleBuffered(true);
            Dimension dim = new Dimension(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
            setPreferredSize(dim);

        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            GamePanel activePanel = getActiveGamePanel();
            g.drawImage(activePanel.getRenderGraphics(), 0, 0, this);

            // super.paintComponents(g2d);
            g.dispose();

        }
    }

}
