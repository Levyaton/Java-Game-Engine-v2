package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * The GameLoops jobs is to create a consistant render enviroment set by the
 * frame rate and calling repaint for the rerendering of the GUI
 *
 */
public class GameLoop implements Runnable {
    private static final Logger LOG = Logger.getLogger(GameLoop.class.getName());
    private Window frame;

    /**
     * @param frame
     * 
     */
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

    /**
     * thread native run function renders everytime the render variable is set true.
     * The catchup function set the time for renderin by calculating the needed
     * time.
     */
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
