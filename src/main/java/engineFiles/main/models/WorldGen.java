package engineFiles.main.models;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
// import engineFiles.GUIs.mainGameGui.GamePanel;
// import engineFiles.GUIs.mainGameGui.OverworldPanel;
// import engineFiles.GUIs.mainGameGui.PanelManager;
import engineFiles.GUIs.mainGameGui.Window;
import engineFiles.colorer.program.RecolorV3;
import engineFiles.main.game.GameContainer;
import engineFiles.ui.TileMapClasses.TileMap;
import engineFiles.ui.fonts.FontLibrary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

import static engineFiles.main.models.WorldGenKeys.*;

//Class containing world generation logic. Mainly used for game loading
public class WorldGen {

    public WorldGen() {
        LOG.config("WorldGen Initialized");
    }

    private static final Logger LOG = Logger.getLogger(WorldGen.class.getName());

    /**
     * @throws FileNotFoundException
     */
    public void generateWorld() throws FileNotFoundException {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        Gson gson = new Gson();
        BufferedReader br = null;

        JsonObject worldGen = JsonParser.parseReader(new FileReader(WORLD_GEN_JSON_PATH)).getAsJsonObject();
        String tilesetPath = worldGen.get(TILESET_PATH_KEY).getAsString();
        try {
            EntitiesModel em = new EntitiesModel(worldGen.get(ENTITY_MODELS_KEY).getAsJsonObject());
            ColorerModel cm = new ColorerModel(worldGen.get(COLORER_MODELS_KEY).getAsJsonObject());
            if (cm.isRecolor()) {
                RecolorV3.recolorAndSave(cm);
            }
            TileMap tm = new TileMap(worldGen.get(TILESET_KEY).getAsJsonObject(), tilesetPath);
            FontLibrary fl = new FontLibrary();
            WorldGenModel model = new WorldGenModel(tm, cm, em, tilesetPath,
                    ItemsModel.generateOverworldItems(worldGen.getAsJsonArray(WORLD_ITMES_KEY)));

            Window frame = new Window(model);
            GameContainer gc = new GameContainer(frame);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.severe("Failed to generate world");
        }
    }
}
