            package smudiagnosis;

/*  3:   */ import java.io.File;
/*  4:   */ import java.io.FileNotFoundException;
/*  5:   */ import java.io.FileOutputStream;
/*  6:   */ import java.io.IOException;
/*  7:   */ import java.text.SimpleDateFormat;
/*  8:   */ import java.util.Date;
/*  9:   */ 
/* 10:   */ public class Service_Log
/* 11:   */ {
/* 12:20 */   public static String file_name = "-1";
             
/* 13:21 */   public static String service_log_path = "";
/* 14:22 */   public static File srcFile = null;
/* 15:   */   
/* 16:   */   public static void setLogPath(String log_path)
/* 17:   */   {
/* 18:26 */     service_log_path = log_path;
/* 19:   */   }
/* 20:   */   
/* 21:   */   public static void write_File(String data)
/* 22:   */   {
	            String folder = "Railway_Log";
	           
/* 23:31 */     String date = new SimpleDateFormat("ddMMyyyy").format(new Date());
/* 24:32 */     String time = new SimpleDateFormat("HHmmss").format(new Date());
/* 25:33 */     if (file_name.equals("-1"))
/* 26:   */     {
/* 27:35 */       file_name = "dculog_" + date + "_" + time + ".txt";
/* 28:36 */       srcFile = new File(service_log_path + "/" + folder + "/" + date + "/" + file_name);
/* 29:   */     }
/* 30:   */     else
/* 31:   */     {
/* 32:40 */       long filesize = srcFile.length();
/* 33:41 */       long filesizeInKB = filesize / 1024L;
/* 34:42 */       long filesizeInMB = filesizeInKB / 1024L;
/* 35:43 */       if (filesizeInMB >= 10L)
/* 36:   */       {
/* 37:45 */         date = new SimpleDateFormat("ddMMyyyy").format(new Date());
/* 38:46 */         file_name = "dculog_" + date + "_" + time + ".txt";
/* 39:47 */         srcFile = new File(service_log_path + "/" + folder + "/" + date + "/" + file_name);
/* 40:   */       }
/* 41:   */     }
/* 42:   */     try
/* 43:   */     {
/* 44:53 */       date = new SimpleDateFormat("ddMMyyyy").format(new Date());
/* 45:54 */       File log_dir = new File(service_log_path + "/" + folder + "/" + date);
/* 46:55 */       if (!log_dir.exists()) {
	                
					file_name="-1";
/* 47:56 */         log_dir.mkdirs();
						

/* 48:   */       }
/* 49:57 */       if (!srcFile.exists()) {
						
/* 50:58 */         srcFile.createNewFile();
/* 51:   */       }
/* 52:   */     }
/* 53:   */     catch (IOException ex)
/* 54:   */     {
/* 55:62 */       ex.printStackTrace();
/* 56:   */     }
/* 57:65 */     FileOutputStream fos = null;
/* 58:   */     try
/* 59:   */     {
/* 60:68 */       fos = new FileOutputStream(srcFile, true);
/* 61:   */       try
/* 62:   */       {
/* 63:71 */         data = new SimpleDateFormat("\r\ndd-MM-yyyy HH:mm:ss ==> ").format(new Date()) + data;
/* 64:72 */         fos.write(data.getBytes());
/* 65:   */       }
/* 66:   */       catch (IOException ex)
/* 67:   */       {
/* 68:76 */         ex.printStackTrace();
/* 69:   */       }
/* 70:   */       return;
/* 71:   */     }
/* 72:   */     catch (FileNotFoundException ex)
/* 73:   */     {
/* 74:81 */       ex.printStackTrace();
/* 75:   */     }
/* 76:   */     finally
/* 77:   */     {
/* 78:   */       try
/* 79:   */       {
/* 80:87 */         if (fos != null) {
/* 81:88 */           fos.close();
/* 82:   */         }
/* 83:   */       }
/* 84:   */       catch (IOException ex) {}
/* 85:   */     }
/* 86:   */   }


			

			}



