package engineFiles.colorer.program;

import java.util.Dictionary;
import java.util.Hashtable;

public class ColorManager {

    private Dictionary<String, String> specialColors = new Hashtable();
    private Dictionary<String, Dictionary<String, String>> colors = new Hashtable();

    ColorManager(){
        specialColorsInit();
        colorsInit();
    }

    private void colorsInit(){
        generateBlack();
        generateBrown();
        generateGreen();
        generatePink();
        generatePurple();
        generateYellow();
        generateWhite();
        genertateBlue();
        generateRed();
    }
    private void specialColorsInit(){
        String random = "ff0000";
        String midColor = "b7b7b7";
        String darkColor = "555555";
        String border = "000000";
        String lightColor = "ffffff";
    }

    private void generateRed(){
        String light = "#ffcccb";
        String dark = "#8B0000";
        String mid = "#FF0000";
        String name = "red";
        generateColor(light,mid, dark,name);
    }

    private void genertateBlue(){
        String light = "#add8e6";
        String dark = "#00008b";
        String mid = "#0000ff";
        String name = "blue";
        generateColor(light,mid, dark,name);
    }

    private void generateGreen(){
        String light = "#90ee90";
        String dark = "#013220";
        String mid = "#008000";
        String name = "green";
        generateColor(light,mid, dark,name);
    }

    private void generateYellow(){
        String light = "#ffffed";
        String mid = "#ffff00";
        String dark = "#9B870C";
        String name = "yellow";
        generateColor(light,mid, dark,name);
    }

    private void generatePurple(){
        String light = "#b19cd9";
        String mid = "#800080";
        String dark = "#301934";
        String name = "purple";
        generateColor(light,mid, dark,name);
    }

    private void generatePink(){
        String light = "#FFB6C1";
        String mid = "#FFC0CB";
        String dark = "#e75480";
        String name = "pink";
        generateColor(light,mid, dark,name);
    }

    private void generateBrown(){
        String light = "#b5651d";
        String mid = "#A52A2A";
        String dark = "#654321";
        String name = "brown";
        generateColor(light,mid, dark,name);
    }

    private void generateWhite(){
        String light = "ffffff";
        String mid = "#e5e5e5";
        String dark = "#cccccc";
        String name = "white";
        generateColor(light,mid, dark,name);
    }

    private void generateBlack(){
        String light = "#323232";
        String mid = "#191919";
        String dark = "000000";
        String name = "black";
        generateColor(light,mid, dark,name);
    }

    private void generateColor(String light, String mid, String dark, String colorName){
        Dictionary<String, String> colors = new Hashtable<>();
        colors.put("light",light);
        colors.put("mid",mid);
        colors.put("dark",dark);
        this.colors.put(colorName,colors);
    }


}
