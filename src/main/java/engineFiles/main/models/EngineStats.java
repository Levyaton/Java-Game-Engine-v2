package engineFiles.main.models;

import java.util.function.Function;

public class EngineStats {
    public static final double UPDATE_CAP = 1.0 / 60.0;
    public static final double LARGE_CONST = 1000000000.0;
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

    }


    public static void updateStats() {

    }

    public static void catchUp(Function<Void, Void> updateGame) {
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

            updateGame.apply(null); //TO-Do: Create the function

            if (frameTime >= 1.0) {
                frameTime = 0;
                fps = frames;
                frames = 0;

                System.out.println("FPS: " + fps);
            }
        }
    }
}
