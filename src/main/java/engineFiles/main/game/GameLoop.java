package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the game loop
public class GameLoop implements Runnable {
    private static final Logger LOG = Logger.getLogger(GameLoop.class.getName());
    private Window frame;

    public GameLoop(Window frame) {
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
        LOG.config("GameLoop Initialized");
    }

    @Override
    public void run() {
        LOG.info("gameloop starting");
        EngineStats.running = true;
        while (EngineStats.running) {

            // Catches up on the missed updates, however many times is needed
            EngineStats.catchUp();

            if (EngineStats.render) {
                frame.redraw();
                EngineStats.frames++;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        dispose();
    }

    private void dispose() {

    }

}
