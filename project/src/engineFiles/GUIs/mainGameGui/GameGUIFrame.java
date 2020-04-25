package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameGUIFrame extends JFrame {
    int lastDrawX;
    int lastDrawY;
    private GameGUIArea activePanel;
    private BufferStrategy bs;

    public GameGUIFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameGUIFrame(GameGUIArea activePanel) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setActivePanel(activePanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        //setUndecorated(true);
        //setIgnoreRepaint(true);
        setVisible(true);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        bs = getBufferStrategy();

    }

    public void setActivePanel(GameGUIArea activePanel) {
        if (this.activePanel != null) {
            this.remove(activePanel);
        }
        this.activePanel = activePanel;
        this.add(activePanel);
        activePanel.setFocusable(true);
    }

    public GameGUIArea getGameGUIArea() {
        return activePanel;
    }

    public void redraw() {
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        for (Sprite s : activePanel.getSprites()) {
            g2d.drawImage(s.getImg(), s.getCoord().getX(), s.getCoord().getY(), this);
        }
        g2d.dispose();
        bs.show();
        revalidate();

    }

}
