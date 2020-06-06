package engineFiles.main.models;

import com.google.gson.*;
import engineFiles.main.models.Sprites.Entities.*;
import engineFiles.main.models.Sprites.Items.Item;
import engineFiles.ui.Coordinates;
import engineFiles.ui.Player;
import engineFiles.ui.charecterSpriteSheetClasses.SpriteSheetParser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import static engineFiles.main.models.WorldGenKeys.EntityKeys.*;
import static engineFiles.main.models.WorldGenKeys.InventoryKeys.*;
import static engineFiles.main.models.WorldGenKeys.PlayerKeys.*;

public class EntitiesModel {

    private Gson gson = new Gson();
    private OverworldPlayer player;
    private List<HomingEntity> homing;
    private List<ControllableEntity> controlable;
    private List<VectorEntity> vector;

    public EntitiesModel(OverworldPlayer player, List<HomingEntity> homing, List<ControllableEntity> controlable,
            List<VectorEntity> vector) {
        this.player = player;
        this.homing = homing;
        this.controlable = controlable;
        this.vector = vector;
    }

    public EntitiesModel(JsonObject json) throws IOException {
        List<Entity> allEntities = new ArrayList<>();
        this.player = initOverworldPlayer(json.get(OVERWORLD_PLAYER_KEY).getAsJsonObject());
        allEntities.add(player);
        this.controlable = initControllableEntities(json.get(CONTROLLABLE_ENITITIES_KEY));
        allEntities.addAll(controlable);
        this.vector = initVectorEntities(json.get(VECTOR_ENTITIES_KEY));
        allEntities.addAll(vector);
        this.homing = initHomingEntities(json.get(HOMING_ENITITIES_KEY), allEntities);
    }

    private Entity loadVectorEntity(EntitiyParam param) {
        List<Vector> vecotrs = new ArrayList<>();
        for (JsonElement el : param.getJson().get(VECTORS_KEY).getAsJsonArray()) {
            JsonObject obj = el.getAsJsonObject();
            Vector vector = gson.fromJson(obj, Vector.class);
            vecotrs.add(vector);
        }
        return new VectorEntity(param.anim,
                new File("src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png"),
                param.getSpeedCounter(), vecotrs);
    }

    private List<VectorEntity> initVectorEntities(JsonElement element) throws IOException {
        return initEntityArray(element, this::loadVectorEntity);
    }

    private List<ControllableEntity> initControllableEntities(JsonElement element) throws IOException {
        Function<EntitiyParam, Entity> e = param -> new ControllableEntity(param.anim,
                new File("src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png"),
                param.getSpeedCounter());
        return initEntityArray(element, e);
    }

    private Entity loadHomingEntity(EntitiyParam param) {
        HomingEntity e = new HomingEntity(param.anim,
                new File("src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png"), null, 0,
                param.getSpeedCounter());
        e.setRange(param.getJson().get(RANGE_KEY).getAsInt());
        e.setTargetIndex(param.getJson().get(TARGET_INDEX_KEY).getAsInt());
        return e;
    }

    private List<HomingEntity> initHomingEntities(JsonElement element, List<Entity> allEntities) throws IOException {
        List<HomingEntity> result = initEntityArray(element, this::loadHomingEntity);
        allEntities.addAll(result);
        for (HomingEntity e : result) {
            int targetIndex = e.getTargetIndex();
            for (Entity ent : allEntities) {
                if (ent.getUniqueIndex() == targetIndex) {
                    e.setTarget(ent);
                    break;
                }
            }
        }
        return result;
    }

