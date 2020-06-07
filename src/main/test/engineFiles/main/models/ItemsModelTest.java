package engineFiles.main.models;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import engineFiles.main.models.Sprites.Items.Item;
import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.ui.Coordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.File;
import java.util.List;

import static engineFiles.main.models.WorldGenKeys.InventoryKeys.*;
import static engineFiles.main.models.WorldGenKeys.OverworldItemsKeys.*;
import static org.mockito.Mockito.withSettings;
import static org.powermock.api.mockito.PowerMockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@RunWith(PowerMockRunner.class)

@PrepareForTest({Item.class, ItemSprite.class, Coordinates.class})
class ItemsModelTest {

    @Mock
    static Coordinates coordinates;
    static int dimMod = 999;
    static  int x  = 999;
    static int y = 999;
    static int z = 999;
    static int cost = 999;
    static int healthMod = 999;
    static int speedMod = 999;
    static int attackMod = 999;
    static String name = "testName";
    static String description = "testDescription";
    @Mock
    static Item item;
    @Mock
    static ItemSprite itemSprite;
    static String path = "testPath";
    static File file = new File(path);

    public void prepareMocks() throws Exception {
        File f= new File(path);
        coordinates = mock(Coordinates.class);
        item = mock(Item.class);
        itemSprite = mock(ItemSprite.class,withSettings()
                .useConstructor());
        whenNew(Item.class).withArguments(name, healthMod, speedMod, attackMod, description).thenReturn(item);
        whenNew(ItemSprite.class).withArguments(file, dimMod, x, y, z,item).thenReturn(itemSprite);
        whenNew(Coordinates.class).withAnyArguments().thenReturn(coordinates);
        when(coordinates.getX()).thenReturn(x);
        when(coordinates.getY()).thenReturn(y);
        when(coordinates.getZ()).thenReturn(z);
        when(item.getName()).thenReturn(name);
        when(item.getHealthMod()).thenReturn(healthMod);
        when(item.getSpeedMod()).thenReturn(speedMod);
        when(item.getAttackMod()).thenReturn(attackMod);
        when(item.getDescription()).thenReturn(description);
        when(itemSprite.getItem()).thenReturn(item);
        when(itemSprite.getDimMod()).thenReturn(dimMod);
        when(itemSprite.getCoord()).thenReturn(coordinates);
       // when(itemSprite.getCoord().getX()).thenReturn(x);
       // when(itemSprite.getCoord().getY()).thenReturn(y);
       // when(itemSprite.getCoord().getZ()).thenReturn(z);
        when(itemSprite.getItem().getCost()).thenReturn(cost);
        when(itemSprite.getItem().getHealthMod()).thenReturn(healthMod);
        when(itemSprite.getItem().getAttackMod()).thenReturn(attackMod);
        when(itemSprite.getItem().getDescription()).thenReturn(description);
    }
    @Test
    void generateOverworldItems() throws Exception {
        //ACT
        prepareMocks();
        int objectCount = 10;
        JsonArray array = new JsonArray();
        for(int x = 0; x < objectCount; x++){
            JsonObject obj = new JsonObject();
            obj.addProperty(ITEM_NAME_KEY, name);
            obj.addProperty(FILE_PATH_KEY, file.getPath());
            obj.addProperty(DIM_MOD_KEY, dimMod);
            obj.addProperty(X_KEY, x);
            obj.addProperty(Y_KEY,y);
            obj.addProperty(Z_KEY, z);
            obj.addProperty(COST_KEY, cost);
            obj.addProperty(HEALTH_MOD_KEY, healthMod);
            obj.addProperty(SPEED_MOD_KEY, speedMod);
            obj.addProperty(ATTACK_MOD_KEY, attackMod);
            obj.addProperty(DESCRIPTION_KEY, description);
            array.add(obj);
        }

        List<ItemSprite> result = ItemsModel.generateOverworldItems(array);
        //ASSERT
        Assertions.assertEquals(objectCount, result.size());
        Assertions.assertEquals(dimMod, result.get(0).getDimMod());
        Assertions.assertEquals(x, result.get(0).getCoord().getX());
        Assertions.assertEquals(y, result.get(0).getCoord().getY());
        Assertions.assertEquals(z, result.get(0).getCoord().getZ());
        Assertions.assertEquals(cost, result.get(0).getItem().getCost());
        Assertions.assertEquals(healthMod, result.get(0).getItem().getHealthMod());
        Assertions.assertEquals(speedMod, result.get(0).getItem().getSpeedMod());
        Assertions.assertEquals(attackMod, result.get(0).getItem().getAttackMod());
        Assertions.assertEquals(name, result.get(0).getName());
        Assertions.assertEquals(description, result.get(0).getItem().getDescription());
        Assertions.assertEquals(item, result.get(0).getItem());
        Assertions.assertEquals(itemSprite, result.get(0));
        Assertions.assertEquals(file, result.get(0).getOgFile());

        /*static int dimMod = 999;
    static  int x  = 999;
    static int y = 999;
    static int z = 999;
    static int cost = 999;
    static int healthMod = 999;
    static int speedMod = 999;
    static int attackMod = 999;
    static String name = "testName";
    static String description = "testDescription";
    static Item item;
    static ItemSprite itemSprite;
    static File file = new File("testPath");

         */
    }

    @Test
    void generateOverworldItemsJson() {
    }
}