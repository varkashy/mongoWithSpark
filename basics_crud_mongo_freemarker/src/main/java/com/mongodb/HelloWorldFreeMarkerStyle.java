package com.mongodb;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class HelloWorldFreeMarkerStyle {
    public static void main(String[] args) {
        Configuration configuration=new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreeMarkerStyle.class,"/");
        try {
            Template helloTemplate=configuration.getTemplate("hello.ftl");
            StringWriter stringWriter=new StringWriter();
            Map<String,Object> helloMap=new HashMap<>();
            helloMap.put("name","hello freemarker");
            helloTemplate.process(helloMap,stringWriter);
            System.out.println(stringWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
