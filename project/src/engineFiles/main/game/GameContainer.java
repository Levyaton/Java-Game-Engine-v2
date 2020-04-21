package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GameGUIArea;
import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.FolderOP;
import engineFiles.ui.Player;
import engineFiles.ui.Settings;
import engineFiles.ui.Sprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class GameContainer{

    private Thread thread;
    private GameLoop loop;


    private GameGUIFrame frame;

    public GameContainer(GameGUIFrame frame){
        this.frame = frame;


    }

    public void start(){
        new Thread(new GameLoop(frame)).start();
    }

    public void stop(){
        EngineStats.running = false;
    }




    public static void main(String[] args) {
        String spritePathDir = "project/src/gameFiles/models/sprites/static/";
        // BufferedImage test = null;
        String playerPath = spritePathDir+"other/redSquare.png";
        String backgroundPath = spritePathDir+"backgrounds/iceBlue.png";
        OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());
        ArrayList<Sprite> sprites = FolderOP.getSprites(spritePathDir);
        Sprite background = new Sprite(backgroundPath);
        background.setCurrentHeight(Toolkit.getDefaultToolkit().getScreenSize().height);
        background.setCurrentWidth(Toolkit.getDefaultToolkit().getScreenSize().width);
        background.getCoord().setZ(-100);
        sprites.add(new Sprite(backgroundPath));
        GameGUIArea area = new GameGUIArea(sprites, player);
        GameGUIFrame frame = new GameGUIFrame(area);
        GameContainer gc = new GameContainer(frame);
        gc.start();
    }


}
