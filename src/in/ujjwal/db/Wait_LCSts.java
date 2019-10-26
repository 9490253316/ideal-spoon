package in.ujjwal.db;

import smudiagnosis.SMain;

public class Wait_LCSts implements Runnable{

	@Override
	public void run() {
		int res_14=0,res_14a=0,res_15=0,ok_14=0,ok_14a=0,ok_15=0;
		Store_Data sd =new Store_Data();
		SMain sm =new SMain();
		UtilityFunctions uf = new UtilityFunctions();
		
		System.out.println("OPen thread started");
		
		for(;;)
		 {
			
			
			try {
				
				res_14 = sd.callLCState("lc14");  //column name ack_1 4 in lc_state need to check for all acks write extra code
				if(res_14==0 )
				{
					
					if(ok_14 == 0){
						
						SMain.sp.getViewport().add(sm.getTable(SMain.column));
						uf.callmp3("/home/ujjwal/Rasp_Dev/Service/lc14_open.mp3");
						//Thread.sleep(5000);
						ok_14 = 1;
					}
				}else
				{
					
				}
				
				res_14a = sd.callLCState("lc14a");   //column name ack_14a in lc_state 
				if(res_14a==0 )
				{
					
					if(ok_14a == 0){
						
						SMain.sp.getViewport().add(sm.getTable(SMain.column));
						uf.callmp3("/home/ujjwal/Rasp_Dev/Service/lc14a_open.mp3");
						//Thread.sleep(5000);
						ok_14a = 1;
					}
				}else 
				{
					
				}
				
			/*	res_15 = sd.callLCState("lc15");   //column name ack_15 in lc_state 
				if(res_15==0 && ok_15 == 0)
				{
					SMain.sp.getViewport().add(sm.getTable(SMain.column));
					ok_15 = 1;
				} */
				
			/*	if(ok_14 == 1 && ok_14a == 1 && ok_15 == 1)
				{ 
				    ok_14=0;ok_14a=0;ok_15=0;
					break;
				} */
				
				if(ok_14 == 1 || ok_14a == 1) //if(ok_14 == 1 && ok_14a == 1)
				{ 
					System.out.println("======Inside loop break==========");
					ok_14=0;ok_14a=0;
					break;
				    
					
				}
				
				
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			System.out.println("checking lc==>"+res_14a);
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			
		 }
	}

}
