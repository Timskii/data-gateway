package kz.tg.data.es.util;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;

public class Utils {
    public static ObjectMapper mapper = new ObjectMapper();



    public static ObjectWriter json = mapper.writerWithDefaultPrettyPrinter();


    public static String toJson(Object object) throws JsonProcessingException {
        return json.writeValueAsString(object);
    }

    public static String toJsonRaw(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public static String toJsonNonNull(Object object) throws JsonProcessingException {
        return mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    public static String toJsonNonNullRaw(Object object) throws JsonProcessingException {
        return mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL).writeValueAsString(object);
    }

    @SuppressWarnings("unchecked")
    public static Object fromJson(String jsonString, Class clazz) throws IOException {
        return mapper.readValue(jsonString, clazz);
    }
}

