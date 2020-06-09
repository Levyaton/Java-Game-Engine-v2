package engineFiles.main.models.Sprites;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the games overworld controlls
public class Controlls {
    private static final Logger LOG = Logger.getLogger(Controlls.class.getName());
    private List<Integer> up;
    private List<Integer> down;
    private List<Integer> left;
    private List<Integer> right;
    private int gameSave;
    private int interaction;

    /**
     * @param up
     * @param down
     * @param left
     * @param right
     * 
     */
    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
        LOG.config("Controlls Initialized");
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction) {
        this(up, down, left, right);
        this.interaction = interaction;
        LOG.config("Controlls Initialized");
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction,
            int gameSave) {
        this(up, down, left, right, interaction);
        this.gameSave = gameSave;
        LOG.config("Controlls Initialized");
    }

    /**
     * @return List<Integer>
     */
    // GETTERS

    public List<Integer> getUp() {
        return up;
    }

    /**
     * @param up
     */
    public void setUp(List<Integer> up) {
        this.up = up;
    }

    /**
     * @return List<Integer>
     */
    public List<Integer> getDown() {
        return down;
    }

    /**
     * @param down
     */
    public void setDown(List<Integer> down) {
        this.down = down;
    }

    /**
     * @return List<Integer>
     */
    // SETTERS

    public List<Integer> getLeft() {
        return left;
    }

    /**
     * @param left
     */
    public void List(ArrayList<Integer> left) {
        this.left = left;
    }

    /**
     * @return List<Integer>
     */
    public List<Integer> getRight() {
        return right;
    }

    /**
     * @param right
     */
    public void setRight(ArrayList<Integer> right) {
        this.right = right;
    }

    /**
     * @return int
     */
    public int getInteraction() {
        return interaction;
    }

    /**
     * @param interaction
     */
    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    /**
     * @return int
     */
    public int getGameSave() {
        return gameSave;
    }
}
