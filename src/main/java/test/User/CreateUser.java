package test.User;

import Util.mongo.MongoCollecction;
import com.mongodb.BasicDBObject;
import Constants.DataBaseC;
import Constants.UserC;

public class CreateUser {

    public static void main(String[] args) {
        try {
            MongoCollecction collection = MongoCollecction.getInstance(DataBaseC.COLLECTION_USER);
            System.out.println("BasicDBObject example...");
            BasicDBObject document = new BasicDBObject();
            document.put(UserC.USER, "");
            document.put(UserC.PASS, "");
            document.put(UserC.TYPE, UserC.TYPE_ADMIN);
            collection.insert(document);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
