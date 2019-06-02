package il.co.ilrd.server;
 
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
 
	public class Server {
		
	/************************* CLASS VARIABLES *************************/
	private static long TIME_OUT = 10000;
	public static final int BUFFER_SIZE = 256;
	public static final int PORT = 4444;
	public static final String IP = "10.1.0.85";
	public static final String DISCONNECT = "DISCONNECT";
	
	/************************* INST. VARIABLES *************************/
	private boolean runningflag = true;
	private ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
	private Selector selector;
	private ServerSocketChannel serverSocket;
	
	/************************* CTOR ************************************/
	 public Server() throws IOException {
		selector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.bind(new InetSocketAddress(IP, PORT));
		serverSocket.configureBlocking(false);
		serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		
		Start();
	 }
	 
	 private final void Start() throws IOException {
		 while (runningflag) {
				selector.select(TIME_OUT);
				Iterator<SelectionKey> ServerIterator = selector.selectedKeys().iterator();
				while (ServerIterator.hasNext()) {
					SelectionKey myKey = ServerIterator.next();
					
					if (myKey.isAcceptable()) {
						Connect(selector, serverSocket);
					} 
					
					else if(myKey.isReadable()) {
						Write(myKey);}
				}
				
				ServerIterator.remove();
			}
		}
	 
	 public void Connect(Selector selector, ServerSocketChannel serverSocket) throws IOException {
			SocketChannel client = serverSocket.accept(); 
			client.configureBlocking(false);
			client.register(selector, SelectionKey.OP_READ);
	 }
	 
	 public void Write(SelectionKey key) throws IOException {
		 SocketChannel writingSocket = (SocketChannel)key.channel();
		 if(-1 == writingSocket.read(buffer)) {
			 key.cancel();
			 return;
		 }
		 buffer.flip();
		 
		 String str = new String(buffer.array()).trim();
		 if(str.equals(DISCONNECT) || str.endsWith("has disconnected")) {
			 //writingSocket.write(ByteBuffer.wrap(("goodbye".getBytes())));
			 writingSocket.close();
			 System.out.println("user left");
			 return;}
		 
		 for(SelectionKey keyiter : selector.keys()) {
			 if(keyiter.isValid() && keyiter.channel() instanceof SocketChannel && keyiter != key) {
                       SocketChannel sc = (SocketChannel) (keyiter.channel());
                       if(!sc.isConnected()) {
                    	   key.cancel();
                       }
                       
                       else { 
                    	   sc.write(buffer);
                    	   buffer.rewind();
                	   }
			 		}
		 		}
		 buffer.clear();
	 	}
	 
	public static void main(String[] args) {
		try {
			new Server();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}