/*package il.co.ilrd.gatewayserver;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.HttpExchange;

public class SingletonCommandFactory{
	private static volatile SingletonCommandFactory instance;
	HashMap<Entry<String, String>, RequestHandler> map = new HashMap<>();
	
	private SingletonCommandFactory() {}
	
	public static SingletonCommandFactory getInstance() {
		instance = instance == null ? new SingletonCommandFactory(): instance;
		return instance;
	}
	
	public void add(Entry<String, String> key, RequestHandler r) { 
		map.put(key, r);
	}
	
		
	public ServerResponse Create(Entry<String, String> key,JsonObject parsedReq) throws IOException{
		if(!map.containsKey(key)) {
			return ServerResponse.BAD_REQUEST;
		}
		if(parsedReq.get("consumer_key") == null || parsedReq.get("consumer_secret") == null
			||parsedReq.get("access_token") == null || parsedReq.get("token_secret") == null) {
			return ServerResponse.MISSING_TOKENS;
		}
		
		else {
			return map.get(key).apply( parsedReq);
   		 }
	}
}

*/