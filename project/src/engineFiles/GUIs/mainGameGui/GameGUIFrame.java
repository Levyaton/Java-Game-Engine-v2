package engineFiles.GUIs.mainGameGui;

import javax.swing.*;

public class GameGUIFrame extends JFrame {
    int lastDrawX;
    int lastDrawY;
    private JPanel activePanel;
    public GameGUIFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public GameGUIFrame(JPanel activePanel){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setActivePanel(activePanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setVisible(true);

    }

    public void setActivePanel(JPanel activePanel){
        if(this.activePanel != null){
            this.remove(activePanel);
        }
        this.activePanel = activePanel;
        this.add(activePanel);
    }

}
