package engineFiles.main.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static engineFiles.main.models.WorldGenKeys.*;

//Class containing save game logic. Used to save the game, by storing individual classes into their respective models,
// which are then translated into json objects and stored into a file
public class SaveGame {

    /**
     * @param worldGenModel
     */
    public static void save(WorldGenModel worldGenModel) {
        JsonObject world = new JsonObject();
        world.add(WORLD_ITMES_KEY, ItemsModel.generateOverworldItemsJson(worldGenModel.getArea().getSpritesItems()));
        world.addProperty(TILESET_PATH_KEY, new File(worldGenModel.getTilesetPath()) + "/");
        world.add(TILESET_KEY, worldGenModel.getTileMap().getJson());
        world.add(COLORER_MODELS_KEY, worldGenModel.getColorerModel().getColorerModelJson());
        world.add(ENTITY_MODELS_KEY, worldGenModel.getEntitiesModel().getEntities());
        try {
            System.out.println("saving");
            Writer writer = new FileWriter(WORLD_GEN_JSON_PATH);
            new Gson().toJson(world, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to save");
            e.printStackTrace();
        }
    }
}
