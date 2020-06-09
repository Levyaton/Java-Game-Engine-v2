package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.WorldGenModel;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

// Class that helps manage the various GamePanel children that are being used
public class PanelManager {
    private static final Logger LOG = Logger.getLogger(PanelManager.class.getName());
    private GamePanel current;
    private MenuPanel menu;
    private OverworldPanel overworld;
    private BattlePanel battle;
    private GameOverPanel gameover;
    private WorldGenModel model;

    /**
     * @param model
     * @param window
     * 
     *               Initialzes the panels and sets the current as menu
     */
    public PanelManager(WorldGenModel model, Window window) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        menu = new MenuPanel("menu", window);
        battle = new BattlePanel("battle", window);
        gameover = new GameOverPanel("gameover", window);
        overworld = new OverworldPanel("overworld", window, model);
        current = menu;
        LOG.config("PanelManager Initialized");
    }

    /**
     * @return GamePanel
     * 
     *         Returns the current panel
     */
    public GamePanel getCurrent() {
        return current;
    }

    /**
     * @param name
     * 
     *             Sets the current panel based on the name of the panel
     */
    public void setCurrent(String name) {
        switch (name) {
            case "menu":
                current = menu;
                break;
            case "overworld":
                current = overworld;
                break;
            case "gameover":
                current = gameover;
                break;
        }

        LOG.info("Set panel " + name + " as Current");
    }

    /**
     * @param player
     * @param opponent
     * 
     *                 Starts the battle by setting the oponnent and player and
     *                 changing the current panel
     */
    public void initBattle(OverworldPlayer player, Entity opponent) {
        battle.setOpponents(player, opponent);
        current = battle;
        LOG.info("Call to battle");
    }

    /**
     * @return OverworldPanel
     */
    public OverworldPanel getOverWorldPanel() {
        return overworld;
    }
}