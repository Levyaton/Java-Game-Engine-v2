package engineFiles.main.models;


//Class containing static string keys, used to make making edits to strings easier, since they are, for the most part, here, and not in
//random classes
public class WorldGenKeys {

    public static String TILESET_PATH_KEY = "tilesetPath";
    public static String TILESET_KEY = "tileset";
    public static String WORLD_GEN_JSON_PATH = "src/main/java/resources/gameFiles/models/WorldGen.json";
    public static String ENTITY_MODELS_KEY = "entities";
    public static String COLORER_MODELS_KEY = "colorer";
    public static String WORLD_ITMES_KEY = "items";

    public static class EntityKeys {
        public static String VECTOR_ENTITIES_KEY = "vector";
        public static String OVERWORLD_PLAYER_KEY = "overworldPlayer";
        public static String CONTROLLABLE_ENITITIES_KEY = "controllable";
        public static String HOMING_ENITITIES_KEY = "homing";
        public static String VECTORS_KEY = "vectors";
        public static String RANGE_KEY = "range";
        public static String TARGET_INDEX_KEY = "targetIndex";
        public static String PLAYER_KEY = "player";
        public static String SPEED_COUNTER_KEY = "speedCounter";
        public static String COORDINATES_KEY = "coordinates";
        public static String OG_COORDINATES_KEY = "ogCoordinates";
        public static String NAME_KEY = "name";
        public static String CHARACTER_SPRITE_SHEET_MODEL_KEY = "characterSpriteSheetModel";
        public static String UNIQUE_INDEX_KEY = "uniqueIndex";
        public static String CURRENT_WIDTH_KEY = "currentWidth";
        public static String CURRENT_HEIGHT_KEY = "currentHeight";

    }

    public static class ColorerKeys {
        public static String TILESET_INPUT_DIR_KEY = "tilesetInputDir";
        public static String TILESET_OUTPUT_DIR_KEY = "tilesetOutputDir";
        public static String RECOLOR_KEY = "recolor";
        public static String RED_SHIFT_KEY = "redShift";
        public static String GREEN_SHIFT_KEY = "greenShift";
        public static String BLUE_SHIFT_KEY = "blueShift";
    }

    public static class AreaKeys {
        public static String NAME_KEY = "Name";
        public static String WIDTH_KEY = "Width";
        public static String HEIGHT_KEY = "Height";
        public static String SPRITES_KEY = "Sprites";
    }

    public static class TileMapKeys {
        public static String LAYERS_KEY = "layers";
        public static String TILE_SETS_KEY = "tilesets";
        public static String EDITOR_SETTINGS_KEY = "editorsettings";
    }

    public static class PlayerKeys {
        public static String USERNAME_KEY = "username";
        public static String INVENTORY_KEY = "inventory";
    }

    public static class InventoryKeys {
        public static String DESCRIPTION_KEY = "description";
        public static String ITEM_NAME_KEY = "name";
        public static String HEALTH_MOD_KEY = "health";
        public static String SPEED_MOD_KEY = "speed";
        public static String ATTACK_MOD_KEY = "attack";
        public static String COST_KEY = "cost";
    }

    public static class OverworldItemsKeys {
        public static String FILE_PATH_KEY = "path";
        public static String DIM_MOD_KEY = "dim";
        public static String X_KEY = "x";
        public static String Y_KEY = "y";
        public static String Z_KEY = "z";
    }

    public static class CharacterSpriteSheetKeys {
        public static String SHEET_PATH_KEY = "sheetPath";
        public static String ENTITY_INDEX_KEY = "entityIndex";
        public static String SPRITE_HEIGHT_KEY = "spriteHeight";
        public static String SPRITE_WIDTH_KEY = "spriteWidth";
        public static String COLUMN_COUNT_KEY = "columnCount";
        public static String ROW_COUNT_KEY = "rowCount";
    }

}