    private OverworldPlayer initOverworldPlayer(JsonObject json) throws IOException {
        List<Item> inventory = new ArrayList<>();
        JsonObject p = json.get(PLAYER_KEY).getAsJsonObject();
        for (JsonElement el : p.getAsJsonArray(INVENTORY_KEY)) {
            JsonObject item = el.getAsJsonObject();
            String itemName = item.get(ITEM_NAME_KEY).getAsString();
            int healthMod = item.get(HEALTH_MOD_KEY).getAsInt();
            int speedMod = item.get(SPEED_MOD_KEY).getAsInt();
            int attackMod = item.get(ATTACK_MOD_KEY).getAsInt();
            int cost = item.get(COST_KEY).getAsInt();
            inventory.add(new Item(itemName, healthMod, speedMod, attackMod, cost));
        }
        Player player = new Player(p.get(USERNAME_KEY).getAsString(), inventory);

        Function<EntitiyParam, Entity> init = entitiyParam -> new OverworldPlayer(entitiyParam.anim,
                new File("src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png"), player,
                entitiyParam.getSpeedCounter());
        OverworldPlayer e = (OverworldPlayer) initEntity(json, init);
        return e;
    }

    private <T> List<T> initEntityArray(JsonElement element, Function<EntitiyParam, Entity> createEntity)
            throws IOException {
        List<T> entities = new ArrayList<>();
        for (JsonElement el : element.getAsJsonArray()) {
            entities.add((T) initEntity(el.getAsJsonObject(), createEntity));
        }
        return entities;
    }

    private Entity initEntity(JsonObject json, Function<EntitiyParam, Entity> createEntity) throws IOException {
        int speedCounter = json.get(SPEED_COUNTER_KEY).getAsInt();

        Coordinates coordinates = gson.fromJson(json.get(COORDINATES_KEY), Coordinates.class);
        String name = json.get(NAME_KEY).getAsString();
        System.out.println(name + "has a SPEED COUTNER of " + speedCounter);
        CharacterSpriteSheetModel characterSpriteSheetModel = new CharacterSpriteSheetModel(
                json.get(CHARACTER_SPRITE_SHEET_MODEL_KEY).getAsJsonObject());
        BufferedImage sheet = ImageIO.read(new File(characterSpriteSheetModel.getSheetPath()));
        HashMap<Integer, MovementAnimation> animations = SpriteSheetParser.parse(sheet,
                characterSpriteSheetModel.getRowCount(), characterSpriteSheetModel.getColumnCount(),
                characterSpriteSheetModel.getSpriteWidth(), characterSpriteSheetModel.getSpriteHeight());
        MovementAnimation movemet = animations.get(characterSpriteSheetModel.getEntityIndex());
        int index = json.get(UNIQUE_INDEX_KEY).getAsInt();
        EntitiyParam param = new EntitiyParam(movemet, speedCounter, json);
        Entity e = createEntity.apply(param);
        e.setCharacterSpriteSheetModel(characterSpriteSheetModel);
        e.setCurrentWidth(json.get(CURRENT_WIDTH_KEY).getAsInt());
        e.setCurrentHeight(json.get(CURRENT_HEIGHT_KEY).getAsInt());
        e.setCoord(coordinates);
        e.setOgCoord(coordinates);
        e.setName(name);
        e.setUniqueIndex(index);
        return e;
    }

    private class EntitiyParam {
        private MovementAnimation anim;
        private int speedCounter;
        private JsonObject json;

        EntitiyParam(MovementAnimation anim, int speedCounter, JsonObject json) {
            this.anim = anim;
            this.speedCounter = speedCounter;
            this.json = json;
        }

        public MovementAnimation getAnim() {
            return anim;
        }

        public int getSpeedCounter() {
            return speedCounter;
        }

        public JsonObject getJson() {
            return json;
        }
    }

    public OverworldPlayer getPlayer() {
        return player;
    }

    public List<HomingEntity> getHoming() {
        return homing;
    }

    public List<ControllableEntity> getControlable() {
        return controlable;
    }

    public List<VectorEntity> getVector() {
        return vector;
    }

    public void setPlayer(OverworldPlayer player) {
        this.player = player;
    }

    public void setHoming(List<HomingEntity> homing) {
        this.homing = homing;
    }

