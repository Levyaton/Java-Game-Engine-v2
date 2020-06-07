package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.WorldGenModel;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;


//Class that helps manage the various GamePanel children that are being used
public class PanelManager {

    private GamePanel current;
    private MenuPanel menu;
    private OverworldPanel overworld;
    private BattlePanel battle;
    private WorldGenModel model;
    private Window window;

    public PanelManager(WorldGenModel model, Window window) {
        menu = new MenuPanel("menu", window);
        battle = new BattlePanel("battle", window);
        overworld = new OverworldPanel("overworld", window, model);
        current = menu;
    }

    public GamePanel getCurrent() {
        return current;
    }

    public void setCurrent(String name) {
        switch (name) {
            case "menu":
                current = menu;
                break;
            case "overworld":
                current = overworld;
                break;
        }
    }

    public void initBattle(OverworldPlayer player, Entity opponent) {
        battle.setOpponents(player, opponent);
        current = battle;
    }

    public OverworldPanel getOverWorldPanel() {
        return overworld;
    }
}