import javax.swing.*;

import java.lang.String; 
import java.sql.*;

	public class Login 
	{
		public static int loginUserID=0, IsStaff=0;
		public static String enteredUserName="";
		
	
		public void userLogin() throws Exception 
		{
				boolean access = false;	
				int count=0, totcount;
				String sql, enteredpassword, originalpassword;
				
				
				while(count<3)
				{
					JTextField loginname = new JTextField(20);
				    JPasswordField upassword = new JPasswordField(20);
				    			     
				    JComponent[] inputs = new JComponent[] 
				    {
				    		new JLabel("Login Name"), loginname,
				    		new JLabel("Password"), upassword
					};
				    
				    
				      int result = JOptionPane.showConfirmDialog(null, inputs, 
				               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
				      if (result == JOptionPane.OK_OPTION) 
				      {
				    
					    	enteredpassword = upassword.getText();
							
					    	Class.forName(TicketSystem.driver).newInstance();
							Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
							Statement st=conn.createStatement(); //object for statement
							
							sql = " SELECT COUNT(*) AS TOTCOUNT FROM USERCRED WHERE LOGINNAME='" + loginname.getText() + "'";
							
							ResultSet rs=st.executeQuery(sql);
							
						
							rs.next(); //executing the query with this next command
							totcount = rs.getInt("TOTCOUNT"); //storing the arrayzise ...this is required to define the array size which is based on the table size

							//enters if it is valid user name
							if (totcount==1) 
					    	{ 
							
								sql = " SELECT NAME, ID, ISSTAFF, PASSWORD FROM USERCRED WHERE LOGINNAME='" + loginname.getText() + "'";
								rs=st.executeQuery(sql);
								
								rs.next(); //executing the query with this next command
								enteredUserName = rs.getString("NAME");
								loginUserID = rs.getInt("ID");
								IsStaff = rs.getInt("ISSTAFF");
								originalpassword = rs.getString("PASSWORD");
								//System.out.println(sql+" "+IsStaff);						
								
								
								
							    if (enteredpassword.equals(originalpassword)) 
							    { 
							      access = true;
							      break;
							    }
							    else
							    	JOptionPane.showMessageDialog(null, "Incorrect password"); 
							  
							    count++;
							}
							else
							{
								JOptionPane.showMessageDialog(null, "Incorrect login name");
								count++;
			
							}
							conn.close();
				      }
				}
				if(access == true)
				{
					try 
					{
						if (IsStaff==1)
						{
							JOptionPane.showMessageDialog(null,"Hello " + enteredUserName + "\n\n" + "Permission: " + "IsStaff");
							new StaffMenu();
							
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Hello " + enteredUserName + "\n\n" + "Permission: " + "General User");
							new SearchMovie();
						}
							System.exit(1);
					}
					catch (Exception e) 
					{ 
						System.out.println(e);
					} 
				}
				else
					JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
				
				System.exit(1);
		}//end main
	}//end class
		
