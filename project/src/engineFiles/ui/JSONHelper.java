package engineFiles.ui;

import org.json.JSONObject;

public class JSONHelper {

    public static int getIntParameter(JSONObject json, String key){
        return Integer.decode(json.get(key).toString());
    }





}
