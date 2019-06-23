package pipeline.twitter;

import org.apache.flink.shaded.akka.org.uncommons.maths.statistics.DataSet;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;
import java.io.FileWriter;
import java.io.IOException;

public class streamData {
        public String location;
        public String content;

        public String getLocation(){
                return location;
        }

        public String getContent(){
                return content;
        }

        public void twitterAPI(String keyword){
                ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true);
                cb.setOAuthConsumerKey("***");
                cb.setOAuthConsumerSecret("***");
                cb.setOAuthAccessToken("***");
                cb.setOAuthAccessTokenSecret("***");
                TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

                StatusListener listener = new StatusListener() {
                        @Override
                        public void onException(Exception ex) {

                        }

                        public void onStatus(Status status) {
                                User user = status.getUser();
                                String screename = user.getScreenName();
                                String profileLocation = user.getLocation();
                                String description = user.getDescription();
                                String content = status.getText();
                                app Entry = new app();
                                Entry.addtoDatabase(screename, profileLocation, description, content);
                                wordInput Word = new wordInput();
                                try {
									Word.storing(profileLocation);
								} catch (IOException e) {
									e.printStackTrace();
								}
                        }


                        @Override
                        public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

                        }

                        @Override
                        public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

                        }

                        @Override
                        public void onScrubGeo(long userId, long upToStatusId) {

                        }

                        @Override
                        public void onStallWarning(StallWarning warning) {

                        }
                };
                FilterQuery fq = new FilterQuery();
                String keywords[] = {keyword};
                fq.track(keywords);
                twitterStream.addListener(listener);
                twitterStream.filter(fq);

        }
}

