package engineFiles.colorer.program;

import java.awt.image.ColorModel;

public class Colorer_2_Model extends ColorModel {

    int a;
    int r;
    int g;
    int b;

    public Colorer_2_Model(int bits, int r, int g, int b, int a) {
        super(bits);
        this.a = a;
        this.b = b;
        this.r = r;
        this.g = g;
    }

    @Override
    public int getRed(int pixel) {
        return a;
    }

    @Override
    public int getGreen(int pixel) {
        return g;
    }

    @Override
    public int getBlue(int pixel) {
        return b;
    }

    @Override
    public int getAlpha(int pixel) {
        return a;
    }
}
