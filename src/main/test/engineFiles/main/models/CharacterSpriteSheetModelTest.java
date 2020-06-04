package engineFiles.main.models;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CharacterSpriteSheetModelTest {



    @Test
    void getCharacterSpriteSheetModelJson_generatesAJasonContainingTheClassProperties_returnsJsonObject() {
        //ACT
         int rowCount = 2;
         int columnCount = 3;
         int spriteWidth = 4;
         int spriteHeight = 5;
         int entityIndex = 6;
         String sheetPath = "testPath";
         CharacterSpriteSheetModel model = new CharacterSpriteSheetModel(rowCount,columnCount,spriteWidth,spriteHeight,entityIndex,sheetPath);
        //ASSERT
        JsonObject object = model.getCharacterSpriteSheetModelJson();
        Assertions.assertEquals(rowCount, object.get("rowCount").getAsInt());
        Assertions.assertEquals(columnCount, object.get("columnCount").getAsInt());
        Assertions.assertEquals(spriteWidth, object.get("spriteWidth").getAsInt());
        Assertions.assertEquals(spriteHeight, object.get("spriteHeight").getAsInt());
        Assertions.assertEquals(entityIndex, object.get("entityIndex").getAsInt());
        Assertions.assertEquals(sheetPath, object.get("sheetPath").getAsString());

    }
}