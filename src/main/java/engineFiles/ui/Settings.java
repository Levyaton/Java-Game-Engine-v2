package engineFiles.ui;

import engineFiles.main.models.Sprites.Controlls;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Settings {

    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static double MOVEMENT_SPEED = 1;

    public static Controlls controlls = setControlls();


    private static Controlls setControlls() {
        ArrayList<Integer> up = new ArrayList<>(Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_W));
        ArrayList<Integer> down = new ArrayList<>(Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_S));
        ArrayList<Integer> left = new ArrayList<>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A));
        ArrayList<Integer> right = new ArrayList<>(Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_D));
        int interaction = KeyEvent.VK_SPACE;

        return new Controlls(up, down, left, right, interaction);
    }

}