    public void setControlable(List<ControllableEntity> controlable) {
        this.controlable = controlable;
    }

    public void setVector(List<VectorEntity> vector) {
        this.vector = vector;
    }

    public List<Entity> getAllStandardEntities() {
        List<Entity> entities = new CopyOnWriteArrayList<>();
        entities.addAll(this.homing);
        entities.addAll(this.vector);
        entities.addAll(this.controlable);
        entities.addAll(this.homing);
        return entities;
    }

    public List<Entity> getAllEntities() {
        List<Entity> all = getAllStandardEntities();
        all.add(this.player);
        return all;
    }

    private JsonObject getEntityJson(Entity e) {
        JsonObject object = new JsonObject();
        object.addProperty(SPEED_COUNTER_KEY, e.getSpeedCounter());
        object.add(COORDINATES_KEY, JsonParser.parseString(gson.toJson(e.getOgCoord())));
        object.add(CHARACTER_SPRITE_SHEET_MODEL_KEY,
                e.getCharacterSpriteSheetModel().getCharacterSpriteSheetModelJson());
        object.addProperty(UNIQUE_INDEX_KEY, e.getUniqueIndex());
        object.addProperty(NAME_KEY, e.getName());
        object.addProperty(CURRENT_HEIGHT_KEY, e.getCurrentHeight());
        object.addProperty(CURRENT_WIDTH_KEY, e.getCurrentWidth());
        return object;
    }

    private JsonObject getHomingEntityJson(HomingEntity e) {
        JsonObject object = getEntityJson(e);
        object.addProperty(TARGET_INDEX_KEY, e.getTargetIndex());
        object.addProperty(RANGE_KEY, e.getRange());
        return object;
    }

    private JsonObject getVectorEntityJson(VectorEntity e) {

        JsonObject object = getEntityJson(e);
        JsonArray vectors = new JsonArray();
        for (Vector v : e.getVectors()) {
            vectors.add(JsonParser.parseString(gson.toJson(v)));
        }
        object.add(VECTORS_KEY, vectors);
        return object;
    }

    private JsonObject getControlableEntityJson(ControllableEntity e) {
        return getEntityJson(e);
    }

    private JsonObject getPlayerEntityJson(OverworldPlayer p) {
        JsonObject object = getEntityJson(p);
        JsonObject player = new JsonObject();
        JsonArray inventory = new JsonArray();
        for (Item i : p.getPlayer().getInventory()) {
            JsonObject item = new JsonObject();
            item.addProperty(ITEM_NAME_KEY, i.getName());
            item.addProperty(HEALTH_MOD_KEY, i.getHealthMod());
            item.addProperty(SPEED_MOD_KEY, i.getSpeedMod());
            item.addProperty(ATTACK_MOD_KEY, i.getAttackMod());
            item.addProperty(COST_KEY, i.getCost());
            inventory.add(item);
        }
        player.addProperty(USERNAME_KEY, p.getPlayer().getUsername());
        player.add(INVENTORY_KEY, inventory);
        object.add(PLAYER_KEY, player);
        return object;
    }

    private <T> JsonArray getEntitiesJson(List<T> entities, Function<T, JsonObject> function) {
        JsonArray arr = new JsonArray();
        for (T entity : entities) {
            arr.add(function.apply(entity));
        }
        return arr;
    }

    public JsonObject getEntities() {
        JsonObject entities = new JsonObject();
        entities.add(VECTOR_ENTITIES_KEY, getEntitiesJson(this.getVector(), this::getVectorEntityJson));
        entities.add(HOMING_ENITITIES_KEY, getEntitiesJson(this.getHoming(), this::getHomingEntityJson));
        entities.add(OVERWORLD_PLAYER_KEY, getPlayerEntityJson(player));
        entities.add(CONTROLLABLE_ENITITIES_KEY,
                getEntitiesJson(this.getControlable(), this::getControlableEntityJson));
        return entities;
    }

}
