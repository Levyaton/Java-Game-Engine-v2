package engineFiles.ui.TileMapClasses;

//Class containing tileset logic
public class Tileset {
    int firstgid;
    String source;

    /**
     * @return int
     */
    public int getFirstgid() {
        return firstgid;
    }

    /**
     * @return String
     */
    public String getSource() {
        return source;
    }

    /**
     * @param firstgid
     */
    public void setFirstgid(int firstgid) {
        this.firstgid = firstgid;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }
}
