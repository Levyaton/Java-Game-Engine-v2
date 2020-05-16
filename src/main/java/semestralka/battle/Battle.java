package semestralka.battle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;
import semestralka.graphics.Resources;
import semestralka.models.creatures.Creature;
import semestralka.utils.KeyManager;
import semestralka.view.GamePanel;
import semestralka.world.World;

public class Battle {

  private boolean inBattle = false;
  private World world;

  private Stack<Component> componentStack;
  private Controller controller;

  private BattleManager battleManager;

  public Creature player, opponent;

  public Battle(World world) {
    this.world = world;
  }

  public void startBattle(Creature opponnent) {
    if (inBattle) {
      return;
    }
    this.opponent = opponnent;

    player = world.getPlayer();
    controller = new Controller(this);
    battleManager = new BattleManager(this);
    componentStack = new Stack<Component>();

    componentStack.push(controller);
    componentStack.push(new Dialog("A wild " + opponnent.getName() + " has appeard", () -> {
      componentStack.pop();
    }));

    inBattle = true;
  }

  public boolean isInBattle() {
    return inBattle;
  }

  public void input(KeyManager keyManager) {
    componentStack.peek().input(keyManager);
  }

  public void update() {
    componentStack.peek().update();
    if (opponent.getCurHealth() != 0 && player.getCurHealth() != 0) {
      battleManager.update();
    }
  }

  public void render(Graphics g) {
    drawLayout(g);
    drawPlayer(g);
    drawOpponent(g);
    drawHealthBars(g);
    componentStack.peek().render(g);
  }

  public void drawLayout(Graphics g) {
    // setting up background and components
    g.drawImage(Resources.battleBackground, 0, 0, GamePanel.width, GamePanel.height, null);
    // if (opponent.getCurHealth() != 0 && player.getCurHealth() != 0) {
    // g.drawImage(Resources.battleOptions, 10, 200, 178, 144, null);
    // controller.render(g);
    // }
    // setting up font
    g.setFont(Resources.zeldaFontSmall);
    // player
    g.drawImage(Resources.battleStats, 10, 120, 178, 48, null);
    // opponent
    g.drawImage(Resources.battleStats, 500, 20, 178, 48, null);
  }

  public void drawPlayer(Graphics g) {
    g.drawString(player.getName(), 20, 145);
    g.drawImage(player.getAnimation().getStandFrame(3), 250, 260, 120, 120, null);
  }

  public void drawOpponent(Graphics g) {
    g.drawString(opponent.getName(), 510, 45);
    g.drawImage(opponent.getAnimation().getStandFrame(0), 350, 100, 70, 70, null);
  }

  public void drawHealthBars(Graphics g) {
    // health width: 150
    g.setColor(Color.GREEN);
    if (player.getCurHealth() > 0) {
      g.fillRect(20, 145, 150 - ((player.getHealth() - player.getCurHealth()) * (150 / player.getHealth())), 10);
    }
    if (opponent.getCurHealth() > 0) {
      g.fillRect(510, 50, 150 - ((opponent.getHealth() - opponent.getCurHealth()) * (150 / opponent.getHealth())), 10);
    }
  }

  public Controller getController() {
    return controller;
  }

  public void pushDialog(String text) {
    componentStack.push(new Dialog(text, () -> {
      componentStack.pop();
    }));
  }
}