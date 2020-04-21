package engineFiles.ui;

import java.util.ArrayList;
import java.util.Comparator;

public class SpriteCollection extends ArrayList<Sprite> {


    @Override
    public boolean add(Sprite sprite) {

        int size = this.size();
        int given = sprite.getCoord().getZ();
        for(int x = 0; x < this.size(); x++){
            for(Sprite s: this){
                int current = s.getCoord().getZ();
                if(given < current){
                   super.add(x, sprite);
                   return this.size() > size;
                }
            }
        }
        return super.add(sprite);
    }

    public void sort(){
        Comparator<Sprite> compareByZ = Sprite::compareTo;
        super.sort(compareByZ);

    }


}
