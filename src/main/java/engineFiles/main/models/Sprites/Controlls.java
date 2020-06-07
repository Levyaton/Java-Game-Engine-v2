package engineFiles.main.models.Sprites;

import java.util.ArrayList;
import java.util.List;

//Class containing the games overworld controlls
public class Controlls {
    private List<Integer> up;
    private List<Integer> down;
    private List<Integer> left;
    private List<Integer> right;
    private int gameSave;
    private int interaction;

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction) {
        this(up, down, left, right);
        this.interaction = interaction;
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction,
            int gameSave) {
        this(up, down, left, right, interaction);
        this.gameSave = gameSave;
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
