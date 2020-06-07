package engineFiles.ui.TileMapClasses;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;


//Class containing tilemap settings
public class EditorSettings {
    Export export;



    public Export getExport() {
        return export;
    }

    public void setExport(Export export) {
        this.export = export;
    }

    public void setExport(JSONObject export) {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        this.export = gson.fromJson(gson.toString(), Export.class);
    }
}
