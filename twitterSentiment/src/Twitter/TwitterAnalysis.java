package Twitter;

import twitter4j.*;
import twitter4j.conf.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

public class TwitterAnalysis {

    
    @SuppressWarnings({ "deprecation", "static-access" })
	public List<Status> TweetsRetrival(String word, String place){
    	

        ConfigurationBuilder cb = new ConfigurationBuilder();
//        cb.setIncludeRetweetsEnabled(false);
  	  cb.setDebugEnabled(true)
  	    .setOAuthConsumerKey("......")
  	    .setOAuthConsumerSecret(".....")
  	    .setOAuthAccessToken(".....")
  	    .setOAuthAccessTokenSecret(".....");
  	  TwitterFactory tf = new TwitterFactory(cb.build());
  	  Twitter twitter = tf.getInstance();
  	  double lat = 55.32443216228089;   // a search with no correct place given will return tweets from Ireland. 
  	  double lon = -9.602050778750026;
  	  int radius = 350;    // the radius of a country; the radius of a city will be 20 km
        try {
        	MapCreation maps = new MapCreation();
        	if(maps.countries.get(place) != null){
    			String l = "";
    			String pl = maps.countries.get(place);
    			int i=0;
       			for (i = 0; i < pl.length(); i++) {
       				if (pl.charAt(i) == '\t') {
       					break;
       				}
       				l = l + pl.charAt(i);
       			}
       			lat = Double.parseDouble(l);
       			lon = Double.parseDouble(pl.substring(i + 1, pl.length()));
       		}
        	else if (maps.cities.get(place) != null){ 
        		radius = 20; 
        		String l = "";
        		String pl = maps.cities.get(place);
				int i=0;	
        		for (i = 0; i < pl.length(); i++) {
       					if (pl.charAt(i) == '\t') {
       						break;
       					}
       					l = l + pl.charAt(i);
       				}
       				lat = Double.parseDouble(l);
       				lon = Double.parseDouble(pl.substring(i + 1, pl.length()));
        		}
            Query query = new Query(word);
            query.setCount(500);
            query.setResultType(query.RECENT);  //  - retrieves recent / popular tweets 
            query.setLang("en");
            query.setGeoCode(new GeoLocation(lat, lon), radius, "km");  // coordinates for Ireland
            QueryResult result;
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
            
            return  tweets; 
            
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Your search encountered the following error: " + te.getMessage());
            System.exit(-1);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
        
    }
}