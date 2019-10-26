package in.ujjwal.db;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;


public class MultiThreadedSocketServer {

    ServerSocket myServerSocket;
    boolean ServerOn = true;
    String data_buffer="";
    int port = 0;
    
    public MultiThreadedSocketServer() 
    { 
        try 
        { 
        	
        	port = DBUtil.port;
        	System.out.println("port is ===>"+port);
            myServerSocket = new ServerSocket(port); 
        } 
        catch(IOException ioe) 
        { 
            System.out.println("Could not create server socket on port "+port+". Quitting."); 
            System.exit(-1); 
        } 

        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("It is now ==> " + formatter.format(now.getTime()));
        System.out.println("Server Thread started Successfully....");

        // Successfully created Server Socket. Now wait for connections. 
        while(ServerOn) 
        {                        
            try 
            { 
                // Accept incoming connections. 
                Socket clientSocket = myServerSocket.accept(); 
                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start(); 
            } 
            catch(IOException ioe) 
            { 
                System.out.println("Exception encountered on accept. Ignoring. Stack Trace :"); 
                ioe.printStackTrace(); 
            } 
        }

        try 
        { 
            myServerSocket.close(); 
            System.out.println("Server Stopped"); 
        } 
        catch(Exception ioe) 
        { 
            System.out.println("Problem stopping server socket"); 
            System.exit(-1); 
        } 
    } 

    
}

