package engineFiles.main.game;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Class used to keep track of typed keys
public class KeyMap {
    private static Set<Integer> keys = new HashSet<>();
    private static boolean pressed = false;

    /**
     * @param keyCode
     * @param unset
     */
    public static void setKey(int keyCode, boolean unset) {
        if (unset) {
            keys.remove(keyCode);
            return;
        }
        keys.add(keyCode);
        pressed = !unset;
    }

    /**
     * @param keyCode
     * @return boolean
     */
    public static boolean isPressed(List<Integer> keyCode) {
        for (int key : keyCode) {
            if (keys.contains(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean
     */
    public static boolean isPressed() {
        return pressed;
    }

    /**
     * @param isPressed
     */
    public static void setPressed(boolean isPressed) {
        pressed = isPressed;
    }

    /**
     * @param keyCode
     * @return boolean
     */
    public static boolean isPressed(int keyCode) {

        return keys.contains(keyCode);
    }

    /**
     * @return Set<Integer>
     */
    public static Set<Integer> getKeys() {
        return keys;
    }

}