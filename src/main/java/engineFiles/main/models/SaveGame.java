package engineFiles.main.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

import static engineFiles.main.models.WorldGenKeys.*;

//Class containing save game logic. Used to save the game, by storing individual classes into their respective models,
// which are then translated into json objects and stored into a file
public class SaveGame {
    private static final Logger LOG = Logger.getLogger(SaveGame.class.getName());
    /**
     * @param worldGenModel
     */
    public static void save(WorldGenModel worldGenModel) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        JsonObject world = new JsonObject();
        world.add(WORLD_ITMES_KEY, ItemsModel.generateOverworldItemsJson(worldGenModel.getArea().getSpritesItems()));
        world.addProperty(TILESET_PATH_KEY, new File(worldGenModel.getTilesetPath()) + "/");
        world.add(TILESET_KEY, worldGenModel.getTileMap().getJson());
        world.add(COLORER_MODELS_KEY, worldGenModel.getColorerModel().getColorerModelJson());
        world.add(ENTITY_MODELS_KEY, worldGenModel.getEntitiesModel().getEntities());
        try {
            LOG.info("Saving");
            Writer writer = new FileWriter(WORLD_GEN_JSON_PATH);
            new Gson().toJson(world, writer);
            writer.close();
        } catch (IOException e) {
            LOG.severe("Failed to save");
            e.printStackTrace();
        }
        LOG.info("Game Saved");
    }
}
