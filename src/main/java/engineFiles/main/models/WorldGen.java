package engineFiles.main.models;

import engineFiles.GUIs.mainGameGui.GamePanel;
import engineFiles.GUIs.mainGameGui.OverworldPanel;
import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.colorer.program.RecolorV3;
import engineFiles.main.game.GameContainer;
import engineFiles.main.models.Sprites.Entities.*;
import engineFiles.main.models.Sprites.Items.Item;
import engineFiles.main.models.Sprites.Items.ItemSprite;
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

public class WorldGen {

    List<TileMap> tm;
    List<ColorerModel> cm;
    EntitiesModel em;


    public void generateWorld(){
        String playerPath = "src/main/java/resources/gameFiles/models/sprites/static/other/redSquare.png";

        //COLORING
        String inputDir = "src/main/java/engineFiles/colorer/testFiles/basictiles.png";
        String outputDir = "src/main/java/resources/playerGameFiles/sprites/tilesets/basictiles.png";
        File sourceImage = new File(inputDir);
        String characterSpriteSheet = "src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png";


        HashMap<Integer, MovementAnimation> animations;


        // cm.switchColors(member, new File(outputDir + member.getName()));
        int rowCount = 4;
        int columnCount = 3;
        int spriteWidth = 16;
        int spriteHeight = 16;

        try {
            BufferedImage recolor = RecolorV3.recolor(ImageIO.read(sourceImage));
            File f = new File(outputDir);
            f.createNewFile();
            ImageIO.write(recolor,"png", f);

            BufferedImage sheet = ImageIO.read(new File(characterSpriteSheet));
            animations = SpriteSheetParser.parse(sheet, rowCount, columnCount,  spriteWidth, spriteHeight);

            MovementAnimation playerAnimation = animations.get(0);
            OverworldPlayer player = new OverworldPlayer(playerAnimation, new File(playerPath), new Player(), 5);
            // player.setImg(Scalr.resize(player.getImg(), 4));

            player.getCoord().setX(60);
            player.getCoord().setY(5);
            player.getCoord().setZ(500);
            player.setName("Player");

            int range = Resolution.SCREEN_WIDTH/2;
            MovementAnimation homingAnim = SpriteSheetParser.parse(sheet, rowCount, columnCount,  spriteWidth, spriteHeight).get(0);
            HomingEntity enemy  = new HomingEntity(homingAnim, new File(playerPath), player, range, 7);
            enemy.getCoord().setX(160);
            enemy.getCoord().setY(1005);
            enemy.getCoord().setZ(500);
            enemy.setName("Enemy");

            List<Vector> vectors = new ArrayList<>();
            vectors.add(new Vector(0,50));
            vectors.add(new Vector(50,0));
            vectors.add(new Vector(0,-50));
            vectors.add(new Vector(-50,0));

            MovementAnimation vectorAnim = SpriteSheetParser.parse(sheet, rowCount, columnCount,  spriteWidth, spriteHeight).get(0);
            VectorEntity squareWalker = new VectorEntity(vectorAnim, new File(playerPath), 5, vectors);
            squareWalker.getCoord().setX(-20);
            squareWalker.getCoord().setY(-55);
            squareWalker.getCoord().setZ(500);
            squareWalker.setName("Bob");


            ItemSprite item = new ItemSprite(new File(playerPath), 1, 60,55,499, new Item("testItem", 200));

            try {
                TileMap tm = new TileMap("src/main/java/resources/gameFiles/models/objects/areas/TilesetAreaTest.json");
                Area a = tm.getArea();
                a.getSprites().add(item);
                List<Entity> entities = new ArrayList<>();
                entities.add(player);
                entities.add(enemy);
                entities.add(squareWalker);

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
}
