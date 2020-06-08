package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.WorldGen;

import java.io.*;
import java.util.logging.Logger;

//Class containing the game launching methods
public class GameContainer {

    private static final Logger LOG = Logger.getLogger(GameContainer.class.getName());

    private Thread thread;
    private GameLoop loop;

    private Window frame;

    public GameContainer(Window frame) {
        this.frame = frame;
        start();
        LOG.config("GameContainer Initialized");
    }

    /**
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        File log = new File("src/main/java/Log.txt");
        try {
            log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintStream stream = new PrintStream(new BufferedOutputStream(new FileOutputStream(log)), true);
        System.setOut(stream);

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