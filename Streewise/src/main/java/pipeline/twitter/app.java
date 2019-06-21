package pipeline.twitter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import pipeline.twitter.ModelController;
import pipeline.twitter.Model;

public class app {
    public String screename;
    public String location;
    public String description;
    public String content;

    public void addtoDatabase(String screename, String location, String description, String content) {
        // For Annotation
        ApplicationContext ctx =
                new AnnotationConfigApplicationContext(ModelController.class);
        MongoOperations mongoOperation =
                (MongoOperations) ctx.getBean("mongoTemplate");

        Model add = new Model(screename, location, description, content);
        mongoOperation.save(add);
    }

}