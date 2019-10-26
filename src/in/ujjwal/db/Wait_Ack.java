package in.ujjwal.db;

import smudiagnosis.SMain;

public class Wait_Ack implements Runnable{
	
     public Wait_Ack()
     {
    	 
     }

	@Override
	public void run() {
		Store_Data sd =new Store_Data();
		SMain sm =new SMain();
		int res_14=0,res_14a=0,res_15=0,ok_14=0,ok_14a=0,ok_15=0;
		 for(;;)
		 {
			
			try {
				res_14 = sd.callAck("ack_14");   //column name ack_1 4 in lc_state need to check for all acks write extra code
				if(res_14==1 && ok_14 == 0)
				{
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					ok_14 = 1;
				}else
				{
					//ok_14 = 0;
				} 
				
				 res_14a = sd.callAck("ack_14a");   //column name ack_14a in lc_state 
				if(res_14a==1 && ok_14a == 0)
				{
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					ok_14a = 1;
				}else
				{
					//ok_14a = 0;
				} 
				
			/*	res_15 = sd.callAck("ack_15");   //column name ack_15 in lc_state 
				if(res_15==1 && ok_15 == 0)
				{
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					ok_15 = 1;
				}else
				{
					//ok_15 = 0;
				} */
				
			/*	if(ok_14 == 1 && ok_14a == 1 && ok_15 == 1)
				{   
				    ok_14=0;ok_14a=0;ok_15=0;
					break;
				} */
				
				if(ok_14 == 1 || ok_14a == 1 ) //if(ok_14 == 1 && ok_14a == 1 )
				{   
				    ok_14=0;ok_14a=0;
					break;
				}
				
			//	if( ok_14 == 1 ) {ok_14 = 0; break;}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			System.out.println("checking ack==>"+ok_14a);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		 }
		 
	/*   try {
		SMain.sp.getViewport().add(sm.getTable(SMain.column));
	  } catch (Exception e) {
		
		e.printStackTrace();
	 } */
	   
		 System.out.println("ACK OVER WAITING FOR Close STATE");
		 
		  Wait_Close wc = new Wait_Close();
		  Thread cl = new Thread(wc);
		  cl.start(); 
		  
		 
		 
	  
		
	}
}
