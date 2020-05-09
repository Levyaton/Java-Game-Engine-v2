package engineFiles.main.game;
import engineFiles.GUIs.mainGameGui.GamePanel;
import engineFiles.GUIs.mainGameGui.OverworldPanel;
import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.*;
import engineFiles.ui.TileMapClasses.TileMap;
import org.imgscalr.Scalr;

import java.io.File;
import java.io.FileNotFoundException;

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
        player.setImg(Scalr.resize(player.getImg(), 4));
        player.getCoord().setX(60);
        player.getCoord().setY(5);
        player.getCoord().setZ(500);
        try {
            TileMap tm = new TileMap("project/src/gameFiles/models/objects/areas/TilesetAreaTest.json");
            Area a = tm.getArea();

            GamePanel gamePanel = new OverworldPanel(a, player, "overworld");
            PanelManager.panels.put("overworld", gamePanel);
            Window frame = new Window("overworld",gamePanel);
            frame.resetActivePanel();
            GameContainer gc = new GameContainer(frame);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    public void stop() {
        EngineStats.running = false;
    }


}
