package com.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class HomeWork3 {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        final MongoDatabase db = mongoClient.getDatabase("students");
        final MongoCollection<Document> collection = db.getCollection("grades");
        Bson filterDoc=new Document("type","homework");
        Bson sortDoc=new Document("student_id",1).append("score",1);
        FindIterable<Document> documents = collection.find().filter(filterDoc).sort(sortDoc);
        int count=0;
        for(Document doc:documents) {
           // printDoc(doc);
            if(count==0){
                count++;
                //System.out.println(doc.get("_id"));
                Bson deleteDoc=new Document("_id",doc.get("_id"));
                collection.deleteOne(deleteDoc);
            }
            else
                count=0;
        }
        System.out.println(collection.count());

    }

    private static void printDoc(Document doc) {
        for (Map.Entry entry : doc.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
