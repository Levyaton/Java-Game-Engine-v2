package engineFiles.GUIs.worldEditGUI.models;

import com.google.gson.Gson;
import org.json.JSONObject;

public abstract class GsonModel {

    public JSONObject toJSONObject(){
        return new JSONObject(this.toJSONString());
    }
    public String toJSONString(){
        return new Gson().toJson(this);
    }



}
