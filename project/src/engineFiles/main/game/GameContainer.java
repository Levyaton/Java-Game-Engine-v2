package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GamePanel;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.*;

import java.io.File;

public class GameContainer {

    private Thread thread;
    private GameLoop loop;


    private Window frame;

    public GameContainer(Window frame) {
        this.frame = frame;
        start();

    }

    public static void main(String[] args) {
        String areaDir = "project/src/gameFiles/models/objects/areas/";
        // BufferedImage test = null;
        String playerPath =  "project/src/gameFiles/models/sprites/static/other/redSquare.png";

        OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());
        player.getCoord().setX(500);
        player.getCoord().setY(500);

        Area a = new Area("project/src/gameFiles/models/objects/areas/Test.json");

        GamePanel area = new GamePanel(a, player);
        Window frame = new Window(area);
        GameContainer gc = new GameContainer(frame);
    }

    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    public void stop() {
        EngineStats.running = false;
    }


}
