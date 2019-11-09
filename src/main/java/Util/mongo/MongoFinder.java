package Util.mongo;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.List;

public class MongoFinder {

    public static DBCursor find(String collectionName, DBObject query){
        MongoCollecction collection = MongoCollecction.getInstance(collectionName);
        return collection.getCollection().find(query);
    }
    public static DBCursor find(DBCollection collection, DBObject query){
        return collection.find(query);
    }

    public static DBCursor find(MongoCollecction collection, DBObject query){
        return collection.getCollection().find(query);
    }

    public static List<DBObject> findAll(MongoCollecction collection){
        return collection.getCollection().find().toArray();
    }
}
