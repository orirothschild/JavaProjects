package il.co.ilrd.gatewayserver;


import com.google.gson.JsonObject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class PostHandler implements RequestHandler {

    @Override
    public ServerResponse apply( JsonObject jsonRequest) {
    	//JsonObject jData = jsonRequest.getAsJsonObject("data");
    	
        ConfigurationBuilder cb = new ConfigurationBuilder();
       cb.setDebugEnabled(true)
         .setOAuthConsumerKey(jsonRequest.get("consumer_key").getAsString())
         .setOAuthConsumerSecret(jsonRequest.get("consumer_secret").getAsString())
         .setOAuthAccessToken(jsonRequest.get("access_token").getAsString())
         .setOAuthAccessTokenSecret(jsonRequest.get("token_secret").getAsString());
       
       TwitterFactory tf = new TwitterFactory(cb.build());
       Twitter twitter = tf.getInstance();
       try {
            twitter.updateStatus(jsonRequest.get("status").getAsString());
           // GateWayServer.displayMassage(ResponseHandler.OK, exchange);
            return ServerResponse.OK;
        } catch (TwitterException e) {
            e.printStackTrace();
            ServerResponse.GENERIC_RESPONSE.setResponse(e.getErrorMessage(),e.getErrorCode());
            return ServerResponse.GENERIC_RESPONSE;
        }
    }

}
