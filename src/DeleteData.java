import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DeleteData 
{
	  int choice=0, count=0, size=0;
	  String sql;
	  ResultSet rs;
	  
	DeleteData()
	{
		

		String message = "welcome" + "\n", response;
		  message += "\n" + "Enter your Choice";
		  message += "\n" + "  1 to Delete Movie";
		  message += "\n" + "  2 to Delete Person";
		  message += "\n" + "  3 to Delete ShowTime";
		  message += "\n" + "  4 to exit" + "\n" + " ";
		

		 char answer = 'Y';
		
				   try 
				   {  
					   	response  = JOptionPane.showInputDialog(message);
					   	
					    char ch=response.charAt(0);
					    if(Character.isLetter(ch))	
					    	JOptionPane.showMessageDialog(null,"Please enter your choice");
					    else
					    {		
					    	choice = Integer.parseInt(response);
					    }
				  }//end try
				  
				  catch (Exception e ) 
				  { 
					   System.out.println(e); 
				  }
			  
	}
	
	void DeleteMovie() throws Exception
	{
		try
			{
			
			      Class.forName(TicketSystem.driver).newInstance();
			      Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
			      Statement st=conn.createStatement(); //object for statement
			      
				//////Movies List
				sql = " SELECT COUNT(*) AS TOTCOUNT FROM MOVIE";

				rs=st.executeQuery(sql);
			
				rs.next(); //executing the query with this next command
				size = rs.getInt("TOTCOUNT");
				
				String[] movieArray = new String[size];
				
				sql = " SELECT TITLE FROM MOVIE";


				rs=st.executeQuery(sql);
				
				
				while(rs.next()) //executing the query with this next command
				{
					movieArray[count] = rs.getString("TITLE");
					count++;
				}
				
				JComboBox<String> listMovie = new JComboBox<String>(movieArray);
				
			    
			      Object[] fields =
			    	  {
			    		  "Select the Movie to be deleted", listMovie
		  
			    	  };
			     
			      
			      int result = JOptionPane.showConfirmDialog(null, fields, 
			               "Please Select the movie", JOptionPane.PLAIN_MESSAGE);
			      
		
			      if (result == JOptionPane.OK_OPTION) 
			      {
						
						
						sql = "DELETE FROM DIRECTOR WHERE MovieID= ( SELECT ID FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "')";

						st.execute(sql);
						
						sql = "DELETE FROM ACTOR WHERE MovieID= ( SELECT ID FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "')";

						st.execute(sql);
						
						sql = "DELETE FROM SHOWING WHERE MovieID= ( SELECT ID FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "')";

						st.execute(sql);

						sql = "DELETE FROM WRITERS WHERE MovieID= ( SELECT ID FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "')";

						st.execute(sql);

						sql = "DELETE FROM MOVIE WHERE ID= ( SELECT ID FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "')";

						st.execute(sql);

						JOptionPane.showMessageDialog(null, " Movie Details Deleted");
						
		
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
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
	}

	void DeletePerson() throws Exception
	{
		try
			{
			
				JCheckBox C2 = new JCheckBox("Director");
				JCheckBox C3 = new JCheckBox("Actor");
				JCheckBox C4 = new JCheckBox("Writer");
				
				JPanel jp = new JPanel();
				jp.add(C2);
				jp.add(C3);
				jp.add(C4);
			
			      Class.forName(TicketSystem.driver).newInstance();
			      Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
			      Statement st=conn.createStatement(); //object for statement
			      
			    //////Movies List
				count=0;
				sql = " SELECT COUNT(*) AS TOTCOUNT FROM PERSON";
	
				rs=st.executeQuery(sql);
			
				rs.next(); //executing the query with this next command
				size = rs.getInt("TOTCOUNT");
				
				String[] personArray = new String[size];
				
				sql = " SELECT NAME FROM PERSON";
	
	
				rs=st.executeQuery(sql);
				
				
				while(rs.next()) //executing the query with this next command
				{
					personArray[count] = rs.getString("NAME");
					count++;
				}
				
				JComboBox<String> listPerson = new JComboBox<String>(personArray);
				
			    
			      Object[] fields =
			    	  {
			    		  "Select the Person to be deleted", listPerson,
			    		  "Check the requried Casts", jp
			    	  };
			     
			      
			      int result = JOptionPane.showConfirmDialog(null, fields, 
			               "Select the Person", JOptionPane.PLAIN_MESSAGE);
			      
		
			      if (result == JOptionPane.OK_OPTION) 
			      {
			    	  if(C2.isSelected()==true)
			    	  {
						sql = "DELETE FROM DIRECTOR WHERE ID=( SELECT ID FROM PERSON WHERE NAME ='"+ personArray[listPerson.getSelectedIndex()] + "')";

						st.execute(sql);
			    	  }
			    	  if(C3.isSelected()==true)
			    	  {
						sql = "DELETE FROM ACTOR WHERE ID=( SELECT ID FROM PERSON WHERE NAME ='"+ personArray[listPerson.getSelectedIndex()] + "')";

						st.execute(sql);
			    	  }
			    	  if(C4.isSelected()==true)
			    	  {
						sql = "DELETE FROM WRITERS WHERE ID=( SELECT ID FROM PERSON WHERE NAME ='"+ personArray[listPerson.getSelectedIndex()] + "')";

						st.execute(sql);
			    	  }
			    	  
												
						JOptionPane.showMessageDialog(null, " Person Details Deleted");
						
		
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
			}
			catch (Exception e) 
			{
				System.out.println(e.getMessage());
			}
	}

	

}
