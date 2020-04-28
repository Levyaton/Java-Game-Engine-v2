package semestralka.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import semestralka.graphics.Resources;
import semestralka.screen.ScreenManager;
import semestralka.utils.KeyManager;
import semestralka.utils.MouseManager;
import semestralka.view.GamePanel;

public class GameLoop implements Runnable {

  private BufferStrategy bs;
  private GamePanel gamePanel;

  private boolean running = true;
  private Thread thread;
  private int curFps = 0;

  private ScreenManager screenManager;
  private KeyManager keyManager;
  private MouseManager mouseManager;

  public GameLoop(BufferStrategy bs, GamePanel gamePanel) {
    this.bs = bs;
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
      while (delta >= 1) {
        Graphics g = bs.getDrawGraphics();

        drawStats(g);
        screenManager.init(g, keyManager, mouseManager);

        g.dispose();
        bs.show();

        ticks++;
        delta--;
      }

      try {
        Thread.sleep(2);
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
    screenManager = new ScreenManager();
    keyManager = new KeyManager(gamePanel, screenManager);
    mouseManager = new MouseManager(gamePanel, screenManager);

    Resources.load();
  }

  public void drawStats(Graphics g) {
    g.clearRect(0, 0, GamePanel.width, GamePanel.height);
    g.setColor(Color.BLACK);
    g.fillRect(5, 5, 50, 18);
    g.setColor(Color.WHITE);
    g.setFont(Resources.arialFontBold);
    g.drawString("FPS: " + curFps, 10, 18);
  }
}