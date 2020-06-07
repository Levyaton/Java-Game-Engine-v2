package engineFiles.GUIs.mainGameGui;

import engineFiles.main.battle.BattleManager;
import engineFiles.main.battle.Component;
import engineFiles.main.battle.Controller;
import engineFiles.main.battle.Dialog;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Battle.battleGUI;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.ui.Resolution;
import engineFiles.ui.fonts.FontLibrary;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Stack;

//Class that is used to get the information needed to display for battles
public class BattlePanel extends GamePanel {

  private boolean inBattle = false;

  private Stack<Component> componentStack;
  private BattleManager battleManager;
  private Controller controller;

  public OverworldPlayer player;
  public Entity opponent;

  public BattlePanel(String panelName, Window window) {
    super(panelName, window);
    battleGUI.loadResources();

    battleManager = new BattleManager(this);
    componentStack = new Stack<Component>();
  }

  public void setOpponents(OverworldPlayer player, Entity opponent) {
    this.player = player;
    this.opponent = opponent;
    controller = new Controller(this);
    componentStack.push(controller);
    componentStack.push(new Dialog("A wild " + opponent.getCategoryName() + " has appeard", () -> {
      componentStack.pop();
    }));

    inBattle = true;
  }

  public boolean isInBattle() {
    return inBattle;
  }

  @Override
  public void keyPressed(KeyEvent e) {
    KeyMap.setPressed(true);
    KeyMap.setKey(e.getKeyCode(), false);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    KeyMap.setPressed(false);
    KeyMap.setKey(e.getKeyCode(), true);
  }

  @Override
  public Image getRenderGraphics() {
    Image img = createImage(Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT);
    Graphics g = img.getGraphics();

    drawGUI(g);
    drawPlayer(g);
    drawOpponent(g);
    drawHealthBars(g);

    componentStack.peek().input();
    componentStack.peek().render(g);

    return img;
  }

  @Override
  public void update() {
    if (!gameEnded()) {
      battleManager.update();
    } else if (inBattle) {
      player.setBattleDamage(0);
      opponent.setBattleDamage(0);
      if (inBattle && opponent.getCurHealth() < 1) {
        componentStack.push(new Dialog("Leaving battle...", () -> {
          window.getPanelManager().getOverWorldPanel().removeEntity(opponent);
          window.setPanel("overworld");
        }));
        pushDialog("VICTORY!");
        pushDialog(opponent.getCategoryName() + " was defeated");
      } else if (player.getCurHealth() < 1) {
        componentStack.push(new Dialog("Leaving battle...", () -> {
          window.setPanel("gameover");
        }));
        pushDialog("DEFEAT!");
        pushDialog(player.getCategoryName() + " was defeated");
      }
      inBattle = false;
    }
  }

  public void drawGUI(Graphics g) {
    // setting up background and components
    g.drawImage(battleGUI.battleBackground, 0, 0, Resolution.SCREEN_WIDTH, Resolution.SCREEN_HEIGHT, null);
    // setting up font
    g.setFont(FontLibrary.zeldaFontSmall);
    // player
    g.drawImage(battleGUI.battleStats, 20, 140, 198, 68, null);
    // opponent
    g.drawImage(battleGUI.battleStats, 580, 40, 198, 68, null);
  }

  public void drawPlayer(Graphics g) {
    g.drawString(player.getCategoryName(), 35, 175);
    g.drawImage(player.getAnimation().getUp().get(1), 290, 320, 150, 150, null);
  }

  public void drawOpponent(Graphics g) {
    g.drawString(opponent.getCategoryName(), 595, 75);
    g.drawImage(opponent.getAnimation().getDown().get(1), 380, 160, 90, 90, null);
  }

  public void drawHealthBars(Graphics g) {
    // health width: 160
    g.setColor(Color.GREEN);
    if (player.getCurHealth() > 0) {
      // player
      g.fillRect(35, 180, 160 - ((player.getHealth() - player.getCurHealth()) * (160 / player.getHealth())), 10);
    }
    if (opponent.getCurHealth() > 0) {
      // opponent
      g.fillRect(595, 80, 160 - ((opponent.getHealth() - opponent.getCurHealth()) * (160 / opponent.getHealth())), 10);
    }
  }

  public void pushDialog(String text) {
    componentStack.push(new Dialog(text, () -> {
      componentStack.pop();
    }));
  }

  public boolean gameEnded() {
    return player.getCurHealth() < 1 || opponent.getCurHealth() < 1;
  }

  public BattleManager getBattleManager() {
    return battleManager;
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }
}