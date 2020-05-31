package engineFiles.main.models;

public class WorldGenKeys {

    public static String TILESET_PATH_KEY = "tilesetPath";
    public static String TILESET_KEY = "tileset";
    public static String WORLD_GEN_JSON_PATH = "src/main/java/resources/gameFiles/models/WorldGen.json";
    public static String ENTITY_MODELS_KEY = "entities";
    public static String COLORER_MODELS_KEY = "colorer";



    public static class EntityKeys{
        public static String VECTOR_ENTITIES_KEY = "vector";
        public static String OVERWORLD_PLAYER_KEY = "overworldPlayer";
        public static String CONTROLLABLE_ENITITIES_KEY = "controllable";
        public static String HOMING_ENITITIES_KEY = "homing";
        public static String VECTROES_KEY = "vectors";
        public static String RANGE_KEY = "range";
        public static String TARGET_INDEX_KEY = "targetIndex";
        public static String PLAYER_KEY = "player";
        public static String SPEED_COUNTER_KEY = "speedCounter";
        public static String COORDINATES_KEY = "coordinates";
        public static String NAME_KEY = "name";
        public static String CHARACTER_SPRITE_SHEET_MODEL_KEY = "characterSpriteSheetModel";
        public static String UNIQUE_INDEX_KEY = "uniqueIndex";

    }

    public static class ColorerKeys{
        public static String TILESET_INPUT_DIR_KEY = "tilesetInputDir";
        public static String TILESET_OUTPUT_DIR_KEY = "tilesetOutputDir";
        public static String RECOLOR_KEY = "recolor";
        public static String RED_SHIFT_KEY = "redShift";
        public static String GREEN_SHIFT_KEY = "greenShift";
        public static String BLUE_SHIFT_KEY = "blueShift";
    }

    public static class AreaKeys{
        public static String NAME_KEY = "Name";
        public static String WIDTH_KEY = "Width";
        public static String HEIGHT_KEY = "Height";
        public static String SPRITES_KEY = "Sprites";
    }

    public static class TileMapKeys{
        public static String LAYERS_KEY = "layers";
        public static String TILE_SETS_KEY = "tilesets";
        public static String EDITOR_SETTINGS_KEY  = "editorsettings";
    }

    public static class PlayerKeys{
        public static String USERNAME_KEY = "username";
        public static String INVENTORY_KEY = "inventory";
    }



}

