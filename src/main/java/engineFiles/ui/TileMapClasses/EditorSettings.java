package engineFiles.ui.TileMapClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing tilemap settings
public class EditorSettings {
    private static final Logger LOG = Logger.getLogger(EditorSettings.class.getName());
    Export export;

    /**
     * @return Export
     */
    public Export getExport() {
        return export;
    }

    /**
     * @param export
     */
    public void setExport(Export export) {
        this.export = export;
    }

    /**
     * @param export
     */
    public void setExport(JSONObject export) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        this.export = gson.fromJson(gson.toString(), Export.class);
        LOG.info("Export set");
    }
}
