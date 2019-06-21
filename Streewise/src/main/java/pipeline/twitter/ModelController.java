package pipeline.twitter;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import pipeline.twitter.Model;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.mongodb.MongoClient;

import java.util.Collection;

/**
 * Spring MongoDB configuration file
 *
 */
@Configuration
public class ModelController{
    private MongoTemplate mongoTemplate;

    public @Bean
    MongoTemplate mongoTemplate() throws Exception {

        MongoTemplate mongoTemplate =
                new MongoTemplate(new MongoClient("127.0.0.1"),"tweetData");
        return mongoTemplate;

    }


}
