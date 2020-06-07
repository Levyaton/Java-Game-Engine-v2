package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.WorldGen;

import java.io.*;

public class GameContainer {

    private Thread thread;
    private GameLoop loop;

    private Window frame;

    public GameContainer(Window frame) {
        this.frame = frame;
        start();
    }

    public static void main(String[] args) throws FileNotFoundException {
       /*
        File log = new File("src/main/java/Log.txt");
       try {
           log.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
         }
        PrintStream stream = new PrintStream(new BufferedOutputStream(new
       FileOutputStream(log)), true);
       System.setOut(stream);

        */
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