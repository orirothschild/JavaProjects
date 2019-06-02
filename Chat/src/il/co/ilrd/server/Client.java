package il.co.ilrd.server;
import java.nio.channels.*;
import java.util.function.Function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.*;

public class Client{
	
	private final SocketChannel client;
	private String username = null;
	private final BufferedReader servermsg;
    private volatile boolean flag = true;
    private Function<String,Void> func = null;
    
    public Client(String name, Function<String,Void> func) throws IOException {
    	client = SocketChannel.open(new InetSocketAddress(Server.IP, Server.PORT));
    	servermsg = new BufferedReader(new InputStreamReader(client.socket().getInputStream()));
    	username = name;
		this.func = func;
		readMassage();
       
     }
     
     public Client(String name, String address, int port , Function<String,Void> func) throws IOException {
    	 client = SocketChannel.open(new InetSocketAddress(address, port));
    	 servermsg = new BufferedReader(new InputStreamReader(client.socket().getInputStream()));
    	 this.func = func;
    	 username = name;
         readMassage();
         
     }
     
     private void readMassage() {
    	 new Thread( ()-> {
    		 while(flag) {
					try {
						String msg = servermsg.readLine();
						if(null == msg) {
//							System.out.println("server is off");
							disconnectClient();
						}
						switch(msg) {
						case "goodbye":
							disconnectClient();
							break;
						default:
							func.apply(msg);
							break;
						}
						
				} catch (IOException e) {
					System.out.println((username + " has disconnected "));
				}
		}
    	 }).start();
     }
     
     private void disconnectClient(){
    	 String msg = new String(username + "has disconnected");
    	 try {
			client.write(ByteBuffer.wrap(msg.getBytes()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	 	flag = false;
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
     }
     
     private void writeMassage(String massage) {
    	 try {
			client.write(ByteBuffer.wrap(massage.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
     }
     
     public void sendMassage(String massage) {
    	 if(massage.equals(Server.DISCONNECT) || massage.endsWith(Server.DISCONNECT)) {
    		disconnectClient();
			return;
    	 }
    	 
    	 writeMassage(username + ":" + massage + "\n");
     }
    
    public static void main(String [] args) throws IOException {
    	System.out.println("enter your userName");
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	Client client = new Client(reader.readLine(), (S)->{ System.out.println(S);return null;});
    	
    	while(client.flag) {
    		client.sendMassage(">" +reader.readLine());
    	}
    }
/*//	private ByteBuffer WritingBuffer = ByteBuffer.allocate(Server.BUFFER_SIZE); 
//	private ByteBuffer msgFromServer = ByteBuffer.allocate(Server.BUFFER_SIZE);
 * client.read(msgFromServer);
					func.apply(new String(msgFromServer.array(), "UTF-8"));
					msgFromServer = ByteBuffer.allocate(Server.BUFFER_SIZE);*/
}