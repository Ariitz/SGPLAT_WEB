package Model.especialista;

import Util.webservice.WSConsumer;
import com.google.gson.JsonObject;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class EspecialistaIndexMB implements Serializable {

    public static final String INDEX_PAGE = "/content/especialista/index.xhtml";

    private List<JSONObject> especialistas;

    @PostConstruct
    public void init(){
        especialistas = new ArrayList<JSONObject>();
    }


    public String prepareIndex(){   String redirect = INDEX_PAGE;
        especialistas.clear();

        try {
            //Consulta Web service
            String response =  WSConsumer.get("http://localhost:8080/rest/especialista");
            //Crea un objeto a partir de la respuesta
            JSONArray jsonArray = new JSONArray(response);

            for (int i=0; i< jsonArray.length(); i++) {
                especialistas.add( jsonArray.getJSONObject(i) );
            }
        } catch (Exception e) {
            System.out.println("error");
        }

        System.out.println(redirect);
        return redirect;



    }

    public List<JSONObject> getEspecialistas() {
        return especialistas;
    }

    public void setEspecialistas(List<JSONObject> especialistas) {
        this.especialistas = especialistas;
    }
}



/*
* public void download(String fileName) {
        System.out.println("Calling download...");
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

        try {
            MongoDatabase database = mongoClient.getDatabase("sgplat");
            GridFSBucket gridBucket = GridFSBuckets.create(database);

            FileOutputStream fileOutputStream = new FileOutputStream("/home/daniel/Descargas/aduio.mp3");
            gridBucket.downloadToStream(fileName, fileOutputStream);
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mongoClient.close();
        }
    }
*/