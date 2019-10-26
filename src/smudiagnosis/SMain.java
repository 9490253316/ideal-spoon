package smudiagnosis;

import in.ujjwal.db.DBUtil;
import in.ujjwal.db.Main_Thread;
import in.ujjwal.db.SendDataSocket;
import in.ujjwal.db.Store_Data;
import in.ujjwal.db.UtilityFunctions;
import in.ujjwal.db.Wait_Ack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.DefaultCaret;


public class SMain extends JFrame {
	
		public JScrollPane jScrollPane1;
		public JScrollPane jScrollPane2;
		public JScrollPane jScrollPane3;
		public JTextArea jTextArea1;
		public static JTextArea jTextArea2,jTextArea3,jTextArea4,jTextArea5,jTextArea6,jTextArea7,jTextArea8,jTextArea9;
		public DefaultCaret caret = null;
		
		
	   public static JLabel conn;	
	   public JLabel lbl_dt;
	   public JLabel train;
	   public JLabel pn;
	   public JLabel logo_lbl,sm,sm_nm,per,dt,dt_nm,time,time_nm,batt_lbl;
	   public JLabel lc14,lc14a,lc15,lc14_com,lc14a_com,lc15_com,lc14_gt,lc14a_gt,lc15_gt;
	   public JTextField jtext1;  
	   public JTextField tfp;  
	   public JTextField jtext2;
	   public JTextField txt_dt;
	   public JButton start_btn,btn;
	   public JButton send_btn;
	   public JLabel statul_lbl;
	   public JRadioButton jrb1,jrb2;
	   public JTable t1;
	   public static JScrollPane sp=new JScrollPane(); 
	   ButtonGroup bg = new ButtonGroup();
	   String rbtn = "";
	   
	   DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	   String date=df.format(new Date());
	   
	   DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	   String today=df1.format(new Date());
	   
	   DateFormat tf = new SimpleDateFormat("HH:mm");
	   String tm = tf.format(new Date());
    
	   public static String column[]={"SLNO","TRAIN NO","CMD","SM PN","LC #","ACKN","LC STATE","LC PN"};   
      
	   Border border = BorderFactory.createLineBorder(Color.gray);
	   
	   DataTextFiledsBean text_fld_bean = new DataTextFiledsBean();
	   SendDataSocket sds = new SendDataSocket();
	   Store_Data sd = new Store_Data();
	   UtilityFunctions uf = new UtilityFunctions();
	   public int start = 0;
	   public void main_fun() throws Exception
	   {
		   create();
	       setVisible(true);
	   }
	   
