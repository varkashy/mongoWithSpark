package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;
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
        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                try {
                    Template helloTemplate = configuration.getTemplate("hello.ftl");
                    StringWriter stringWriter = new StringWriter();
                    Map<String, Object> helloMap = new HashMap<>();
                    helloMap.put("name", "hello freemarker");
                    helloTemplate.process(helloMap, stringWriter);
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
