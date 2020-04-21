package engineFiles.GUIs.mainGameGui;

import javax.swing.*;

public class GameGUIFrame extends JFrame {
    int lastDrawX;
    int lastDrawY;
    private GameGUIArea activePanel;
    public GameGUIFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameGUIFrame(GameGUIArea activePanel){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setActivePanel(activePanel);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        //setUndecorated(true);
        //setIgnoreRepaint(true);
        setVisible(true);

    }

    public void setActivePanel(GameGUIArea activePanel){
        if(this.activePanel != null){
            this.remove(activePanel);
        }
        this.activePanel = activePanel;
        this.add(activePanel);
        activePanel.setFocusable(true);
    }

    public GameGUIArea getGameGUIArea(){
        return activePanel;
    }

}
