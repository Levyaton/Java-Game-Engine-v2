package engineFiles.main.models.Sprites;


import engineFiles.ui.Settings;

import java.util.ArrayList;
import java.util.List;

public class Controlls {
    private List<Integer> up;
    private List<Integer> down;
    private List<Integer> left;
    private List<Integer> right;
    private int gameSave = Settings.controlls.gameSave;
    private int interaction;


    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction) {
        this(up,down,left,right);
        this.interaction = interaction;
    }

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right, int interaction, int gameSave) {
        this(up,down,left,right,interaction);
        this.gameSave = gameSave;
    }


    //GETTERS

    public List<Integer> getUp() {
        return up;
    }

    public void setUp(List<Integer> up) {
        this.up = up;
    }

    public List<Integer> getDown() {
        return down;
    }

    public void setDown(List<Integer> down) {
        this.down = down;
    }

    //SETTERS

    public List<Integer> getLeft() {
        return left;
    }

    public void List(ArrayList<Integer> left) {
        this.left = left;
    }

    public List<Integer> getRight() {
        return right;
    }

    public void setRight(ArrayList<Integer> right) {
        this.right = right;
    }

    public int getInteraction() {
        return interaction;
    }

    public void setInteraction(int interaction) {
        this.interaction = interaction;
    }

    public int getGameSave() {
        return gameSave;
    }
}
