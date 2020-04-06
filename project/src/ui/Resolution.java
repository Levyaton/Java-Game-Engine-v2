package ui;

public class Resolution {

    private boolean isValid;
    private int width;
    private int height;
    private String resolutionString;

    public Resolution(int width, int height){
        this.width = width;
        this.height = height;
        this.isValid = true;
        this.resolutionString = this.width + "x" + this.height;
    }
    public Resolution(String resolution){
        this.resolutionString = resolution;
        try{
            String[] parts = resolution.split("x");
            this.width = Integer.getInteger(parts[0]);
            this.height = Integer.getInteger(parts[1]);
            isValid = true;
        }
        catch (Exception e){
            e.printStackTrace();
            isValid = false;
        }
    }

    public String getResolutionString(){
        return this.resolutionString;
    }

    public boolean isValid() {
        return isValid;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
