package engineFiles.ui;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Settings {

    public static double screenWidth;
    public static double screenHeight;


    public static Controlls controlls = setControlls();


    private static Controlls setControlls() {
        ArrayList<Integer> up = new ArrayList<>(Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_W));
        ArrayList<Integer> down = new ArrayList<>(Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_S));
        ArrayList<Integer> left = new ArrayList<>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A));
        ArrayList<Integer> right = new ArrayList<>(Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_D));

        return new Controlls(up, down, left, right);
    }

}
