package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.WorldGen;

import java.io.FileNotFoundException;

public class GameContainer {

    private Thread thread;
    private GameLoop loop;

    private Window frame;

    public GameContainer(Window frame) {
        this.frame = frame;
        start();
    }

    public static void main(String[] args) throws FileNotFoundException {
        WorldGen load = new WorldGen();
        load.generateWorld();
    }

    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    public void stop() {
        EngineStats.running = false;
    }
}