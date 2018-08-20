package com.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreeMarkerSparkStyle {
    public static void main(String[] args) {
        final Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerSparkStyle.class,"/");
        final MongoClient mongoClient = new MongoClient();
        final MongoDatabase db = mongoClient.getDatabase("course");
        final MongoCollection<Document> collection = db.getCollection("hello");
        collection.drop();

        collection.insertOne(new Document("name","mongodb"));
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    StringWriter stringWriter = new StringWriter();
                    /*Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "hello freemarker");*/
                    Document document=collection.find().first();
                    helloTemplate.process(document, stringWriter);
                    return stringWriter;
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
