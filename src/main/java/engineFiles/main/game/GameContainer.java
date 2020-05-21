package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GamePanel;
import engineFiles.GUIs.mainGameGui.OverworldPanel;
import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.Entities.Entity;
import engineFiles.main.models.Entities.HomingEntity;
import engineFiles.main.models.MovementAnimation;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Area;
import engineFiles.ui.Player;
import engineFiles.ui.Resolution;
import engineFiles.ui.TileMapClasses.TileMap;
import engineFiles.ui.charecterSpriteSheetClasses.SpriteSheetParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameContainer {

    private Thread thread;
    private GameLoop loop;


    private Window frame;

    public GameContainer(Window frame) {
        this.frame = frame;
        start();

    }

    public static void main(String[] args) {
       // String areaDir = "src/main/java/resources/gameFiles/models/objects/areas/";
        // BufferedImage test = null;
        String playerPath = "src/main/java/resources/gameFiles/models/sprites/static/other/redSquare.png";

        //COLORING
        String inputDir = "src/main/java/resources/gameFiles/models/sprites/tilesets/basictiles.png";
        String outputDir = "src/main/java/resources/playerGameFiles/sprites/tilesets/basictiles.png";
        String characterSpriteSheet = "src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png";

        File sourceImage = new File(inputDir);
        HashMap<Integer, MovementAnimation> animations;


            // cm.switchColors(member, new File(outputDir + member.getName()));
            int rowCount = 4;
            int columnCount = 3;
            int spriteWidth = 16;
            int spriteHeight = 16;

            try {

                BufferedImage sheet = ImageIO.read(new File(characterSpriteSheet));
                animations = SpriteSheetParser.parse(sheet, rowCount, columnCount,  spriteWidth, spriteHeight);

                MovementAnimation playerAnimation = animations.get(0);
                OverworldPlayer player = new OverworldPlayer(playerAnimation, new File(playerPath), new Player(), 5);
               // player.setImg(Scalr.resize(player.getImg(), 4));

                player.getCoord().setX(60);
                player.getCoord().setY(5);
                player.getCoord().setZ(500);

                int range = Resolution.SCREEN_WIDTH/2;
                HomingEntity enemy  = new HomingEntity(playerAnimation, new File(playerPath), player, range, 7);
                enemy.getCoord().setX(160);
                enemy.getCoord().setY(1005);
                enemy.getCoord().setZ(500);
                try {
                    TileMap tm = new TileMap("src/main/java/resources/gameFiles/models/objects/areas/TilesetAreaTest.json");
                    Area a = tm.getArea();
                    List<Entity> entities = new ArrayList<>();
                    entities.add(player);
                    entities.add(enemy);
                    GamePanel gamePanel = new OverworldPanel(a, entities, "overworld");
                    PanelManager.panels.put("overworld", gamePanel);
                    Window frame = new Window("overworld", gamePanel);
                    frame.resetActivePanel();
                    GameContainer gc = new GameContainer(frame);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

    }







    public void start() {
        new Thread(new GameLoop(frame)).start();
    }

    public void stop() {
        EngineStats.running = false;
    }


}