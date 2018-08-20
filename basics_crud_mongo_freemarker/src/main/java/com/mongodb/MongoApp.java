package com.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Map;

import static com.mongodb.client.model.Filters.eq;

public class MongoApp {

    public static void main(String[] args) {
       /* MongoClient mongoClient=new MongoClient();
        // Defaults to local host, use different constructors for mapping to specific host*/

        MongoClientOptions mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(500).build();
        MongoClient mongoClient = new MongoClient(new ServerAddress(), mongoClientOptions);
        final MongoDatabase db = mongoClient.getDatabase("varun_db");
        final MongoCollection<Document> person = db.getCollection("person");
        for (Document doc : person.find()) {
            printDoc(doc);
        }
        person.drop();
        for(int i=0;i<10;i++) {
            Document smith = new Document("name", "smith"+i)
                    .append("age", 30)
                    .append("profession", "programmer"+i);
            person.insertOne(smith);
        }
        final Document document = person.find().first();
        printDoc(document);

        final ArrayList<Document> into = person.find().into(new ArrayList<Document>());
        for (Document doc : into)
            printDoc(doc);

        final MongoCursor<Document> mongoCursor = person.find().iterator();
        try {
            while (mongoCursor.hasNext())
                printDoc(mongoCursor.next());
        } finally {
            mongoCursor.close();
        }

        System.out.println("total count is "+ person.count());

        System.out.println("filter");
        Bson filterDoc=new Document("name","smith9");
        final FindIterable<Document> filter = person.find().filter(filterDoc);
        for(Document doc:filter)
            printDoc(doc);
        Bson filterDoc2=eq("name","smith8");
        final FindIterable<Document> filter2 = person.find().filter(filterDoc2);
        for(Document doc:filter2)
            printDoc(doc);

    }

    private static void printDoc(Document doc) {
        for (Map.Entry entry : doc.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}
