package engineFiles.colorer.program;

import java.awt.*;

public class SpecialColorObj extends ColorObj {

    private String boarder;
    private String random;

    private Color chosenDark;
    private Color chosenLight;
    private Color chosenMid;
    private Color chosenRandom;


    SpecialColorObj(String light, String dark, String mid, String boarder, String random, String name) {
        super(light, dark, mid, name);
        this.boarder = boarder;
        this.random = random;
    }

    public void setChosen(Color light, Color dark, Color mid, Color random) {
        this.chosenDark = dark;
        this.chosenLight = light;
        this.chosenMid = mid;
        this.chosenRandom = random;
    }

    public Color getChosenDark() {
        return chosenDark;
    }

    public Color getChosenLight() {
        return chosenLight;
    }

    public Color getChosenMid() {
        return chosenMid;
    }

    public Color getChosenRandom() {
        return chosenRandom;
    }

    public String getBoarderHex() {
        return boarder;
    }

    public String getRandomHex() {
        return random;
    }

    public Color getRandomColor() {
        return Color.decode(random);
    }

    public Color getBoarderColor() {
        return Color.decode(boarder);
    }


    public Color getCorrespondingColor(Color input) {
        if (hexColorValue(input).equals(getRandomHex())) {
            return chosenRandom;
        } else if (hexColorValue(input).equals(getDarkHex())) {
            return chosenDark;
        } else if (hexColorValue(input).equals(getLightHex())) {
            return chosenLight;
        } else if (hexColorValue(input).equals(getMidHex())) {
            return chosenMid;
        }
        return input;
    }

    private String hexColorValue(Color input) {
        String hex = Integer.toHexString(input.getRGB());
        hex = "#" + hex.substring(2);
        return hex;
    }
}
