package engineFiles.main.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static engineFiles.main.models.WorldGenKeys.*;

public class SaveGame {

    public static void save(WorldGenModel worldGenModel){
        JsonObject world = new JsonObject();
        world.add(WORLD_ITMES_KEY,ItemsModel.generateOverworldItemsJson(worldGenModel.getArea().getItems()));
        world.addProperty(TILESET_PATH_KEY, new File(worldGenModel.getTilesetPath()) + "\\");
        world.add(TILESET_KEY, worldGenModel.getTileMap().getJson());
        world.add(COLORER_MODELS_KEY, worldGenModel.getColorerModel().getColorerModelJson());
        world.add(ENTITY_MODELS_KEY, worldGenModel.getEntitiesModel().getEntities());
        try {
            Writer writer = new FileWriter(WORLD_GEN_JSON_PATH);
            new Gson().toJson(world, writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
