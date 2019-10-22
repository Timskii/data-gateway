package kz.tg.data.es.processor;


import kz.tg.data.es.model.WebPage;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;


public class GatewayProcessor {

    public static void parseDataAndSaveToFile(MongoTemplate mongoTemplate){
        Query query = new Query();
        query.addCriteria(Criteria.where("contentType").in("application/json"));
        query.fields()
                .include("content")
                .include("baseUrl");
        List<WebPage> data = mongoTemplate.find(query, WebPage.class);
        System.out.println("data = " + data.size());
        data.forEach(
                webPage -> {
                    String fileName = "";
                    fileName = webPage.getBaseUrl().replace("https://","").replace("/","_");
                    File file = new File("/home/tim/Documents/" + fileName);
                    try {
                        if (!file.createNewFile()){
                            System.out.println("File already exists.");
                        }
                        FileWriter writer = new FileWriter(file);
                        writer.write(webPage.getContent());
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}
