package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GameGUIArea;
import engineFiles.GUIs.mainGameGui.GameGUIFrame;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.FolderOP;
import engineFiles.ui.Player;
import engineFiles.ui.Settings;
import engineFiles.ui.Sprite;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;

public class GameContainer implements KeyListener, Runnable {

    private Thread thread;
    private Settings settings = new Settings();


    private GameGUIFrame frame;

    public GameContainer(GameGUIFrame frame){
        this.frame = frame;
        this.frame.getGameGUIArea().addKeyListener(this);


    }

    public void start(){

        thread = new Thread(this);
        thread.run();
    }

    public void stop(){
        EngineStats.running = false;
    }

    public void run(){

        EngineStats.running = true;
        while (EngineStats.running){

            //System.out.println("hey");
            //Catches up on the missed updates, however many times is needed

            EngineStats.catchUp(this::updateGame);

            if(EngineStats.render){
                frame.getGameGUIArea().doUpdate();
                EngineStats.frames++;
            }
            else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }
        dispose();
    }

    private void dispose(){

    }


    private Void updateGame(Void param){
        //TO-DO
        return null;
    }

    public static void main(String[] args) {
        String spritePathDir = "project/src/gameFiles/models/sprites/static/";
        // BufferedImage test = null;
        String playerPath = spritePathDir+"other/redSquare.png";
        String backgroundPath = spritePathDir+"backgrounds/iceBlue.png";
        OverworldPlayer player = new OverworldPlayer(new File(playerPath), new Player());

        GameGUIArea area = new GameGUIArea(FolderOP.getSprites(spritePathDir), player, new Sprite(backgroundPath));
        GameGUIFrame frame = new GameGUIFrame(area);
        GameContainer gc = new GameContainer(frame);
        gc.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<Integer> up = Settings.controlls.getUp();
        ArrayList<Integer> down = Settings.controlls.getDown();
        ArrayList<Integer> left = Settings.controlls.getLeft();
        ArrayList<Integer> right = Settings.controlls.getRight();
        if(up.contains(e.getKeyCode())){
            frame.getGameGUIArea().getPlayer().getCoord().moveUp();
        }
        else if(down.contains(e.getKeyCode())){
            frame.getGameGUIArea().getPlayer().getCoord().moveDown();
        }
        else if(left.contains(e.getKeyCode())){
            frame.getGameGUIArea().getPlayer().getCoord().moveLeft();
        }
        else if(right.contains(e.getKeyCode())){
            frame.getGameGUIArea().getPlayer().getCoord().moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
