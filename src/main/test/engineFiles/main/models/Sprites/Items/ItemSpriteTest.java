package engineFiles.main.models.Sprites.Items;


import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Sprites.Controlls;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.ui.Player;
import engineFiles.ui.Settings;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@RunWith(PowerMockRunner.class)

@PrepareForTest({ItemSprite.class, Item.class, KeyMap.class, Settings.class, Controlls.class, OverworldPlayer.class, Player.class})

class ItemSpriteTest {
    int keyMapInput = 0;

    @InjectMocks
    ItemSprite itemSprite;
    @InjectMocks Controlls controlls;
    @InjectMocks Item item;
    @InjectMocks OverworldPlayer sprite;
    @InjectMocks Player player;

    List<Integer> up = new ArrayList<>();
    List<Integer> down = new ArrayList<>();
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    List<Item> inventory = new ArrayList<>();
    int interaction = 12;


    void setUp() throws Exception {

        up.add(1);
        down.add(0);
        left.add(2);
        right.add(3);

        //SETS UP CLASS MOCKS
        itemSprite = mock(ItemSprite.class);
        item = mock(Item.class);
        controlls = mock(Controlls.class);
        sprite = mock(OverworldPlayer.class);
        player = mock(Player.class);

        when(itemSprite.onCollision(sprite)).thenCallRealMethod();
        when(controlls.getDown()).thenReturn(down);
        when(controlls.getLeft()).thenReturn(left);
        when(controlls.getRight()).thenReturn(right);
        when(controlls.getUp()).thenReturn(up);
        when(controlls.getInteraction()).thenReturn(interaction);



        PowerMockito.mockStatic(KeyMap.class);
        mockStatic(Settings.class);
        when(Settings.controlls.getInteraction()).thenReturn(interaction);
        when(player.getInventory()).thenReturn(inventory);
        PowerMockito.doAnswer(invocationOnMock -> inventory.add(item)).when(player).addItem(item);

    }

    @PrepareForTest(KeyMap.class)
    @Order(1)
    @Test
    void onCollision_addsItemToInventory_returnsTrue() {
        //ACT
        try {
            setUp();
            when(sprite.getCategoryName()).thenReturn("player");
            when(KeyMap.isPressed(interaction)).thenReturn(true);
            //ASSERT
            Assertions.assertEquals(0, this.inventory.size());
            Assertions.assertTrue(itemSprite.onCollision(sprite));
            Assertions.assertEquals(1, this.inventory.size());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @Order(2)
    @Test
    void onCollision_doesntAddItemToInventoryBecauseOfWrongName_returnsFalse() {
        //ACT
        try {
            setUp();
            when(sprite.getCategoryName()).thenReturn("doggo");
            when(KeyMap.isPressed(interaction)).thenReturn(true);
            //ASSERT
            Assertions.assertEquals(0, this.inventory.size());
            Assertions.assertFalse(itemSprite.onCollision(sprite));
            Assertions.assertEquals(0, this.inventory.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Order(3)
    @Test
    void onCollision_doesntAddItemToInventoryBecauseOfNoInteraction_returnsFalse() {
        //ACT
        try {
            setUp();
            when(sprite.getCategoryName()).thenReturn("player");
            when(KeyMap.isPressed(interaction)).thenReturn(false);
            //ASSERT
            Assertions.assertEquals(0, this.inventory.size());
            Assertions.assertFalse(itemSprite.onCollision(sprite));
            Assertions.assertEquals(0, this.inventory.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Order(4)
    @Test
    void onCollision_doesntAddItemToInventoryBecauseOfNoInteractionAndWrongName_returnsFalse() {
        //ACT
        try {
            setUp();
            when(sprite.getCategoryName()).thenReturn("doggo");
            when(KeyMap.isPressed(interaction)).thenReturn(false);
            //ASSERT
            Assertions.assertEquals(0, this.inventory.size());
            Assertions.assertFalse(itemSprite.onCollision(sprite));
            Assertions.assertEquals(0, this.inventory.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}