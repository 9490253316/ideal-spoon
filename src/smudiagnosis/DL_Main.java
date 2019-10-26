package smudiagnosis;


import in.ujjwal.db.DBUtil;
import in.ujjwal.db.MultiThreadedSocketServer;

import java.io.IOException;
import java.util.Properties;



public class DL_Main
{

	public static void main(String[] args) throws IOException
    {
	    
     try
     {  
        
        // String cur_path = "/home/ujjwal/Desktop/Services/Railway";    
    	// Service_Log.setLogPath(cur_path);
    	 DBUtil.readDBProps();
    	 SMain m = new SMain();
    	 m.main_fun();
    	 new MultiThreadedSocketServer(); 
         
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
