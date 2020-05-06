package semestralka.map;

import java.io.File;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONLoader {
  public JSONObject load(String path) {
    try {
      JSONObject jsonObject = (JSONObject) new JSONParser()
          .parse(new FileReader(new File(JSONLoader.class.getResource(path).toURI())));
      return jsonObject;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Couldnt not load json at path: " + path);
      return null;
    }
  }
}