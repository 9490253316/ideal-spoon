package in.ujjwal.db;

import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import smudiagnosis.SMain;

public class Main_Thread implements Runnable {

	@Override
	public void run() {
		SendDataSocket sds = new SendDataSocket();
		Store_Data sd = new Store_Data();
		int sck_en = 0;
		SMain sm =new SMain();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		for(;;)
		{
			    String stsm[];
				try {
					
					sd.getLcGateSts(); 
					Thread.sleep(20000);
			    	
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				
				
		  
		}
		
	}

}
