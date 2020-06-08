package integrationTests;
import engineFiles.GUIs.mainGameGui.OverworldPanel;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Area;
import engineFiles.main.models.Sprites.Entities.ControllableEntity;
import engineFiles.main.models.Sprites.Entities.MovementAnimation;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.main.models.Sprites.SpriteCollection;
import engineFiles.ui.Coordinates;
import engineFiles.ui.Player;
import engineFiles.ui.Settings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class IntegrationTests {


    @Test
    public void itemAddedToInventoty(){
        //ACT
        ItemSprite itemSprite = setUpItemSprite();
        OverworldPlayer overworldPlayer = setUpOverWorldPlayer();
        OverworldPanel op = setUpOverworldPanel(itemSprite,overworldPlayer);
        //ASSERT
        Assertions.assertTrue(op.getArea().getSpritesItems().contains(itemSprite));
        Assertions.assertFalse(((OverworldPlayer)op.getPlayer()).getPlayer().getInventory().contains(itemSprite.getItem()));
        op.keyPressed(simmulateKeyPress(KeyEvent.VK_SPACE));
        Assertions.assertFalse(op.getArea().getSpritesItems().contains(itemSprite));
        Assertions.assertTrue(((OverworldPlayer)op.getPlayer()).getPlayer().getInventory().contains(itemSprite.getItem()));
    }



    @Test
    public void playerMovementTest(){
        //ACT

        OverworldPlayer overworldPlayer = setUpOverWorldPlayer();
        OverworldPanel op = setUpOverworldPanel(overworldPlayer);
        Coordinates playerPostion = new Coordinates(op.getPlayer().getCoord().getX(), op.getPlayer().getCoord().getY(), op.getPlayer().getCurrentWidth(), op.getPlayer().getCurrentHeight());
        //ASSERT
        //System.out.println(playerPostion.getX()  - Settings.MOVEMENT_SPEED + ", " + playerPostion.getY() + " is expected");
        KeyMap.setKey(KeyEvent.VK_A, false);
        op.update();
        KeyMap.setKey(KeyEvent.VK_A, true);
        Assertions.assertEquals((playerPostion.getX() - Settings.MOVEMENT_SPEED), op.getPlayer().getCoord().getX());
        Assertions.assertEquals((playerPostion.getY()), op.getPlayer().getCoord().getY());

        playerPostion = new Coordinates(op.getPlayer().getCoord().getX(), op.getPlayer().getCoord().getY(), op.getPlayer().getCurrentWidth(), op.getPlayer().getCurrentHeight());
        //System.out.println(playerPostion.getX() + ", " + playerPostion.getY());
        KeyMap.setKey(KeyEvent.VK_W, false);
        op.update();
        KeyMap.setKey(KeyEvent.VK_W, true);
        Assertions.assertEquals((playerPostion.getX()), op.getPlayer().getCoord().getX());
        Assertions.assertEquals((playerPostion.getY() -  Settings.MOVEMENT_SPEED), op.getPlayer().getCoord().getY());

        playerPostion = new Coordinates(op.getPlayer().getCoord().getX(), op.getPlayer().getCoord().getY(), op.getPlayer().getCurrentWidth(), op.getPlayer().getCurrentHeight());
       // System.out.println(playerPostion.getX() + ", " + playerPostion.getY());

        KeyMap.setKey(KeyEvent.VK_S, false);
        op.update();
        KeyMap.setKey(KeyEvent.VK_S, true);
        Assertions.assertEquals((playerPostion.getX()), op.getPlayer().getCoord().getX());
        Assertions.assertEquals((playerPostion.getY()) +  Settings.MOVEMENT_SPEED, op.getPlayer().getCoord().getY());

        playerPostion = new Coordinates(op.getPlayer().getCoord().getX(), op.getPlayer().getCoord().getY(), op.getPlayer().getCurrentWidth(), op.getPlayer().getCurrentHeight());

        KeyMap.setKey(KeyEvent.VK_D, false);
        op.update();
        KeyMap.setKey(KeyEvent.VK_D, true);
        Assertions.assertEquals((playerPostion.getX() + Settings.MOVEMENT_SPEED), op.getPlayer().getCoord().getX());
        Assertions.assertEquals((playerPostion.getY()), op.getPlayer().getCoord().getY());

    }

    @Test
    public void collisionTest(){
        //ACT
        ItemSprite item = setUpItemSprite();
        item.setCoord(new Coordinates(0,0,item.getCurrentWidth(),item.getCurrentHeight()));
        OverworldPlayer player = setUpOverWorldPlayer();
        player.setCoord(new Coordinates(0,0,player.getCurrentWidth(),player.getCurrentHeight()));
        OverworldPanel overworldPanel = setUpOverworldPanel(player);
        //ASSERT
        Coordinates ogPlayerPostion = new Coordinates(overworldPanel.getPlayer().getCoord().getX(), overworldPanel.getPlayer().getCoord().getY(), overworldPanel.getPlayer().getCurrentWidth(), overworldPanel.getPlayer().getCurrentHeight());
        Coordinates currentPlayerPosition = new Coordinates(overworldPanel.getPlayer().getCoord().getX(), overworldPanel.getPlayer().getCoord().getY(), overworldPanel.getPlayer().getCurrentWidth(), overworldPanel.getPlayer().getCurrentHeight());
        KeyMap.setKey(KeyEvent.VK_W, false);
        overworldPanel.update();
        KeyMap.setKey(KeyEvent.VK_W, true);
        Assertions.assertEquals((currentPlayerPosition.getX()), overworldPanel.getPlayer().getCoord().getX());
        Assertions.assertEquals((currentPlayerPosition.getY() -  Settings.MOVEMENT_SPEED), overworldPanel.getPlayer().getCoord().getY());
        Assertions.assertEquals((currentPlayerPosition.getX()), overworldPanel.getPlayer().getCoord().getX());
        player.setCoord(new Coordinates(0,0,player.getCurrentWidth(),player.getCurrentHeight()));
        overworldPanel.getArea().getSpritesItems().add(item);
        KeyMap.setKey(KeyEvent.VK_W, false);
        overworldPanel.update();
        KeyMap.setKey(KeyEvent.VK_W, true);
        Assertions.assertEquals((currentPlayerPosition.getX()), overworldPanel.getPlayer().getCoord().getX());
        Assertions.assertNotEquals((currentPlayerPosition.getY() -  Settings.MOVEMENT_SPEED), overworldPanel.getPlayer().getCoord().getY());
    }

    @Test
    public void multipleControllablesTest(){
        //ACT
        OverworldPlayer overworldPlayer = setUpOverWorldPlayer();
        OverworldPanel overworldPanel = setUpOverworldPanel(overworldPlayer);
        ControllableEntity controllableEntity = setUpControlebleEntity();
        Coordinates oldPlayerCoords = new Coordinates(overworldPlayer.getCoord().getX(), overworldPlayer.getCoord().getY(), overworldPlayer.getCurrentWidth(), overworldPlayer.getCurrentHeight());
        int oldContrrollableX = 1000;
        int oldContrrollableY = 1000;
        controllableEntity.setCoord(new Coordinates(oldContrrollableX, oldContrrollableY, controllableEntity.getCurrentWidth(), controllableEntity.getCurrentHeight()));
        overworldPanel.getEntities().add(controllableEntity);
        //System.out.println( oldControlableEntityCoords.getY() -  controllableEntity.getCoord().getMOD());
        KeyMap.setKey(KeyEvent.VK_W, false);
        overworldPanel.update();
        KeyMap.setKey(KeyEvent.VK_W, true);
        Assertions.assertEquals((oldPlayerCoords.getX()), overworldPanel.getPlayer().getCoord().getX());
        Assertions.assertEquals((oldPlayerCoords.getY() -  Settings.MOVEMENT_SPEED), overworldPanel.getPlayer().getCoord().getY());
        Assertions.assertEquals(oldContrrollableX, overworldPanel.getEntities().get(1).getCoord().getX());
        Assertions.assertEquals((oldContrrollableY -  controllableEntity.getCoord().getMOD()), overworldPanel.getEntities().get(1).getCoord().getY());

    }
    private KeyEvent simmulateKeyPress(int keycode){
        return new KeyEvent(new Button(), 1, 20, 1, keycode, 'a');
    }
    private  ItemSprite setUpItemSprite(){
        int width = 148;
        int height = 260;
        int dimMod = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        int speedMod = 0;
        int defMod = 0;
        int healthMod = 0;
        int attackMod = 0;

        File sourceImage = new File("src/main/java/resources/gameFiles/models/tilesets/basictiles.png");
        ItemSprite itemSprite = new ItemSprite(sourceImage,dimMod,x,y,z,"item",healthMod,speedMod,defMod,attackMod);
        itemSprite.setCurrentWidth(width);
        itemSprite.setCurrentHeight(height);
        Coordinates coordinates = new Coordinates(0,0,width,height);
        itemSprite.setCoord(coordinates);
        return itemSprite;
    }

    private OverworldPlayer setUpOverWorldPlayer(){
        File sourceImage = new File("src/main/java/resources/gameFiles/models/tilesets/basictiles.png");
        return new OverworldPlayer(new MovementAnimation(), sourceImage, new Player("PlayerUsername", new ArrayList<>()), 0);
    }

    private ControllableEntity setUpControlebleEntity(){
        File sourceImage = new File("src/main/java/resources/gameFiles/models/tilesets/basictiles.png");
        MovementAnimation animation = new MovementAnimation();
        int speedCounter = 0;
        return new ControllableEntity(animation, sourceImage, speedCounter);
    }

    private OverworldPanel setUpOverworldPanel(ItemSprite itemSprite, OverworldPlayer overworldPlayer){
        int width = 148;
        int height = 260;

        SpriteCollection sprites = new SpriteCollection();
        sprites.add(itemSprite);
        Area area = new Area(sprites, "testArea", 400,400);
        area.addItems(Arrays.asList(itemSprite));
        OverworldPanel op = new OverworldPanel("testPanel", null, false, area,new ArrayList<>(Arrays.asList(overworldPlayer)));
        return op;
    }

    private OverworldPanel setUpOverworldPanel(OverworldPlayer overworldPlayer){
        int width = 148;
        int height = 260;

        SpriteCollection sprites = new SpriteCollection();

        Area area = new Area(sprites, "testArea", 400,400);
        OverworldPanel op = new OverworldPanel("testPanel", null, false, area,new ArrayList<>(Arrays.asList(overworldPlayer)));
        return op;
    }




}
