package engineFiles.ui;

import engineFiles.main.models.Sprites.Controlls;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing  the game settings
public class Settings {
    private static final Logger LOG = Logger.getLogger(Settings.class.getName());
    public static int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    public static double MOVEMENT_SPEED = 1;

    public static Controlls controlls = setControlls();

    public static class ColorerSettings{
        public static boolean recolor = true;
        public static int redShift = new Random().nextInt(255);
        public static int greenShift = new Random().nextInt(255);
        public static int blueShift = new Random().nextInt(255);
    }


    private static Controlls setControlls() {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        ArrayList<Integer> up = new ArrayList<>(Arrays.asList(KeyEvent.VK_UP, KeyEvent.VK_W));
        ArrayList<Integer> down = new ArrayList<>(Arrays.asList(KeyEvent.VK_DOWN, KeyEvent.VK_S));
        ArrayList<Integer> left = new ArrayList<>(Arrays.asList(KeyEvent.VK_LEFT, KeyEvent.VK_A));
        ArrayList<Integer> right = new ArrayList<>(Arrays.asList(KeyEvent.VK_RIGHT, KeyEvent.VK_D));
        int interaction = KeyEvent.VK_SPACE;
        int gameSave = KeyEvent.VK_ENTER;
        LOG.info("Controlls set");
        return new Controlls(up, down, left, right, interaction,gameSave);
    }

}
