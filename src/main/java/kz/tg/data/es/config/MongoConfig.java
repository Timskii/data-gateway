package kz.tg.data.es.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.bson.types.Binary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.ZoneId.systemDefault;
import static java.time.ZonedDateTime.ofInstant;

/**
 * <h3>Config</h3>
 * Simple mongodb config
 *
 * @author tim
 * @see MongoTemplate
 */

@Configuration
public class MongoConfig {

    @Bean
    public MongoClient mongo() {
        //return new MongoClient("mongo.dev.vat.tdcis.kz",4500);
        //TODO: вынести в настройки
        return new MongoClient(
                new ServerAddress("localhost",27017)
                , MongoCredential.createCredential("test","mytestdatabase", "test".toCharArray())
                , MongoClientOptions.builder().serverSelectionTimeout(30000).build()
        );
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongo(),"mytestdatabase");
        MappingMongoConverter mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
        mongoMapping.setCustomConversions(customConversions()); // tell mongodb to use the custom converters
        mongoMapping.afterPropertiesSet();
        return mongoTemplate;
    }

    public MongoCustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new BinaryToStringConverter());
        return new MongoCustomConversions(converters);
    }

    private static class BinaryToStringConverter implements Converter<Binary, String> {
        @Override
        public String convert(Binary source) {
            return new String (source.getData());
        }
    }


  /*  public MongoCustomConversions customConversions() {
         List<Converter<?, ?>> converters = new ArrayList<>();
         converters.add(new BigDecimalToDoubleConverter());
         converters.add(new DateToZonedDateTimeConverter());
         converters.add(new ZonedDateTimeToDateConverter());
        return new MongoCustomConversions(converters);
    }

    private static class BigDecimalToDoubleConverter implements Converter<BigDecimal, Double> {
        @Override
        public Double convert(BigDecimal source) {
            return source.doubleValue();
        }
    }

    private static class DateToZonedDateTimeConverter implements Converter<Date, ZonedDateTime> {
        @Override
        public ZonedDateTime convert(Date source) {
            return source == null ? null : ofInstant(source.toInstant(), systemDefault());
        }
    }

    private static class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {
        @Override
        public Date convert(ZonedDateTime source) {
            return source == null ? null : Date.from(source.toInstant());
        }
    }*/

}
