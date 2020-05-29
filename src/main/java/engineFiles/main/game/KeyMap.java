package engineFiles.main.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KeyMap {
    private static Set<Integer> keys = new HashSet<>();

    public static void setKey(int keyCode, boolean unset) {
        if (unset) {
            keys.remove(keyCode);
            return;
        }
        keys.add(keyCode);
    }

    public static boolean isPressed(List<Integer> keyCode) {
        for (int key : keyCode) {
            if (keys.contains(key)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPressed(int keyCode) {

        return keys.contains(keyCode);
    }

    public static Set<Integer> getKeys() {
        return keys;
    }


}