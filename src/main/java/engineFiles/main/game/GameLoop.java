package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;

//Class containing the game loop
public class GameLoop implements Runnable {

    private Window frame;

    public GameLoop(Window frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        System.out.println("gameloop starting");
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
