package kz.tg.data.es.model;


import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "webpage")
public class WebPage implements Serializable {
    private static final long serialVersionUID = 1L;
    private String content;
    private String baseUrl;

    public WebPage(
            String content,
            String baseUrl) {
        super();
        this.content = content;
        this.baseUrl = baseUrl;
    }

    public String getContent() {
        return content;
    }

    public String getBaseUrl(){
        return baseUrl;
    }
}
