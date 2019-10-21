package kz.tg.data.es.model;


import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "webpage")
public class WebPage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;

    public WebPage(
            String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
