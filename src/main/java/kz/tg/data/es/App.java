package kz.tg.data.es;


import kz.tg.data.es.model.WebPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public String parseDataAndSaveToFile(MongoTemplate mongoTemplate){
        Query query = new Query();
        query.addCriteria(Criteria.where("contentType").regex("json"));
        query.fields().include("content");
        List<WebPage> data = mongoTemplate.find(query, WebPage.class);
        System.out.println("data = " + data.size());
        data.forEach(webPage -> System.out.println(webPage.getContent()));
        return "";
    }
}
