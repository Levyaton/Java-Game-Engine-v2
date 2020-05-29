package engineFiles.main.models;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import engineFiles.main.models.Sprites.Entities.*;
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
import java.util.function.Function;

public class EntitiesModel {
    private Gson gson = new Gson();
    OverworldPlayer player;
    List<HomingEntity> homing;
    List<ControllableEntity> controlable;
    List<VectorEntity> vector;


    public EntitiesModel(JsonObject json) throws IOException {
        List<Entity> allEntities = new ArrayList<>();
        this.player = initOverworldPlayer(json.get("overworldPlayer").getAsJsonObject());
        allEntities.add(player);
        this.controlable = initControllableEntities(json.get("controllable"));
        allEntities.addAll(controlable);
        this.vector = initVectorEntities(json.get("vector"));
        allEntities.addAll(vector);
        this.homing = initHomingEntities(json.get("homing"), allEntities);
    }

    private Entity loadVectorEntity(EntitiyParam param){
        List<Vector> vecotrs = new ArrayList<>();
        for (JsonElement el : param.getJson().get("vectors").getAsJsonArray()){
            JsonObject obj = el.getAsJsonObject();
            Vector vector = gson.fromJson(obj, Vector.class);
            vecotrs.add(vector);
        }
        return new VectorEntity(param.anim,(File) null, param.getSpeedCounter(), vecotrs);
    }

    private List<VectorEntity> initVectorEntities(JsonElement element) throws IOException {
       return initEntityArray(element, this::loadVectorEntity);
    }

    private List<ControllableEntity> initControllableEntities(JsonElement element) throws IOException {
        Function<EntitiyParam, Entity> e =
                param -> new ControllableEntity(param.anim,(File) null,  param.getSpeedCounter());
        return initEntityArray(element, e);
    }

    private Entity loadHomingEntity(EntitiyParam param){
        HomingEntity e = new HomingEntity(param.anim,(File) null, null, 0,  param.getSpeedCounter());
        e.setRange(param.getJson().get("range").getAsInt());
        e.setTargetIndex(param.getJson().get("targetIndex").getAsInt());
       return e;
    }

    private List<HomingEntity> initHomingEntities(JsonElement element, List<Entity> allEntities) throws IOException {
        List<HomingEntity> result = initEntityArray(element,this::loadHomingEntity);
        allEntities.addAll(result);
        for (HomingEntity e: result){
            int targetIndex = e.getTargetIndex();
            for(Entity ent: allEntities){
                if(ent.getUniqueIndex() == targetIndex){
                    e.setTarget(ent);
                    break;
                }
            }
        }
        return result;
    }

    private OverworldPlayer initOverworldPlayer(JsonObject json) throws IOException {
        Player p = new Player(json.get("player").getAsJsonObject());
        Function<EntitiyParam, Entity> init =
                entitiyParam -> new OverworldPlayer(entitiyParam.anim,(File) null, p, entitiyParam.getSpeedCounter());
        OverworldPlayer e = (OverworldPlayer) initEntity(json, init);
        return e;
    }

    private <T>List<T> initEntityArray(JsonElement element,  Function<EntitiyParam, Entity> createEntity) throws IOException {
        List<T> entities = new ArrayList<>();
        for (JsonElement el: element.getAsJsonArray()){
            entities.add((T)initEntity(element.getAsJsonObject(),createEntity));
        }
        return entities;
    }
    private Entity initEntity(JsonObject json, Function<EntitiyParam, Entity> createEntity) throws IOException {
        int speedCounter = json.get("speedCounter").getAsInt();
        Coordinates coordinates = gson.fromJson(json.get("coordinates"), Coordinates.class);
        String name = json.get("name").getAsString();
        CharacterSpriteSheetModel characterSpriteSheetModel = gson.fromJson(json.get("characterSpriteSheetModel"), CharacterSpriteSheetModel.class);
        BufferedImage sheet = ImageIO.read(new File(characterSpriteSheetModel.getSheetPath()));
        HashMap<Integer, MovementAnimation> animations = SpriteSheetParser.parse(sheet, characterSpriteSheetModel.getRowCount(), characterSpriteSheetModel.getColumnCount(),  characterSpriteSheetModel.getSpriteWidth(), characterSpriteSheetModel.getSpriteHeight());
        MovementAnimation movemet = animations.get(characterSpriteSheetModel.getEntityIndex());
        int index = json.get("uniqueIndex").getAsInt();
        EntitiyParam param = new EntitiyParam(movemet, speedCounter, json);
        Entity e = createEntity.apply(param);
        e.setCoord(coordinates);
        e.setName(name);
        e.setUniqueIndex(index);
        return e;
    }

    private class EntitiyParam{
        private MovementAnimation anim;
        private int speedCounter;
        private JsonObject json;
        EntitiyParam(MovementAnimation anim, int speedCounter, JsonObject json){
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
}
