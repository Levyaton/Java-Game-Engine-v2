package engineFiles.ui;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.Comparator;

public class SpriteCollection extends ArrayList<Sprite> {

    public SpriteCollection(JSONArray sprites){
        for (Object json: sprites) {
            Sprite s = new Sprite((JSONObject) json);
            add(s);
        }
    }

    public SpriteCollection(ArrayList<Sprite> arr){
        addAll(arr);
    }

    public SpriteCollection(){

    }

    @Override
    public boolean add(Sprite sprite) {

        int size = this.size();
        int given = sprite.getCoord().getZ();
        for (int x = 0; x < this.size(); x++) {
            for (Sprite s : this) {
                int current = s.getCoord().getZ();
                if (given < current) {
                    super.add(x, sprite);
                    return this.size() > size;
                }
            }
        }
        return super.add(sprite);
    }

    public void sort() {
        Comparator<Sprite> compareByZ = Sprite::compareTo;
        super.sort(compareByZ);

    }

    public JSONArray toJSONArray(){
        JSONArray arr = new JSONArray();
        for (Sprite s:this) {
            arr.put(s.getJSON());
        }
        return arr;
    }



}
