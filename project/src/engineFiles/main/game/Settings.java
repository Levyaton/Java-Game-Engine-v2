package engineFiles.main.game;

import ui.Resolution;

import java.awt.*;

public class Settings {
    final int LARGE_CONSTANT = 1000000000;
    private double gameHertz;
    private double timeBetweenUpdates;
    private int maxUpdatesBeforeRender;
    private double lastUpdateTime;
    private double lastRenderTime;
    private double targetFPS;
    private double targetTimeBetweenRenders;
    private int lastSecondTime;
    private Resolution resolution;

    //INIT
    public Settings(){
        this.gameHertz = 30.0;
        this.maxUpdatesBeforeRender = 5;
        this.targetFPS = 60;
        universalSettings();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.resolution = new Resolution(screenSize.width, screenSize.height);
    }

    public Settings(double gameHertz, int maxUpdatesBeforeRender, double targetFPS, int resWidth, int resHeight){
        this.gameHertz = gameHertz;
        this.maxUpdatesBeforeRender = maxUpdatesBeforeRender;
        this.targetFPS = targetFPS;
        this.resolution = new Resolution(resWidth,resHeight);
        universalSettings();
    }
    private void universalSettings(){
        this.timeBetweenUpdates = LARGE_CONSTANT / gameHertz;
        this.lastUpdateTime = System.nanoTime();
        this.lastRenderTime = System.nanoTime();
        this.targetTimeBetweenRenders = LARGE_CONSTANT / targetFPS;
        this.lastSecondTime = (int) (lastUpdateTime / LARGE_CONSTANT);
    }

   //GETTERS

    public int getLARGE_CONSTANT() {
        return LARGE_CONSTANT;
    }

    public double getGameHertz() {
        return gameHertz;
    }

    public double getTimeBetweenUpdates() {
        return timeBetweenUpdates;
    }

    public int getMaxUpdatesBeforeRender() {
        return maxUpdatesBeforeRender;
    }

    public double getLastUpdateTime() {
        return lastUpdateTime;
    }

    public double getLastRenderTime() {
        return lastRenderTime;
    }

    public double getTargetFPS() {
        return targetFPS;
    }

    public double getTargetTimeBetweenRenders() {
        return targetTimeBetweenRenders;
    }

    public int getLastSecondTime() {
        return lastSecondTime;
    }

    //SETTERS
    public void setGameHertz(double gameHertz) {
        this.gameHertz = gameHertz;
    }

    public void setMaxUpdatesBeforeRender(int maxUpdatesBeforeRender) {
        this.maxUpdatesBeforeRender = maxUpdatesBeforeRender;
    }

    public void setTargetFPS(double targetFPS) {
        this.targetFPS = targetFPS;
    }


}
