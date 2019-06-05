package il.co.ilrd.gatewayserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

public class GateWayServer {
	private static final int CORE_POOL_SIZE = 1;
	private static final int MAX_POOL_SIZE = 10;
	private static final long KEEP_ALIVE = 5;
	private static final int QUEUE_CAPACITY = 100;
	
	private HttpServer httpServer;
	private final ThreadPoolExecutor threadPool;
	private final JsonObject response = new JsonObject();
	
	
    public GateWayServer(String ip, int port){
    	threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE,
    			MAX_POOL_SIZE,
    			KEEP_ALIVE,
    			TimeUnit.SECONDS,
    			new ArrayBlockingQueue<>(QUEUE_CAPACITY));
    	
    	 serverInit(ip, port);
    	 singletonInit();
    }
    
  /*  public static void displayMassage(ServerResponse r, HttpExchange exchange){
		
		OutputStream returnedMessageStream = exchange.getResponseBody(); 
		try {
			exchange.sendResponseHeaders(r.code, r.msg.length());
			returnedMessageStream.write(r.msg.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				returnedMessageStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
    */
    private void serverInit(String ip, int port) {
    	
    		try {
				httpServer = HttpServer.create(new InetSocketAddress(ip, port), 0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    		
    		httpServer.createContext("/ori", (exchange)->{
        	 threadPool.execute(()->{
        		 JsonObject parsedReq = (JsonObject) (new JsonParser().parse(new BufferedReader(new InputStreamReader(exchange.getRequestBody()))));
        		 SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<String, String>(
					exchange.getRequestMethod(),commandAsString(parsedReq));
					try {
						JsonObject jsonResponse = buildResponse(SingletonCommandFactory.getInstance().Create(entry, parsedReq));
						
						sendResponse(exchange, jsonResponse);
					} catch (IOException e) {
						e.printStackTrace();
					}
        	 ;});
         });
    }
    
    private void singletonInit() {
    	 SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<String, String>("POST" ,"tweet");
    	 SingletonCommandFactory.getInstance().add(entry, new PostHandler()::apply);
	}
    
    public void start() {
    	 httpServer.start();
    }
    
    private String commandAsString(JsonObject parsedReq) {
    	return parsedReq.get("command_type") == null ? "" : parsedReq.get("command_type").getAsString(); 
    	
    }
    
    private void sendResponse(HttpExchange request, JsonObject jsonResponse) throws IOException {
		request.sendResponseHeaders(jsonResponse.get("status").getAsInt(),
									(long)jsonResponse.toString().length());;
		OutputStream stream = request.getResponseBody();
		stream.write(jsonResponse.toString().getBytes());
		stream.close();
	}

	private JsonObject buildResponse(ServerResponse status) {
		response.addProperty("status", status.getCode());
		response.addProperty("description", status.getMsg());
		return response;
	}
	
	public static class SingletonCommandFactory{
		private static volatile SingletonCommandFactory instance;
		HashMap<Entry<String, String>, RequestHandler> map = new HashMap<>();
		
		private SingletonCommandFactory() {}
		
		private static SingletonCommandFactory getInstance() {
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
}
