//package il.co.ilrd.gatewayserver;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.InetSocketAddress;
//import java.util.AbstractMap;
//import java.util.AbstractMap.SimpleEntry;
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Consumer;
//
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.sun.net.httpserver.HttpExchange;
//import com.sun.net.httpserver.HttpServer;
//
//public class JavaHttpWrapper{
//	private HttpServer httpServer;
//	private final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 100, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
//	private SingletonCommandFactory singletonCommand;
//	private Consumer<HttpExchange> consumer;    //works only for the generic 
//	
//	public JavaHttpWrapper(Consumer<HttpExchange> consumer){
//		this.consumer = consumer;
//		try {
//			httpServer = HttpServer.create(new InetSocketAddress(GateWayServer.IP, GateWayServer.PORT), 0);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		serverInit();
//		singletonInit();
//	}
//    
//    private void serverInit() {
//		httpServer.createContext("/ori", (exchange)->{
//    	 JsonObject parsedReq = (JsonObject) (new JsonParser().parse(new BufferedReader(new InputStreamReader(exchange.getRequestBody()))));
//    	 
//    	 threadPool.execute(()->{
//    		 SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<String, String>(
//				exchange.getRequestMethod(), parsedReq.get("command_type").getAsString());
//				try {
//					singletonCommand.Create(entry, exchange, parsedReq);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//    	 ;});
//     });
//    }
//    
//    private void singletonInit() {
//    	singletonCommand = SingletonCommandFactory.getInstance();
//    	 SimpleEntry<String, String> entry = new AbstractMap.SimpleEntry<String, String>("POST" ,"tweet");
//    	 singletonCommand.add(entry, new PostHandler()::apply);
//	}
//    
//	
//	public void validate(HttpExchange exchange) {
//		consumer.accept(exchange);
//		}
//	}
