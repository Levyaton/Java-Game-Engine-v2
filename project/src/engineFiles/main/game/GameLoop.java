package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.models.EngineStats;
import engineFiles.ui.Settings;

import java.util.ArrayList;

public class GameLoop implements Runnable {


    private ArrayList<Integer> up = Settings.controlls.getUp();
    private ArrayList<Integer> down = Settings.controlls.getDown();
    private ArrayList<Integer> left = Settings.controlls.getLeft();
    private ArrayList<Integer> right = Settings.controlls.getRight();


    private GameGUIFrame frame;

    public GameLoop(GameGUIFrame frame) {
        this.frame = frame;
    }

    private Void updateGame(Void param) {
        //Pressing two keys results in faster movement, fix later
        if (KeyMap.isPressed(right)) {
            frame.getGameGUIArea().moveRight();
        }
        if (KeyMap.isPressed(left)) {
            frame.getGameGUIArea().moveLeft();
        }
        if (KeyMap.isPressed(down)) {
            frame.getGameGUIArea().moveDown();
        }
        if (KeyMap.isPressed(up)) {
            frame.getGameGUIArea().moveUp();
        }
        return null;
    }

    @Override
    public void run() {


        EngineStats.running = true;
        while (EngineStats.running) {

            //System.out.println("hey");
            //Catches up on the missed updates, however many times is needed
            EngineStats.catchUp(this::updateGame);

            if (EngineStats.render) {
                frame.redraw();
                EngineStats.frames++;
            } else {
                //System.out.println("SLEEP!!!!");
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
