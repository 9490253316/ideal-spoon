package in.ujjwal.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import smudiagnosis.DL_Main;
import smudiagnosis.Service_Log;



class ClientServiceThread extends Thread 
{ 
	
    Socket myClientSocket;
    boolean m_bRunThread = true; 
    boolean ServerOn = true;
    String data_buffer="" ,ip="",cmd="",server_ip="",wifi_ip="",write_cmd="NA";
    int port = 0 ;
    int server_port=0,wifi_port=0;
    DateFormat df = new SimpleDateFormat("HH:mm:SS");
    Store_Data sdd = new Store_Data();
    SendDataSocket sds = new SendDataSocket();
    Service_Log sl = new Service_Log();
    static String imei = "NA",dt="NA";
    static String hooter_imei = "NA";
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy HH:mm:ss");
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    
    SendDataSocket sd = new SendDataSocket();
    
    public ClientServiceThread() 
    { 
        super(); 
    } 

    ClientServiceThread(Socket s) 
    { 
        myClientSocket = s; 
    } 

    public void run() 
    {  
    	
    	String to = df.format(new Date());
        BufferedReader in = null; 
        PrintWriter out = null; 
        int write_flag = 0;
        String seq = "",h_imei="";
        

        // Print out details of this connection 
        System.out.println("Accepted Client Address is ==> " + myClientSocket.getInetAddress().getHostAddress()); 
        ip = myClientSocket.getInetAddress().getHostAddress();
        
       
        port = DBUtil.port;
        
		//myClientSocket.setSoTimeout (time*1000);
        
        try 
        {                                
            in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream())); 
            out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream())); 

            // At this point, we can read for input and reply with appropriate output. 
            // Run in a loop until m_bRunThread is set to false
           
            while(m_bRunThread) 
            {                    
                // read incoming stream 
            	 String clientData = "" ;
                 clientData = in.readLine(); 
          
                System.out.println("Client Says ===>" + clientData);
                sl.write_File(clientData);   
             
                if(!ServerOn) 
                { 
                    // Special command. Quit this thread 
                    System.out.print("Server has already stopped"); 
                    out.flush(); 
                    m_bRunThread = false;   
                } 
                
                if(clientData.contains("$SOCKET"))  //master
                {
                	System.out.println("====== Inside $SOCKET ========");
                	
                }
              
                else
                {
                	System.out.println(" wrong Respone ========> "+clientData);
                }
                
              
                m_bRunThread = false; 
                out.flush();
                
            }
           
            	
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        finally 
        { 
            // Clean up 
            try 
            {                    
                in.close(); 
                out.close(); 
                myClientSocket.close(); 
                System.out.println("......Stopped"); 
            } 
            catch(IOException ioe) 
            { 
                ioe.printStackTrace(); 
            } 
        } 
    }
    
   
    
} 
