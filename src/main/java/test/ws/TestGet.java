package test.ws;

import Util.webservice.WSConsumer;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestGet {
    public static void main(String[] args) {
        try {
            //MyGETRequest();
            JSONArray jsonObject = new JSONArray(WSConsumer.get("https://jsonplaceholder.typicode.com/comments?postId=1"));
            System.out.println(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void MyGETRequest() throws IOException {
        URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/comments?postId=1");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        conection.setRequestProperty("postId", "1"); // set userId its a sample here
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in.readLine()) != null) {
                response.append(readLine);
            }
            in.close();
            // print result
            System.out.println("JSON String Result " + response.toString());
            JSONArray jsonObject = new JSONArray(response.toString());
            System.out.println(jsonObject);
            //GetAndPost.POSTRequest(response.toString());
        } else {
            System.out.println("GET NOT WORKED");
        }
    }

}
