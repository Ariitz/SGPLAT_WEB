package test.User;

import Util.mongo.MongoCollecction;
import Util.mongo.MongoFinder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import Constants.DataBaseC;
import Constants.UserC;

public class FindUser {

    public static void main(String[] args) {
        try {
            MongoCollecction collection = MongoCollecction.getInstance(DataBaseC.COLLECTION_USER);
            System.out.println("Find example...");

            //Create object
            BasicDBObject document = new BasicDBObject();
            document.put(UserC.USER, "Nombre");
            document.put(UserC.PASS, "pass");
            document.put(UserC.TYPE, UserC.TYPE_ADMIN);

            //Find
            DBCursor cursor = MongoFinder.find(DataBaseC.COLLECTION_USER,document);

            //Results
            System.out.println(cursor);
            while(cursor.hasNext()){
                System.out.println(cursor.next());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
