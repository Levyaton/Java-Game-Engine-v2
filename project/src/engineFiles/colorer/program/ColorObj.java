package engineFiles.colorer.program;

import java.awt.*;
import java.util.Random;

public class ColorObj {
    private String light;
    private String dark;
    private String mid;
    private String name;



    ColorObj(String light, String dark, String mid, String name){
        this.light = light;
        this.dark = dark;
        this.mid = mid;
        this.name = name;
    }

    public String getLightHex() {
        return light;
    }

    public String getDarkHex() {
        return dark;
    }

    public String getMidHex() {
        return mid;
    }

    public String getRandomShadeHex(){
        int choice = new Random().nextInt(3);
        switch (choice){
            case 0:
                return light;
            case 1:
                return dark;
            default:
                return mid;
        }

    }

    public String getName() {
        return name;
    }

    public Color getLightColor(){
        return Color.decode(light);
    }

    public Color getDarkColor(){
        return Color.decode(dark);
    }

    public Color getMidColor(){
        return Color.decode(mid);
    }

    public Color getRandomShade(){
        return Color.decode(this.getRandomShadeHex());
    }
}
