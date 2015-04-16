import javax.swing.*;

import java.lang.String; 
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;

public class CreateUser
{


	public CreateUser() throws Exception 
	{
		try 
		{
			int userID=0, empID;
			java.util.Date curDate = new java.util.Date();
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
	
			//String tktDesc = null, tktCustName = null, tktCustMail = null, tktStatus = "OPEN";
			String tktStatus = "OPEN", empName="", sql;
			empID= Login.loginUserID;
			

			Random rand = new Random();

			// Generates a random ticket number
			userID = rand.nextInt(10001) + 1000; // Generates a  number from 1000 to 11000
			
			JTextField userName = new JTextField(20);
		    JTextField userAddress = new JTextField(20);
		    JTextField userEmail = new JTextField(20);
		    JTextField userLoginName = new JTextField(20);
		    JTextField userPassword = new JTextField(20);
		    JTextField userDOB = new JTextField(dateFormat.format(curDate));

		    
		      Object[] fields =
		    	  {
		    		  "Name", userName,
		    		  "Address", userAddress,
		    		  "EmailID", userEmail,
		    		  "Login UserName", userLoginName,
		    		  "Login Password", userPassword,
		    		  "Date of Birth(yyyy-mm-dd)", userDOB
	  
		    	  };
		     
		      
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);

		      Class.forName(TicketSystem.driver).newInstance();
		      Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
		      Statement st=conn.createStatement(); //object for statement
				
		      if (result == JOptionPane.OK_OPTION) 
		      {
					sql = "INSERT INTO USERCRED(ID, NAME, ADDRESS, EMAIL, LOGINNAME, PASSWORD, DOB, ISSTAFF)"+
								" VALUES ("+ Integer.toString(userID) + ",'" + userName.getText() + "','" + userAddress.getText() + "','" + userEmail.getText() + "','" + userLoginName.getText() + "','" + userPassword.getText() +  "', DATE '" + userDOB.getText() +"'," + Integer.toString(0) + ")";

					st.execute(sql);
					
					JOptionPane.showMessageDialog(null, "User Created. You can continue with Login");
					
					SearchMovie obj = new SearchMovie();
					

				      conn.close();
		      }
		      if (result == JOptionPane.CANCEL_OPTION) 
		      {
		    	  conn.close();
		      }
		      if (result == JOptionPane.CLOSED_OPTION) 
		      {
		    	  conn.close();
		      }
			
			
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}

	
}
