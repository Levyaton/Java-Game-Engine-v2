package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.WorldGen;
import java.io.*;
import java.util.logging.*;

//Class containing the game launching methods
public class GameContainer {

    private static final Logger LOG = Logger.getLogger(GameContainer.class.getName());

    private Thread thread;
    private GameLoop loop;

    private Window frame;

    /**
     * @param frame
     * 
     */
    public GameContainer(Window frame) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        this.frame = frame;
        start();
        LOG.config("GameContainer Initialized");
    }

    /**
     * @param args
     * @throws FileNotFoundException
     * 
     *                               Loads the world data from the json save file
     *                               and generates the world
     */
    public static void main(String[] args) throws FileNotFoundException {
        // LOG.setUseParentHandlers(false);
        // Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
        // @Override
        // public void publish(LogRecord record) {
        // super.publish(record);
        // flush();
        // }
        // };
        // LOG.addHandler(stdout);
        LOG.info("Game Started");
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

    /**
     * Starting the thread for gameloop
     */
    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    /**
     * setting running to false
     */
    public void stop() {
        EngineStats.running = false;
    }
}