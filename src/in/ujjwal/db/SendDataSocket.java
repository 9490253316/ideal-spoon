package in.ujjwal.db;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class SendDataSocket {
	
	 public void send_data_to_slave(String ip, int port, String msg)
	 {
		 String res="", str="";
		 System.out.println("========send=========");
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
	          //  sock.connect(sockaddr, timeoutMs);
	            
	        
	            try {
	                 
	                // Create Buffered Writer object to write String or Byte to socket output stream
	                wr = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
	                String command = "$"+msg+"#";
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

	
	 public int send_data_to_host(String ip, int port, String msg)
	 {
		 String res="", str="";
		 System.out.println("========send=========");
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
	                String command = msg;
	                wr.write(command);
	                System.out.println("Send Command : ==>"+command);
	                 
	                // Flushing the writer
	                
	                wr.flush();
	              
	 
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	           
	            try {
	                 rd = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	                
	                while ((str = rd.readLine()) != null) {
	                	res = str;
	                    System.out.println(res);
	                    System.out.println("res===>"+res);
	    	            
	    	            if(res.equals("ACK"))
	    	            {
	    	            	 ret = 1;
	    	            	  sock.close();
	    	            		break;
	    	            }
	    	            else  if(res.equals("NACK"))
	    	            {
	    	            	 ret = 0;
	    	            	  sock.close();
	    	            		break;
	    	            }
	    	            else{
	    	            	 ret = 0;
	    	            	  sock.close();
	    	            		break;
	    	            }
	    	           
	    	            // Close socket connection after finish receiving a response
	    	           
	                }
	               
	               
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	           
	       System.out.println("return===>"+ret);
	            
	       /*      if(res.contains("ACK"))
	            {
	            	 ret = 1;
	            		
	            }
	           
	            // Close socket connection after finish receiving a response
	            rd.close();
	            wr.close();
	            sock.close();*/
	            rd.close();
	            wr.close();
	          
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (SocketTimeoutException e) {
	        	e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
		 return ret;
	 }

}
