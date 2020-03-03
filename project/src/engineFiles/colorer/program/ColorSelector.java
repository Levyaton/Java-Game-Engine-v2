package engineFiles.colorer.program;

import java.awt.*;
import java.util.Dictionary;
import java.util.Hashtable;

public class ColorSelector {
    private Dictionary specialColors = new Hashtable();
    private Dictionary colors = new Hashtable();

    ColorSelector(){
        colorsInit();
    }
    private void specialColorsInit(){
        String random = "ff0000";
        String midColor = "b7b7b7";
        String darkColor = "555555";
        String border = "000000";
        String lightColor = "ffffff";
    }
}
