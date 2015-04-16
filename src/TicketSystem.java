

import java.sql.*;
import java.util.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

	public class TicketSystem
	{

		//these are the variables that stores the jdbc connection details
		public static String url = "jdbc:oracle:thin:@fourier.cs.iit.edu:1521:orcl"; 	//jdbc connection url
		public static String dbName = "Final_Project" ;									//database name for which we need to connect
		public static String driver = "oracle.jdbc.driver.OracleDriver";		//jdbc connection driver
		public static String userName = "mshaik"; 								//db connection username
		public static String password = "";							//db connection password

		public static void main(String[] args) throws Exception 
		{

			String message = "welcome" + "\n", response;
			  message += "\n" + "Enter your Choice";
			  message += "\n" + "  1 to New User";
			  message += "\n" + "  2 to Existing User";
			  message += "\n" + "  3 to exit" + "\n" + " ";

	   char answer = 'Y';

	   do 
	   {
		   try 
		   {  
			   	response  = JOptionPane.showInputDialog(message);
			   	
			    char ch=response.charAt(0);
			    if(Character.isLetter(ch))	
			    	JOptionPane.showMessageDialog(null,"Please enter your choice");
			    else
			    {		
			    	int choice = Integer.parseInt(response);
				   	switch (choice) 
				   	{
				   		case 1: new CreateUser();
				   				 break;
						case 2:  Login obj = new Login();
									obj.userLogin();
						         break;						         
						case 3:  answer = 'N';  System.exit(1);
								 break;
						default: 
						{ 
								answer = 'Y'; choice = 0;
								JOptionPane.showMessageDialog(null,"enter a number:1-3");
						} 
				   	}//end switch
			    }
		   }//end try
		   
		   catch (Exception e ) 
		   { 
			   System.out.println(e); 
		   }
		   
	   }while(answer == 'Y' || answer == 'y'); 


		}
	}
