package test.dataTransport;

import org.primefaces.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ObjectData {
    public static void main(String[] args) {

        Map<String, String> crunchifyMap = new HashMap<String, String>();
        crunchifyMap.put("Google", "San Jose");
        crunchifyMap.put("Facebook", "Mountain View");
        crunchifyMap.put("Crunchify", "NYC");
        crunchifyMap.put("Twitter", "SFO");
        crunchifyMap.put("Microsoft", "Seattle");
        System.out.println("Raw Map ===> " + crunchifyMap);

        // Construct a JSONObject from a Map.
        JSONObject crunchifyObject = new JSONObject(crunchifyMap);
        System.out.println(crunchifyObject);

        System.out.println(
                crunchifyObject.getString("Google")
        );
        System.out.println(crunchifyObject);

    }
}
