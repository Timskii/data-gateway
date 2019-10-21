package kz.tg.data.es.processor;



import javafx.util.Duration;
import kz.tg.data.es.model.GraphQLQuery;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.List;


public class GatewayProcessor {

    private MongoTemplate mongoTemplate;

    public static void getData() {

        RestTemplate restTemplate = new RestTemplate();
        String query =
                "\n    query Topology($duration: Duration!) {\n      getGlobalTopology(duration: $duration) {\n        nodes {\n          id\n          name\n          type\n          isReal\n        }\n        calls {\n          id\n          source\n          target\n          callType\n          detectPoint\n        }\n      }\n    }\n  ";

        Duration duration = new Duration(121212D);

        GraphQLQuery topologyQuery = new GraphQLQuery();
        // Use a singletonMap to retain the object name
        topologyQuery.setVariables(Collections.singletonMap("duration", duration));
        topologyQuery.setQuery(query);


        //Object results = restTemplate.postForEntity("http://graph.facebook.com/facebook/picture?redirect=false", topologyQuery, Object.class);
        ResponseEntity<String> results = restTemplate.getForEntity("http://graph.facebook.com/facebook/picture?redirect=false", String.class);
        System.out.println(results.getBody());
    }

    public void parseDataAndSaveToFile(){
        Query query = new Query();
        query.addCriteria(Criteria.where("contentType").in("application/json"));
        query.fields().include("content");
        List<Binary> data = mongoTemplate.find(query, Binary.class);
        System.out.println("data = " + data.size());
    }
}
