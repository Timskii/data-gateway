package kz.tg.data.es;


import kz.tg.data.es.processor.GatewayProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public String parseDataAndSaveToFile(MongoTemplate mongoTemplate){
        GatewayProcessor.parseDataAndSaveToFile(mongoTemplate);
        return "";
    }
}
