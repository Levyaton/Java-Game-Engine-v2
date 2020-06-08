package engineFiles.ui.TileMapClasses;

import java.util.logging.Logger;

//Class containing tilemap export logic
public class Export {
   private static final Logger LOG = Logger.getLogger(Export.class.getName());

        String format;

    /**
     * @return String
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }
}
