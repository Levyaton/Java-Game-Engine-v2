package engineFiles.ui;

import java.util.ArrayList;

public class Controlls {
    private ArrayList<Integer> up;
    private ArrayList<Integer> down;
    private ArrayList<Integer> left;
    private ArrayList<Integer> right;

    public Controlls(ArrayList<Integer> up, ArrayList<Integer> down, ArrayList<Integer> left, ArrayList<Integer> right) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
    }


    //GETTERS

    public ArrayList<Integer> getUp() {
        return up;
    }

    public void setUp(ArrayList<Integer> up) {
        this.up = up;
    }

    public ArrayList<Integer> getDown() {
        return down;
    }

    public void setDown(ArrayList<Integer> down) {
        this.down = down;
    }

    //SETTERS

    public ArrayList<Integer> getLeft() {
        return left;
    }

    public void setLeft(ArrayList<Integer> left) {
        this.left = left;
    }

    public ArrayList<Integer> getRight() {
        return right;
    }

    public void setRight(ArrayList<Integer> right) {
        this.right = right;
    }
}