	   public void create() throws Exception
	   {
		     
		  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	      int x = dim.width;
		  int y = dim.height-30;	
		  
		  System.out.println("width : "+dim.width+"Height: "+dim.height); //1024, 600
		  
		 // int x = 1024;
		  //int y = 600;
	      //int y = dim.height+50;                   
		  setLayout(null);
		 
		 // this.getContentPane().setBackground(Color.decode("#60a0bf"));
		 // this.getContentPane().setBackground(Color.lightGray);
		  this.getContentPane().setBackground(new Color(194,228,194));
		  setTitle("SM");
		  UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black ));
		
		  this.start_btn = new JButton("START");
		  this.start_btn.setFont(new Font("Helvetica", Font.BOLD, 18));
		  this.start_btn.setBackground(new Color(0,153,0)); 
		  this.start_btn.setForeground(Color.white);
		  this.start_btn.setFocusPainted(false);
	      addComponent(this, this.start_btn, 5, 15, 100, 70);  //x, y, width,height
	    
		  
	       this.train = new JLabel("TRAIN # : ");
	       this.train.setFont(new Font("Helvetica", Font.BOLD, 17));
		   addComponent(this, this.train, 120, 10, 100, 30);   //(20+105 == 170)
		   this.jtext1 = new JTextField();
		   this.jtext1.setFont(new Font("Helvetica", Font.BOLD, 19));
		   addComponent(this, this.jtext1, 220, 7, 120, 33);
		  
		    this.pn = new JLabel("PN # :"); 
		    this.pn.setFont(new Font("Helvetica", Font.BOLD, 18));
		    addComponent(this, this.pn, 120, 53, 100, 30);
		    this.jtext2 = new JTextField(); 
		    this.jtext2.setFont(new Font("Helvetica", Font.BOLD, 19));
		    this.jtext2.setEditable(false);
		    addComponent(this, this.jtext2, 220, 52, 120, 33);
		    
		  
		    
		    this.jrb1 = new JRadioButton("Up");
		    this.jrb1.setFont(new Font("Helvetica", Font.BOLD, 18)); 
		    this.jrb1.setBackground(Color.decode("#D2B48C"));  //new Color(221,160,221)
		    this.jrb1.setFocusPainted(false);
			addComponent(this, this.jrb1, 350, 12, 80, 30);
			
			this.jrb2 = new JRadioButton("Down");
			this.jrb2.setFont(new Font("Helvetica", Font.BOLD, 17));
			this.jrb2.setBackground(Color.decode("#D2B48C"));
			this.jrb2.setFocusPainted(false);
			addComponent(this, this.jrb2, 350, 52, 80, 30);
			
			bg.add(jrb1);
			bg.add(jrb2);
			
			
			  
			this.send_btn = new JButton("SEND");
			this.send_btn.setFont(new Font("Helvetica", Font.BOLD, 18)); 
			this.send_btn.setBackground(new Color(0,153,0) ); //new Color(255,102,102) Color.decode("#C00000")
			this.send_btn.setForeground(Color.white);
			this.send_btn.setFocusPainted(false);
			this.send_btn.setBorderPainted(false);
			//stop_btn.setBorder(new RoundedBorder(15));
			
		    addComponent(this, this.send_btn, 450, 15, 90, 70);
		    
		   
		
		  
		    this.start_btn.addActionListener(new ActionListener()
		     {
		        public void actionPerformed(ActionEvent e)
		        {
		          // SMU_Main.this.stop_Communication();
		        	try {
		        		
						java.lang.Runtime.getRuntime().exec("matchbox-keyboard");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
		        	
		         }
		     });
		  
		 
		    if(DBUtil.gt_cnt == 1)
		    {
		    	this.lc14 = new JLabel(DBUtil.g1);
			    this.lc14.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc14, 670, 0, 60, 30);
				
				 this.jTextArea4 = new JTextArea();
				 this.jTextArea4.setEditable(false);
				 this.jTextArea4.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea4, 660, 33, 28, 28);
				 this.lc14_com = new JLabel("COM");
				 this.lc14_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_com, 695, 31, 60, 28);
				 
				 this.jTextArea5 = new JTextArea();
				 this.jTextArea5.setEditable(false);
				 this.jTextArea5.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea5, 660, 68, 28, 28);
				 this.lc14_gt = new JLabel("GATE");
				 this.lc14_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_gt, 695, 66, 60, 28);
		    }
		    else if(DBUtil.gt_cnt == 2)
		    {
		    	this.lc14 = new JLabel(DBUtil.g1);
			    this.lc14.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc14, 570, 0, 60, 30);
				
				 this.jTextArea4 = new JTextArea();
				 this.jTextArea4.setEditable(false);
				 this.jTextArea4.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea4, 560, 33, 28, 28);
				 this.lc14_com = new JLabel("COM");
				 this.lc14_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_com, 595, 31, 60, 28);
				 
				 this.jTextArea5 = new JTextArea();
				 this.jTextArea5.setEditable(false);
				 this.jTextArea5.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea5, 560, 68, 28, 28);
				 this.lc14_gt = new JLabel("GATE");
				 this.lc14_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_gt, 595, 66, 60, 28);
				
				
			    this.lc14a = new JLabel(DBUtil.g2);
			    this.lc14a.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc14a, 700, 0, 70, 30);
				
				 this.jTextArea6 = new JTextArea();
				 this.jTextArea6.setEditable(false);
				 this.jTextArea6.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea6, 690, 33, 28, 28);
				 this.lc14a_com = new JLabel("COM");
				 this.lc14a_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14a_com, 725, 31, 60, 28);
				 
				 this.jTextArea7 = new JTextArea();
				 this.jTextArea7.setEditable(false);
				 this.jTextArea7.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea7, 690, 68, 28, 28);
				 this.lc14a_gt = new JLabel("GATE");
				 this.lc14a_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14a_gt, 725, 66, 60, 28);
		    }
		    else if(DBUtil.gt_cnt == 3)
		    {
			    this.lc14 = new JLabel(DBUtil.g1);
			    this.lc14.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc14, 570, 0, 60, 30);
				
				 this.jTextArea4 = new JTextArea();
				 this.jTextArea4.setEditable(false);
				 this.jTextArea4.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea4, 560, 33, 28, 28);
				 this.lc14_com = new JLabel("COM");
				 this.lc14_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_com, 595, 31, 60, 28);
				 
				 this.jTextArea5 = new JTextArea();
				 this.jTextArea5.setEditable(false);
				 this.jTextArea5.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea5, 560, 68, 28, 28);
				 this.lc14_gt = new JLabel("GATE");
				 this.lc14_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14_gt, 595, 66, 60, 28);
				
				
			    this.lc14a = new JLabel(DBUtil.g2);
			    this.lc14a.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc14a, 700, 0, 70, 30);
				
				 this.jTextArea6 = new JTextArea();
				 this.jTextArea6.setEditable(false);
				 this.jTextArea6.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea6, 690, 33, 28, 28);
				 this.lc14a_com = new JLabel("COM");
				 this.lc14a_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14a_com, 725, 31, 60, 28);
				 
				 this.jTextArea7 = new JTextArea();
				 this.jTextArea7.setEditable(false);
				 this.jTextArea7.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea7, 690, 68, 28, 28);
				 this.lc14a_gt = new JLabel("GATE");
				 this.lc14a_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc14a_gt, 725, 66, 60, 28);
				
			
			    this.lc15 = new JLabel(DBUtil.g3);
			    this.lc15.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				addComponent(this, this.lc15, 835, 0, 70, 30);
				
				 this.jTextArea8 = new JTextArea();
				 this.jTextArea8.setEditable(false);
				 this.jTextArea8.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea8, 820, 33, 28, 28);
				 this.lc15_com = new JLabel("COM");
				 this.lc15_com.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc15_com, 855, 32, 60, 28);
				 
				 this.jTextArea9 = new JTextArea();
				 this.jTextArea9.setEditable(false);
				 this.jTextArea9.setBackground(Color.RED);
				 //this.jTextArea1.setBorder(border);
				 addComponent(this, this.jTextArea9, 820, 68, 28, 28);
				 this.lc15_gt = new JLabel("GATE");
				 this.lc15_gt.setFont(new Font("Helvetica", Font.BOLD, 17)); 
				 addComponent(this, this.lc15_gt, 855, 66, 60, 28);
		    }
			
			ImageIcon logo = new ImageIcon("/home/ujjwal/Rasp_Dev/Service/settings.gif");
		    //logo_lbl = new JLabel(logo);
		    this.btn = new JButton(logo);
		    this.btn.setBackground(new Color(194,228,194));
		    this.btn.setFocusPainted(false);
			this.btn.setBorderPainted(false);
		    addComponent(this, this.btn, 950, 10, 35, 35);
		    
		    this.sm = new JLabel("SM : ");
		    this.sm.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.sm, 10, 110, 50, 30); 
			this.sm_nm = new JLabel("--");
			this.sm_nm.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.sm_nm, 55, 110, 100, 30);
			
			this.dt = new JLabel("Date : ");
			this.dt.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.dt, 175, 110, 70, 30); 
			this.dt_nm = new JLabel(date);
			this.dt_nm.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.dt_nm, 250, 110, 120, 30);
			
			this.time = new JLabel("Time :");
			this.time.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.time, 400, 110, 70, 30); 
			this.time_nm = new JLabel(tm);
			this.time_nm.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.time_nm, 475, 110, 70, 30);
			
		    ImageIcon caret = new ImageIcon("/home/ujjwal/Rasp_Dev/Service/caret.png");
		    logo_lbl = new JLabel(caret);
			addComponent(this, this.logo_lbl, 575, 107, 35, 35);
			
			this.conn = new JLabel("Connected");
			this.conn.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.conn, 610, 110, 120, 30); 
			
			/* ImageIcon batt = new ImageIcon("/home/ujjwal/Rasp_Dev/Service/batt.png");
			batt_lbl = new JLabel(batt);
			addComponent(this, this.batt_lbl, 750, 108, 20, 35);
			
			this.per = new JLabel("100%"); 
			this.per.setFont(new Font("Helvetica", Font.BOLD, 18));
			addComponent(this, this.per, 770, 110, 70, 30); */
			
			
			String data[][]={ {"1","177295 DN","CLOSE","86[13:31]","15","[13:31]","CLOSE[13:31]","64[13:31]"},    
					{"2","-","CLOSE","86[13:31]","15","[13:31]","CLOSE[13:31]","64[13:31]"},    
                    };    
			       
			
			// JScrollPane sp=new JScrollPane(); 
			 // sp.add(getTable(column)); 
			 sp.getViewport ().add (getTable(column));
			 addComponent(this, sp, 5, 150, 1010, 365);
			
			 this.jtext1.addKeyListener(new KeyAdapter()
		       {
		         public void keyTyped(KeyEvent e)
		        {
		           
		        	 
		        	 
		           String leng = SMain.this.sm_nm.getText();
		           if(leng.equals("--")) {
		        	  e.consume();
		        	  JOptionPane.showMessageDialog(SMain.this,"Please login with your credentials","Alert",JOptionPane.WARNING_MESSAGE);
		        	  
		          }else{
		        	 
		          }
		           
		         }
		      }); 
			 
			/* PopUpKeyboard keyboard = new PopUpKeyboard(sm_nm);
			 sm_nm.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		                Point p = sm_nm.getLocationOnScreen();
		                p.y += 30;
		                keyboard.setLocation(p);
		                keyboard.setVisible(true);
		            }
		        }); */
			 ActionListener actionListener = new ActionListener() {
				    @Override
				    public void actionPerformed(ActionEvent e) {
				        if(e.getSource() instanceof JRadioButton){
				        	
				        	try {
								java.lang.Runtime.getRuntime().exec("pkill -9 -f matchbox-keyboard");
							} catch (IOException e2) {
								
								e2.printStackTrace();
							}
				        	
				            JRadioButton radioButton = (JRadioButton) e.getSource();
				            if(radioButton.isSelected()){
				               // jtfAnswer.setText(radioButton.getText());
				            	System.out.println("Radio value==>"+radioButton.getText());
				            	String jv = radioButton.getText();
				            	String train_no = SMain.this.jtext1.getText();
				            	if(jv.equals("Up")){
				            		 if(train_no.contains("UP") || train_no.contains("DN"))
				            		 {
				            			 SMain.this.jtext1.setText(train_no.substring(0, train_no.indexOf(" "))+" UP");
				            		 }else{
				            			 SMain.this.jtext1.setText(SMain.this.jtext1.getText()+" UP");
				            		 }
				            	 
				            	}else if(jv.equals("Down")){
				            		if(train_no.contains("UP") || train_no.contains("DN"))
				            		 {
				            			 SMain.this.jtext1.setText(train_no.substring(0, train_no.indexOf(" "))+" DN");
				            		 }else{
				            			 SMain.this.jtext1.setText(SMain.this.jtext1.getText()+" DN");
				            		 }
					            }
				            	 SMain.this.jtext2.setText((int)uf.getRandomIntegerBetweenRange(1.0, 100.0)+"");
				            }
				        }
				    }
				};

				jrb1.addActionListener(actionListener);
				jrb2.addActionListener(actionListener);
			 
			  this.send_btn.addActionListener(new ActionListener()
		       {                                                                                                                                                                                                                                                                                                                     
		         

				public void actionPerformed(ActionEvent e)
		         {
		          
		        	 System.out.println("Train:==>"+SMain.this.jtext1.getText());
			         System.out.println("PN:==>"+SMain.this.jtext2.getText());
			         
			         
			         try {
			        	 
			        	 String pn_no = SMain.this.jtext2.getText()+"["+tf.format(new Date())+"]";
			        	 System.out.println("pn no==>"+pn_no);
			        	 sd.update_lcstate(SMain.this.jtext1.getText()+"@"+pn_no);
				         sd.insert_smdata(SMain.this.jtext1.getText(),"CLOSE",pn_no,SMain.this.sm_nm.getText());
						 sp.getViewport().add(getTable(column));
						 
						 SMain.this.jtext1.setText("");  //clearing
						 SMain.this.jtext2.setText("");
						 bg.clearSelection();
						 
						 Wait_Ack wc = new Wait_Ack();
						 Thread thr = new Thread(wc);
					     thr.start();
								
						 
						/* for(;;)
						 {
							int res = sd.callAck();
							if(res==1)
							{
								 sp.getViewport().add (getTable(column));
								 break;
							}else
							{
								// sp.getViewport().add(getTable(column));
							}
							Thread.sleep(5000);
						 }
						 
						 for(;;)
						 {
							int res = sd.callLCState();
							if(res==0)
							{
								 sp.getViewport().add (getTable(column));
								 break;
							}else
							{
								// sp.getViewport().add(getTable(column));
							}
							Thread.sleep(5000);
						 } */
						 
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
			        
		         }
		       });
			  
			
			  
			  this.btn.addActionListener(new ActionListener()
		       {                                                                                                                                                                                                                                                                                                                     
		         public void actionPerformed(ActionEvent e)
		         {
		        	 try {
						java.lang.Runtime.getRuntime().exec("matchbox-keyboard");
					} catch (IOException e2) {
						
						e2.printStackTrace();
					}
		              tfp = new JTextField();
			          tfp.setFont(new Font("Arial", Font.BOLD, 35));

			          JPanel jp = new JPanel(new BorderLayout(0, 0));
			         // jp.setBackground(new Color(0, 0, 0));
			          
			          JLabel label = new JLabel("Enter PASSWORD");
		             // label.setForeground(new Color(255, 255, 0));
		              label.setHorizontalAlignment(SwingConstants.CENTER);
		              label.setFont(new Font("Arial", Font.BOLD, 30));
		              label.setBounds(0, 20, 300, 35);
		             // label.setBounds(0, 0, 300, 32);

			             jp.add(label, BorderLayout.NORTH);
			             jp.add(tfp, BorderLayout.SOUTH);
			             UIManager.put("OptionPane.minimumSize",new Dimension(300, 160));
			             JOptionPane.showMessageDialog(null,jp,"Login",1);
			         
			             String pwd = tfp.getText();
		              
		            //  String pwd = JOptionPane.showInputDialog(null, panel, "Login", JOptionPane.QUESTION_MESSAGE);
		              System.out.println("password==>"+pwd);
			         
		              try {
						String lgn[] = sd.Login_check(pwd).split("#");
						int sts = Integer.parseInt(lgn[1]);
						
						if(sts == 1){
							JOptionPane.showMessageDialog(SMain.this,"Login Successful");  
							sm_nm.setText(lgn[0]);
							java.lang.Runtime.getRuntime().exec("pkill -9 -f matchbox-keyboard");
						}else{
							 JOptionPane.showMessageDialog(SMain.this,"Wrong Password","Alert",JOptionPane.WARNING_MESSAGE); 
							 sm_nm.setText("--");
							 java.lang.Runtime.getRuntime().exec("pkill -9 -f matchbox-keyboard");
						}
							
					} catch (Exception e1) {
						
						e1.printStackTrace();
					}
			         
			       //  JOptionPane.showMessageDialog(SMain.this, "Gate Started Message Send and Status : "+res); 
			         
		         }
		       });
			 
			/*  this.tfp.addKeyListener(new KeyAdapter()
		       {
		         public void keyTyped(KeyEvent e)
		        {
		          	 
		        	 try {
						java.lang.Runtime.getRuntime().exec("matchbox-keyboard");
					} catch (IOException e1) {
						
						e1.printStackTrace();
					}
		           
		         }
		      }); */
			  
			  Main_Thread mr = new Main_Thread();
			  Thread mt = new Thread(mr);
			  mt.start();
		       
		    
		      setSize(x, y);
		      setDefaultCloseOperation(3);
		     }
		   
	 
			 
		 
	 /*  private void jtext1FocusLost(FocusEvent evt)
		  {  
		  		String sno = this.jtext1.getText();
		   //if (sno.length() >= 8)
		     if (sno.length() >= 6)
		  							{
		    
		      this.jtext1.setText(sno.toUpperCase());
		    }
		  } */
		    private void addComponent(Container container, Component c, int x, int y, int width, int height)
		    {
		      c.setBounds(x, y, width, height);
		      container.add(c);
		     }
		
		    
		    public JTable getTable(String col[])throws Exception{
		       // JTable t1=new JTable();
		        
		         t1 = new JTable()
		        {
			        @Override
			        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
			        Component comp = super.prepareRenderer(renderer, row, column);
			        comp.setBackground(row % 2 == 0 ? Color.decode("#FAFAFA") : new Color(255,204,204) );  // new Color(255,240,245) new Color(255,204,204) Color.decode("#E4ECF7")
			        return comp;
			        }
		        };
		        
		        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)t1.getDefaultRenderer(Object.class);
		        renderer.setHorizontalAlignment( SwingConstants.CENTER );
		        
		        Font font = new Font("Arial Black", Font.BOLD, 24);
		        
		        t1.setTableHeader(new JTableHeader(t1.getColumnModel()) {
		        	  @Override public Dimension getPreferredSize() {
		        	    Dimension d = super.getPreferredSize();
		        	    d.height = 33;
		        	    return d;
		        	  }
		        	});
		        JTableHeader tableHeader = t1.getTableHeader();
		        tableHeader.setBackground(Color.decode("#4286a6"));  //4286a6 60A0BF
		        tableHeader.setForeground(Color.white);
		        tableHeader.setFont(font);
		        
		        Font font1 = new Font("Arial", Font.PLAIN, 27);
		        t1.setFont(font1);
		        t1.setRowHeight(33);
		        
		       // t1.setShowGrid(true);
		        
		        DefaultTableModel dm=new DefaultTableModel();
		        Connection con = new DBUtil().getConnection();
				PreparedStatement ps = null;
			    ResultSet rs=null;
			   // String sql = "select * from sm_data where date_time like '"+today+" %%:%%:%%' order by srlno desc;";
		        String sql = "select * from sm_data where date_time like '"+today+" %%:%%:%%'";
		        ps = con.prepareStatement(sql);
		        rs=ps.executeQuery();
		       /* ResultSetMetaData rsmd=rs.getMetaData();
		        int cols=rsmd.getColumnCount(); */
		        String c[]=new String[col.length];
		        for(int i=0;i<col.length;i++){
		           // c[i]=rsmd.getColumnName(i+1);
		            dm.addColumn(col[i]);
		        }
		        //get data from rows
		        int cnt = 0;
		        Object row[]=new Object[col.length];
		        while(rs.next()){
		             for(int i=0;i<col.length;i++){
		            	 
		            	   if(i==0){
		            		   row[i] = ++cnt;
		            	   }else{
		                     row[i]=rs.getString(i+1);
		            	   }
		                   // System.out.println(row[i]);
		                }
		            dm.addRow(row);
		            
		        }
		      // t1.setBorder(BorderFactory.createCompoundBorder());
		      // t1.setShowGrid(true);
		      //  t1.setShowVerticalLines(true);
		      //  t1.setShowVerticalLines(true);
		        t1.setModel(dm);
		        TableColumnModel columnModel = t1.getColumnModel();
		        columnModel.getColumn(0).setPreferredWidth(90);
		        columnModel.getColumn(1).setPreferredWidth(160);
		        columnModel.getColumn(2).setPreferredWidth(125);
		        columnModel.getColumn(3).setPreferredWidth(150);
		        columnModel.getColumn(4).setPreferredWidth(75);
		        columnModel.getColumn(5).setPreferredWidth(115);
		        columnModel.getColumn(6).setPreferredWidth(220);
		        columnModel.getColumn(7).setPreferredWidth(150);
		        con.close();
		        
		       
		        return t1;
		    }
		    
		    private static class RoundedBorder implements Border {

		        private int radius;


		        RoundedBorder(int radius) {
		            this.radius = radius;
		        }


		        public Insets getBorderInsets(Component c) {
		            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
		        }


		        public boolean isBorderOpaque() {
		            return true;
		        }

		        @Override
		        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
		        }


		    }
     
}
