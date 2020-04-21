package engineFiles.main.game;

import engineFiles.ui.Settings;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
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

    public static boolean isPressed(ArrayList<Integer> keyCode) {
        for (int key:keyCode) {
            if (keys.contains(key)){
                return true;
            }
        }
        return false;
    }
}
