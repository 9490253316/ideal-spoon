package in.ujjwal.db;

import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import smudiagnosis.SMain;
import smudiagnosis.Table_Map;

public class Store_Data {
	
	public String Login_check(String pwd) throws Exception
	{
		 int data = 0;
		 String user ="";
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			// ps =con.prepareStatement("select * from dect_send where send_imei=? and dect_imei=? and flag=?");
			 ps =con.prepareStatement("select count(*) as cnt,user_id from rtum_user where user_password=?");				
			// ps.setString(1, hooter_imei);
		     ps.setString(1, pwd);
		   
		     System.out.println(ps);
		     rs = ps.executeQuery();
		     if (rs.next())
		     {
		    	 data = rs.getInt("cnt");
		    	 user = rs.getString("user_id")+"#"+data;
		    	
		      }
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) {SMain.conn.setText("DisConnected"); }
		 }
	    
	    return user;
	}
	
	
	public void update_lcstate(String msg) throws Exception
	{
		 int data = 0;
		 String user ="";
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			 ps =con.prepareStatement("update lc_state set lc14=1,lc14a=1,lc15=1,ack_14=0,close_ack_14=0,ack_14a=0,close_ack_14a=0,ack_15=0,close_ack_15=0,msg='"+msg+"',sm_imei='"+DBUtil.imei+"'");				
		     System.out.println(ps);
		     ps.executeUpdate();
		    
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) { }
		 }
	    
	}
	
	
	public void insert_smdata(String tr_no,String cmd,String pn,String nm) throws Exception
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String dt = sdf1.format(new Date());
		//StringBuilder pn_no =new StringBuilder(pn);
		//pn_no.append("["+sdf.format(new Date())+"]");
		
		String lc[] = new String[]{"14","14A"};
		String lctab[] = new String[]{"lc14_data","lc14a_data"};
		
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 PreparedStatement ps1 = null;
		 ResultSet rs=null;

	     String insql="insert into sm_data(p1,p2,p3,p4,date_time,sm_name,sm_imei)values(?,?,?,?,?,?,?)";
    		
	    try
	    {
	    	for(int i=0;i<=1;i++){
	    	  ps = con.prepareStatement(insql);
		      ps.setString(1, tr_no);
		      ps.setString(2, cmd);
		      ps.setString(3, pn);
		      ps.setString(4, lc[i]);
		      ps.setString(5, dt);
		      ps.setString(6, nm);
		      ps.setString(7, DBUtil.imei);
		     
		     System.out.println("query is ==>"+ps);
		     int insertCount = ps.executeUpdate();
		     
		      ps1 = con.prepareStatement("insert into "+lctab[i]+"(p1,p2,date_time,lc_nm,sm_imei)values(?,?,?,?,?)");
		      ps1.setString(1, pn);
		      ps1.setString(2, tr_no);
		      ps1.setString(3, dt);
		      ps1.setString(4, lc[i]);
		      ps1.setString(5, DBUtil.imei);
		     
		     System.out.println("query is ==>"+ps1);
	      
		     int ic = ps1.executeUpdate();
	    	}
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) { }
		 }
	} 
	
	public int callLCState(String lc_nm) throws Exception
	{
		 int res = 0;
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			
			 ps =con.prepareStatement("select "+lc_nm+" from lc_state where sm_imei=?");				
			 ps.setString(1, DBUtil.imei);
		     rs = ps.executeQuery();
		     if (rs.next())
		     {
		    		res = rs.getInt(lc_nm);
		    
		      }
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) {SMain.conn.setText("DisConnected"); }
		 }
	   
	    return res;
	}
	

	
	public int callCloseState(String lc_nm) throws Exception  //getting column name of lc_state from method
	{
		 int res = 0;
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			
			 ps =con.prepareStatement("select "+lc_nm+" from lc_state where sm_imei=?");				
			 ps.setString(1, DBUtil.imei);
		     rs = ps.executeQuery();
		     if (rs.next())
		     {
		    		res = rs.getInt(lc_nm);
		    
		      }
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) {SMain.conn.setText("DisConnected"); }
		 }
	   
	    return res;
	}
	
	public int callAck(String lc_nm) throws Exception
	{
		 int res = 0;
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			
			 ps =con.prepareStatement("select "+lc_nm+" from lc_state where sm_imei=?");				
			 ps.setString(1, DBUtil.imei);
		     rs = ps.executeQuery();
		     if (rs.next())
		     {
		    		res = rs.getInt(lc_nm);
		    
		      }
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) {SMain.conn.setText("DisConnected"); }
		 }
	   
	    return res;
	}
	
	
	
	public int getLcGateSts() throws Exception
	{
		 int res = 0;
		 Connection con = new DBUtil().getConnection();
		 PreparedStatement ps = null;
		 ResultSet rs=null; 
		 
		try
	    {
			 ps =con.prepareStatement("select * from comm_state where sm_imei=?");	
			 ps.setString(1, DBUtil.imei);
			 System.out.println(ps);
		     rs = ps.executeQuery();
		     if (rs.next())
		     {
		    	 if(rs.getInt("g1_c") == 1)  // if how many gates are there need to check
				 {
		    		SMain.jTextArea4.setBackground(Color.green);
		    	 }else
		    	 {
		    		 SMain.jTextArea4.setBackground(Color.red); 
		    	 }
		    	 
		    	 if((rs.getInt("g1_g") == 1))  
				 {
		    		SMain.jTextArea5.setBackground(Color.green);
		    	 }else if((rs.getInt("g1_g") == 2)) 
		    	 {
		    		 SMain.jTextArea5.setBackground(Color.red);
		    	 }
		    	 else if((rs.getInt("g1_g") == 3)) 
		    	 {
		    		 SMain.jTextArea5.setBackground(Color.blue);
		    	 }else
		    	 {
		    		 SMain.jTextArea5.setBackground(Color.yellow);
		    	 }
		    	 
		    	 System.out.println(rs.getInt("g2_c"));
		    	 
		    	 if(rs.getInt("g2_c") == 1)  // if how many gates are there need to check
				 {
		    		SMain.jTextArea6.setBackground(Color.green);
		    	 }else
		    	 {
		    		 SMain.jTextArea6.setBackground(Color.red);
		    	 }
		    	 
		    	 if((rs.getInt("g2_g") == 1))  
				 {
		    		SMain.jTextArea7.setBackground(Color.green);
		    	 }else if((rs.getInt("g2_g") == 2)) 
		    	 {
		    		 SMain.jTextArea7.setBackground(Color.red);
		    	 }
		    	 else if((rs.getInt("g2_g") == 3)) 
		    	 {
		    		 SMain.jTextArea7.setBackground(Color.blue);
		    	 }else
		    	 {
		    		 SMain.jTextArea7.setBackground(Color.yellow);
		    	 }
		    	 
		   /* 	 if(rs.getInt("g3_c") == 1)  // if how many gates are there need to check
				 {
		    		SMain.lc15_com.setBackground(Color.green);
		    	 }
		    	 if((rs.getInt("g3_g") == 1))  
				 {
		    		SMain.lc15_gt.setBackground(Color.green);
		    	 }else if((rs.getInt("g3_g") == 2)) 
		    	 {
		    		 SMain.lc15_gt.setBackground(Color.red);
		    	 }
		    	 else if((rs.getInt("g3_g") == 3)) 
		    	 {
		    		 SMain.lc15_gt.setBackground(Color.blue);
		    	 }else
		    	 {
		    		 SMain.lc15_gt.setBackground(Color.yellow);
		    	 } */
		    	 
		    	
		      }
	    }
	    finally
	     {
		    try
			    {
			       DBUtil.closeConnection(con, rs, ps);
			    }
			        catch(SQLException ex) { SMain.conn.setText("DisConnected");}
		 }
	   
	    return res;
	}
	
	
	
	
	
}
