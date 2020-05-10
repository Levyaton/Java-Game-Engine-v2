package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame {
    int lastDrawX;
    int lastDrawY;
    private BufferStrategy bs;
    private String activePanelKey;
    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setIgnoreRepaint(true);
        createBufferStrategy(2);
        bs = getBufferStrategy();
    }

    public Window(String panelKey, GamePanel activePanel) {
        this();
        PanelManager.panels.put(panelKey,activePanel);
        PanelManager.current = panelKey;
        resetActivePanel();
    }


    public Window(String key){
        PanelManager.current = key;
        resetActivePanel();
    }


    private void confirmActivePanel(){
        if(!this.activePanelKey.equals(PanelManager.current)){
            resetActivePanel();
        }
        if(!this.getActiveGamePanel().hasFocus()){
            this.getActiveGamePanel().requestFocus();
        }
    }
    public void resetActivePanel() {
        try{
            this.remove(PanelManager.panels.get(activePanelKey));
        }
        catch (Exception ignored){}
        this.activePanelKey = PanelManager.current;
        GamePanel activePanel = PanelManager.panels.get(activePanelKey);
        this.add(activePanel);
        this.pack();
        activePanel.requestFocus();
    }

    public GamePanel getActiveGamePanel() {
        return PanelManager.panels.get(activePanelKey);
    }



    public void redraw() {
        //System.out.println(getActiveGamePanel().getPanelName() + " is the active panel, does it have focuse? " + getActiveGamePanel().hasFocus());
        confirmActivePanel();
       // super.paintComponents(bs.getDrawGraphics());
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();
        //super.paintComponents(g2d);
//        g2d.clearRect(0,0,Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
//        for (Sprite s : getActiveGamePanel().getSprites()) {
//            g2d.drawImage(s.getImg(), 0, 0, s.getCurrentWidth(),s.getCurrentHeight(),this);
//        }
        g2d.setColor(Color.BLUE);
        g2d.drawRect(0,0, 48, 48);

       // super.paintComponents(g2d);
        g2d.dispose();

        bs.show();
    }

}
