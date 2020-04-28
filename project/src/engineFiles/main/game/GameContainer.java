package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GameGUIArea;
import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class GameContainer {

    private Thread thread;
    private GameLoop loop;


    private GameGUIFrame frame;

    public GameContainer(GameGUIFrame frame) {
        this.frame = frame;


    }

    public static void main(String[] args) {
        String areaDir = "project/src/gameFiles/models/objects/areas/";
        // BufferedImage test = null;
        String playerPath =  "project/src/gameFiles/models/sprites/static/other/redSquare.png";

        OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());
        player.getCoord().setX(500);
        player.getCoord().setY(500);

        Area a = new Area("project/src/gameFiles/models/objects/areas/Test.json");

        int xCoord = 0;
        int yCoord = 300;
        boolean solid = true;



        GameGUIArea area = new GameGUIArea(a, player);
        GameGUIFrame frame = new GameGUIFrame(area);
        GameContainer gc = new GameContainer(frame);
        gc.start();
    }

    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    public void stop() {
        EngineStats.running = false;
    }


}
