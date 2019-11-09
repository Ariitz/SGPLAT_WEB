package Util.mongo;

import com.mongodb.MongoClient;
import Constants.DataBaseC;

import java.net.UnknownHostException;

public class MongoConection {
    private static MongoConection mongoConection;
    private static MongoClient mongoClient;


    MongoConection() {
        try {
            mongoClient = new MongoClient(DataBaseC.HOST, DataBaseC.IP); // should use this always
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static MongoConection getInstance() {
        mongoConection = new MongoConection();
        return mongoConection;
    }

    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    public static void setMongoClient(MongoClient mongoClient) {
        MongoConection.mongoClient = mongoClient;
    }
}
