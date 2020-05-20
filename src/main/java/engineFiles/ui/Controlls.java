package engineFiles.ui;


import java.util.ArrayList;
import java.util.List;

public class Controlls {
    private List<Integer> up;
    private List<Integer> down;
    private List<Integer> left;
    private List<Integer> right;

    public Controlls(List<Integer> up, List<Integer> down, List<Integer> left, List<Integer> right) {
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
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
}
