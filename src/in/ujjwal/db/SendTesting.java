package in.ujjwal.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.TimerTask;

public class SendTesting extends TimerTask {
	
	int port;
	String ip;
	
	public SendTesting(String ip, int port) {
		super();
		this.port = port;
		this.ip = ip;
	}
	
	 public void run() {
		 
	        System.out.println("====Sending Testing==="); 

			 String res="", str="";
			 
			 System.out.println("ip==>"+ip);
			 System.out.println("port==>"+port);
			  int ret = 0;
			  BufferedWriter wr = null;
			  BufferedReader rd = null;
			 try {
		            
		            // Create Socket address for configuring socket configuration
		            SocketAddress sockaddr = new InetSocketAddress(ip, port);
		 
		            // Create socket Object
		            Socket sock = new Socket();
		 
		            // if timeout is reached and no response is received, it will throw socket exception
		            int timeoutMs = 1000;   // in milliseconds
		 
		            // Initiate socket connection to server
		            sock.connect(sockaddr, timeoutMs);
		            
		        
		            try {
		                 
		                // Create Buffered Writer object to write String or Byte to socket output stream
		                wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		                String command = "Testing";
		                wr.write(command);
		                System.out.println("Send Command : ==>"+command);
		                 
		                // Flushing the writer
		                
		                wr.flush();
		              
		 
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		           
		            wr.close();
		            sock.close();
		            
		            
		          
		        } catch (UnknownHostException e) {
		            e.printStackTrace();
		        } catch (SocketTimeoutException e) {
		        	e.printStackTrace();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			 
			
		 
	     }
 }