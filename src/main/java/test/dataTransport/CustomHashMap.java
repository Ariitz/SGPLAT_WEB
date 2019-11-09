package test.dataTransport;


import java.io.Serializable;
import java.util.HashMap;

import org.primefaces.json.JSONObject;

public class CustomHashMap<K, V> extends HashMap<K, V> implements Serializable {

    JSONObject jsonObject;

    public CustomHashMap() {
        jsonObject = new JSONObject();
    }

    public <T>T getComplex(String key, Class<T> v){
        return v.cast(jsonObject.get(key));
    }
}

