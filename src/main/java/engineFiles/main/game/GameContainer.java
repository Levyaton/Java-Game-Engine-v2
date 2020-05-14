package engineFiles.main.game;

import engineFiles.GUIs.mainGameGui.GamePanel;
import engineFiles.GUIs.mainGameGui.OverworldPanel;
import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.colorer.program.Colorizer_2;
import engineFiles.main.models.EngineStats;
import engineFiles.main.models.Entities.Entity;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Area;
import engineFiles.ui.Player;
import engineFiles.ui.TileMapClasses.TileMap;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
        File sourceImage = new File(inputDir);

        try {

            // cm.switchColors(member, new File(outputDir + member.getName()));
            BufferedImage recolor = Colorizer_2.getCompleteReColor(ImageIO.read(sourceImage));
            ImageIO.write(recolor, "png", new File(outputDir));

            List<BufferedImage> down = new ArrayList<>();
            List<BufferedImage> up = new ArrayList<>();
            List<BufferedImage> left = new ArrayList<>();
            List<BufferedImage> right = new ArrayList<>();
            BufferedImage b = ImageIO.read(new File(playerPath));

            down.add(b);
            up.add(b);
            left.add(b);
            right.add(b);

            OverworldPlayer player = new OverworldPlayer(down, up, left, right, new File(playerPath), new Player());
            player.setImg(Scalr.resize(player.getImg(), 4));
            player.getCoord().setX(60);
            player.getCoord().setY(5);
            player.getCoord().setZ(500);
            try {
                TileMap tm = new TileMap("src/main/java/resources/gameFiles/models/objects/areas/TilesetAreaTest.json");
                Area a = tm.getArea();
                List<Entity> entities = new ArrayList<>();
                entities.add(player);
                GamePanel gamePanel = new OverworldPanel(a, entities, "overworld");
                PanelManager.panels.put("overworld", gamePanel);
                Window frame = new Window("overworld", gamePanel);
                frame.resetActivePanel();
                GameContainer gc = new GameContainer(frame);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.out.println(inputDir);
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