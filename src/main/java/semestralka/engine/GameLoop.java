package semestralka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import semestralka.graphics.Resources;
import semestralka.screen.ScreenManager;
import semestralka.view.GamePanel;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;

public class GameLoop implements Runnable {

  private BufferStrategy bs;
  private GamePanel gamePanel;

  private boolean running = true;
  private Thread thread;
  private int curFps = 0;

  private MouseManager mouseManager;
  private KeyManager keyManager;
  private ScreenManager screenManager;

  public GameLoop(GamePanel gamePanel) {
    this.gamePanel = gamePanel;

    thread = new Thread(this, "Game loop");
    thread.start();
  }

  public synchronized void stop() {
    if (!running)
      return;
    running = false;

    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    init();

    double fps = 60D;
    double nsPerTick = 1000000000D / fps;
    long lastTime = System.nanoTime();
    double delta = 0;
    long timer = 0;
    int ticks = 0;

    while (running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / nsPerTick;
      timer += (now - lastTime);
      lastTime = now;

      if (delta >= 1) {
        bs = gamePanel.getBufferStrategy();
        Graphics g = bs.getDrawGraphics();
        g.clearRect(0, 0, GamePanel.width, GamePanel.height);

        screenManager.init(g);
        // drawStats(g);

        g.dispose();
        bs.show();

        ticks++;
        delta--;
      }

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (timer >= 1000000000) {
        curFps = ticks;
        ticks = 0;
        timer = 0;
      }
    }

    stop();
  }

  public void init() {
    new Resources().load();
    mouseManager = new MouseManager(gamePanel);
    keyManager = new KeyManager(gamePanel);
    screenManager = new ScreenManager(keyManager, mouseManager);
  }

  public void drawStats(Graphics g) {
    g.setColor(Color.BLACK);
    g.fillRect(5, 5, 50, 18);
    g.setColor(Color.WHITE);
    g.setFont(Resources.arialFontBold);
    g.drawString("FPS: " + curFps, 10, 18);
  }
}