package engineFiles.main.models;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//A class containing the engine stats, and methods that involve them
public class EngineStats {
    private static final Logger LOG = Logger.getLogger(EngineStats.class.getName());
    public static final double UPDATE_CAP = 1.0 / 60.0;
    public static final double LARGE_CONST = 100000000.0;
    public static boolean render = false;
    public static double currentTime;
    public static double firstTime = 0;
    public static double lastTime = System.nanoTime() / LARGE_CONST;
    public static double passedTime = 0;
    public static double frameTime = 0;
    public static double frames = 0;
    public static double fps = 0;

    public static boolean running = false;

    public static double unprocessedTime = 0;

    public EngineStats() {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        LOG.config("EngineStats Initialized");
    }

    public static void updateStats() {

    }

    public static void catchUp() {
        render = false;
        firstTime = System.nanoTime() / LARGE_CONST;
        passedTime = firstTime - lastTime;
        lastTime = firstTime;

        unprocessedTime += passedTime;
        frameTime += passedTime;

        while (unprocessedTime >= UPDATE_CAP) {

            unprocessedTime -= UPDATE_CAP;
            render = true;
            frameTime += passedTime;

            if (frameTime >= 1.0) {
                frameTime = 0;
                fps = frames;
                frames = 0;
                // System.out.println("FPS: " + fps);
            }
        }
    }
}
