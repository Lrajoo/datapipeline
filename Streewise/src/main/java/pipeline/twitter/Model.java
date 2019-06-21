package pipeline.twitter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweets")
public class Model {
    @Id
    public String id;
    public String screename;
    public String location;
    public String description;
    public String content;


    public Model(String screename,
                 String location,
                 String description,
                 String content){
        this.screename = screename;
        this.location = location;
        this.description = description;
        this.content = content;
    }
}
