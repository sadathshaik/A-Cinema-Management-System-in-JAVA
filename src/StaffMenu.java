import java.sql.*;
import java.util.*;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class StaffMenu 
{
	StaffMenu()
	{
		String message = "welcome" + "\n", response;
		  message += "\n" + "Enter your Choice";
		  message += "\n" + "  1 to Buy Ticket";
		  message += "\n" + "  2 to Add Data";
		  message += "\n" + "  3 to Delete Data";
		  message += "\n" + "  4 to Modify Data";
		  message += "\n" + "  5 to exit" + "\n" + " ";
		
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
					   		case 1: new SearchMovie();
					   				 break;
							case 2:  AddData obja = new AddData();
									 if(obja.choice==1)
									 {
										 obja.AddMovie();
									 }
									 if(obja.choice==2)
									 {
										 obja.AddPerson();
									 }
									 if(obja.choice==3)
									 {
										 obja.AddDirector();
									 }
									 if(obja.choice==4)
									 {
										 obja.AddWriter();
									 }
									 if(obja.choice==5)
									 {
										 obja.AddActor();
									 }
									 if(obja.choice==6)
									 {
										 obja.AddShowing();
									 }

							         break;						         
							case 3:  DeleteData objd = new DeleteData();
									if(objd.choice==1)
									 {
										 objd.DeleteMovie();
									 }
									 if(objd.choice==2)
									 {
										 objd.DeletePerson();
									 }
									 if(objd.choice==3)
									 {
										 //objd.DeleteShowTime();
									 }
									 break;
							case 5:  System.exit(1);
							 		 break;
									 
							default: 
							{ 
									answer = 'Y'; choice = 0;
									JOptionPane.showMessageDialog(null,"enter a number:1-5");
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
