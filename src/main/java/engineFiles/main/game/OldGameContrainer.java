package engineFiles.main.game;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
// import engineFiles.GUIs.mainGameGui.GamePanel;
// import engineFiles.GUIs.mainGameGui.OverworldPanel;
// import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.colorer.program.RecolorV3;
import engineFiles.main.models.*;
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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static engineFiles.main.models.WorldGenKeys.PlayerKeys.INVENTORY_KEY;
import static engineFiles.main.models.WorldGenKeys.PlayerKeys.USERNAME_KEY;

public class OldGameContrainer {

        public static void main(String[] args) {
                // String areaDir = "src/main/java/resources/gameFiles/models/objects/areas/";
                // BufferedImage test = null;
                String playerPath = "src/main/java/resources/gameFiles/models/sprites/static/other/redSquare.png";

                // COLORING
                String inputDir = "src/main/java/resources/gameFiles/models/tilesets/basictiles.png";
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
                        ImageIO.write(recolor, "png", f);

                        BufferedImage sheet = ImageIO.read(new File(characterSpriteSheet));
                        animations = SpriteSheetParser.parse(sheet, rowCount, columnCount, spriteWidth, spriteHeight);

                        MovementAnimation playerAnimation = animations.get(0);
                        JsonObject playerJson = new JsonObject();
                        JsonArray inventory = new JsonArray();
                        playerJson.addProperty(USERNAME_KEY, "user");
                        playerJson.add(INVENTORY_KEY, inventory);
                        CharacterSpriteSheetModel model = new CharacterSpriteSheetModel(rowCount, columnCount,
                                        spriteWidth, spriteHeight, 0, characterSpriteSheet);
                        OverworldPlayer player = new OverworldPlayer(playerAnimation, new File(playerPath),
                                        new Player(playerJson), 5);
                        // player.setImg(Scalr.resize(player.getImg(), 4));
                        player.setCharacterSpriteSheetModel(model);
                        player.getCoord().setX(60);
                        player.getCoord().setY(5);
                        player.getCoord().setZ(500);
                        player.setOgCoord(player.getCoord());
                        player.setName("Player");
                        player.getPlayer().getInventory().add(new Item("testName1", 0, 0, 2, "+2 DMG"));
                        player.getPlayer().getInventory().add(new Item("testName2", 5, 0, 0, "+5 HP"));
                        player.getPlayer().getInventory().add(new Item("testName3", 0, 10, 0, "+10 MS"));
                        player.getPlayer().getInventory().add(new Item("testName4", 3, 0, 1, "+1 DMG, +3 HP"));
                        int range = Resolution.SCREEN_WIDTH / 2;
                        MovementAnimation homingAnim = SpriteSheetParser
                                        .parse(sheet, rowCount, columnCount, spriteWidth, spriteHeight).get(0);
                        HomingEntity enemy = new HomingEntity(homingAnim, new File(playerPath), player, range, 7);
                        enemy.getCoord().setX(160);
                        enemy.getCoord().setY(1005);
                        enemy.getCoord().setZ(500);
                        enemy.setOgCoord(enemy.getCoord());
                        enemy.setName("Enemy");
                        enemy.setCharacterSpriteSheetModel(model);

                        List<Vector> vectors = new ArrayList<>();
                        vectors.add(new Vector(0, 50));
                        vectors.add(new Vector(50, 0));
                        vectors.add(new Vector(0, -50));
                        vectors.add(new Vector(-50, 0));

                        MovementAnimation vectorAnim = SpriteSheetParser
                                        .parse(sheet, rowCount, columnCount, spriteWidth, spriteHeight).get(0);
                        VectorEntity squareWalker = new VectorEntity(vectorAnim, new File(playerPath), 5, vectors);
                        squareWalker.setCharacterSpriteSheetModel(model);
                        squareWalker.getCoord().setX(-20);
                        squareWalker.getCoord().setY(-55);
                        squareWalker.getCoord().setZ(500);
                        squareWalker.setName("Bob");
                        squareWalker.setOgCoord(squareWalker.getCoord());

                        ItemSprite item = new ItemSprite(new File(playerPath), 1, 60, 55, 499,
                                        new Item("testItem", 200));

                        try {
                                TileMap tm = new TileMap(JsonParser.parseReader(new FileReader(
                                                "src/main/java/resources/gameFiles/models/objects/areas/TilesetAreaTest.json"))
                                                .getAsJsonObject(),
                                                "src/main/java/resources/playerGameFiles/sprites/tilesets/");
                                Area a = tm.getArea();
                                a.getSprites().add(item);
                                List<Entity> entities = new ArrayList<>();
                                entities.add(player);
                                entities.add(enemy);
                                entities.add(squareWalker);

                                List<HomingEntity> homingEntities = new ArrayList<>();
                                homingEntities.add(enemy);

                                List<ControllableEntity> controllableEntities = new ArrayList<>();
                                List<VectorEntity> vectorEntities = new ArrayList<>();
                                vectorEntities.add(squareWalker);
                                List<ItemSprite> items = new ArrayList<>();
                                items.add(item);
                                WorldGenModel worldGenModel = new WorldGenModel(tm,
                                                new ColorerModel(inputDir, outputDir, true),
                                                new EntitiesModel(player, homingEntities, controllableEntities,
                                                                vectorEntities),
                                                "src/main/java/resources/playerGameFiles/sprites/tilesets/", items);
                                Window frame = new Window(worldGenModel);
                                GameContainer gc = new GameContainer(frame);
                        } catch (FileNotFoundException e) {
                                e.printStackTrace();
                        }
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

}
