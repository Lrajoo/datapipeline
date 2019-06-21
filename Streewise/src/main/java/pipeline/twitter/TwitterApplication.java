package pipeline.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(TwitterApplication.class, args);
        streamData data =  new streamData();
        data.twitterAPI("Apple");

    }



}