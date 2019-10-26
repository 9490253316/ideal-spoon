package in.ujjwal.db;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil
{
  private static String dbClass = null;
  private static String dbURL = null;
  private static String dbUser = null;
  private static String dbPass = null;
  public static int gt_cnt = 0;
  public static String g1=null,g2=null,g3=null,imei=null;
  public static int port=0,time=0;
  
  public static String getTargetDB()
  {
    return targetDB;
  }
  
  public boolean found = false;
  public int retryCount = 10;
  public int retryDelay = 300;
  public static String targetDB = null;
  
  public static void readDBProps(String className, String url, String user, String pass)
    throws Exception
  {
    dbClass = className;
    dbURL = url;
    dbUser = user;
    dbPass = pass;
      
    Class.forName(dbClass).newInstance();
    System.out.println(dbClass);
    System.out.println(dbURL);
    System.out.println(dbUser);
  }
  
  public static void readDBProps()
    throws Exception
  {
  
	//BufferedReader br = new BufferedReader(new FileReader("db_mdas.properties"));
    BufferedReader br = new BufferedReader(new FileReader("/home/ujjwal/Rasp_Dev/Service/db_mdas.properties"));
	   
    Properties props = new Properties();
    props.load(br);
    targetDB = props.getProperty("target.db").trim();
    System.out.println("Target DB :" + getTargetDB());
    dbClass = props.getProperty(targetDB + "." + "dbClass").trim();
    dbURL = props.getProperty(targetDB + "." + "dbURL").trim();
    dbUser = props.getProperty(targetDB + "." + "dbUser").trim();
    dbPass = props.getProperty(targetDB + "." + "dbPass").trim();
    port = Integer.parseInt(props.getProperty("port"));
    time = Integer.parseInt(props.getProperty("time"));
    gt_cnt = Integer.parseInt(props.getProperty("gate_count"));
    g1 = (props.getProperty("gate1"));
    g2 = (props.getProperty("gate2"));
    g3 = (props.getProperty("gate3"));
    imei = (props.getProperty("imei"));
    System.out.println(dbClass);
    System.out.println(dbURL);
    System.out.println(dbUser);
    Class.forName(dbClass);
    
    //Class.forName("com.mysql.jdbc.Driver");
  }
  
  public static void testConnection()
    throws Exception
  {
    Connection con = null;
    try
    {
      con = DriverManager.getConnection(dbURL, dbUser, dbPass);
    }
    finally
    {
      if (con != null) {
        con.close();
      }
    }
  }
  
  public Connection getConnection()
    throws Exception
  {
    Connection con = null;
    //int count = 0;
    while (con==null) 
    {
      try
      { 
        con = DriverManager.getConnection(dbURL, dbUser, dbPass);
      }
      catch (Exception e)
      {
        System.out.println(e.getMessage());
        Thread.sleep(this.retryDelay);
      }
    }
    return con;
  }
  
  public static void main(String[] args)
    throws Exception
  {
    readDBProps();
    System.out.println(new DBUtil().getConnection());
  }

public static void closeConnection(Connection con, ResultSet rs,
		PreparedStatement ps) throws SQLException {
	// TODO Auto-generated method stub
	if(null!=con)
		con.close();
	if(null!=rs)
		rs.close();
   if(null!=ps)
		ps.close();

}
}
