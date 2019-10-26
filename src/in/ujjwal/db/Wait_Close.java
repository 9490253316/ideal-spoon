package in.ujjwal.db;

import smudiagnosis.SMain;

public class Wait_Close implements Runnable {
   
	@Override
	public void run() {
		int res_14=0,res_14a=0,res_15=0,ok_14=0,ok_14a=0,ok_15=0;
		Store_Data sd =new Store_Data();
		SMain sm =new SMain();
		UtilityFunctions uf = new UtilityFunctions();
		for(;;)
		 {
			
			try {
				
					res_14 = sd.callCloseState("close_ack_14");   //column name close_ack_14  in lc_state need to check for all acks write extra code
				if(res_14==1 && ok_14 == 0)
				{
					
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					uf.callmp3("/home/ujjwal/Rasp_Dev/Service/lc14_close.mp3");
					ok_14 = 1;
				}else
				{
					//ok_14 = 0;
				} 
				
				res_14a = sd.callCloseState("close_ack_14a");   //column name ack_14a in lc_state 
				if(res_14a==1 && ok_14a == 0)
				{
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					uf.callmp3("/home/ujjwal/Rasp_Dev/Service/lc14a_close.mp3");
					ok_14a = 1;
				}else
				{
					//ok_14a = 0;
				} 
				
			/*	res_15 = sd.callCloseState("close_ack_15");   //column name ack_15 in lc_state 
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
					break;
				} */
				

					if(ok_14 == 1 || ok_14a == 1 )  //if(ok_14 == 1 && ok_14a == 1 )
					{
						SMain.sp.getViewport().add(sm.getTable(SMain.column));
						
						ok_14 = 0; ok_14a = 0; break;
					} 
				
			/*	if( ok_14 == 1 ) {
					
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					
					ok_14 = 0; break;
					
				} */
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			System.out.println("checking close==>"+ok_14a);
			
			
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
		 }
		
		 System.out.println("ACK OVER WAITING FOR LC STATE");
		 
		 Wait_LCSts wl = new Wait_LCSts();
		 Thread twl = new Thread(wl);
		 twl.start(); 
	}

}
