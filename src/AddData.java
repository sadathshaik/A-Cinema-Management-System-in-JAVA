import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class AddData 
{
	  int choice=0, count=0, size=0;
	  String sql;
	  ResultSet rs;
	AddData()
	{
		

		String message = "welcome" + "\n", response;
		  message += "\n" + "Enter your Choice";
		  message += "\n" + "  1 to Add Movie";
		  message += "\n" + "  2 to Add Person";
		  message += "\n" + "  3 to Add Director";
		  message += "\n" + "  4 to Add Writer";
		  message += "\n" + "  5 to Add Actor";
		  message += "\n" + "  6 to Add ShowTime";
		  message += "\n" + "  7 to exit" + "\n" + " ";
		

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
	
	void AddMovie() throws Exception
	{
		try
		{
			
			JTextField movieTitle = new JTextField(20);
		    JTextField movieLength = new JTextField(20);
		    JTextField movieReleaseYear = new JTextField(20);
		    JTextField movieRating = new JTextField(20);
		    JTextField movieGenere = new JTextField(20);
		    
		      Object[] fields =
		    	  {
		    		  "Movie Title", movieTitle,
		    		  "Movie Length", movieLength,
		    		  "Release Year", movieReleaseYear,
		    		  "Users Average Rating", movieRating,
		    		  "Genere", movieGenere
	  
		    	  };
		     
		      
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
		      
	
		      Class.forName(TicketSystem.driver).newInstance();
		      Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
		      Statement st=conn.createStatement(); //object for statement
				
		      if (result == JOptionPane.OK_OPTION) 
		      {
					sql = "INSERT INTO MOVIE(ID, TITLE, LENGTH, RELEASE_YEAR, RATING, GENERE)"+
								" SELECT NVL(max(ID),0)+ 1, '" + movieTitle.getText() + "','" + movieLength.getText() + "'," + movieReleaseYear.getText() + "," + movieRating.getText() + ",'" + movieGenere.getText() +  "' FROM MOVIE";
	
					st.execute(sql);
					
					JOptionPane.showMessageDialog(null, " Movie Details Inserted");
					
	
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
	void AddPerson() throws Exception
	{
		try
		{
			
			JTextField name = new JTextField(20);
		    JTextField gender = new JTextField(20);
		    JTextField dob = new JTextField(20);

		      Class.forName(TicketSystem.driver).newInstance();
		      Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
		      Statement st=conn.createStatement(); //object for statement
		    
		    
		      Object[] fields =
		    	  {
		    		  "Name", name,
		    		  "Gender", gender,
		    		  "Date Of Birth(yyyy-mm-dd)", dob	
		    	  };
		     
		      
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
		      
	
		      if (result == JOptionPane.OK_OPTION) 
		      {
					sql = "INSERT INTO PERSON(ID, NAME, GENDER, DOB)"+
								" SELECT NVL(max(ID),0)+ 1, '" + name.getText() + "','" + gender.getText() + "', DATE '" + dob.getText() + "' FROM PERSON";
	
					st.execute(sql);
					JOptionPane.showMessageDialog(null, " Person Details Inserted");
					
	
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

	void AddDirector() throws Exception
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
			JComboBox<String> listMovie = new JComboBox<String>(movieArray);
			
		    
		      Object[] fields =
		    	  {
		    		  "Director Name", listPerson,
		    		  "Movie Directed", listMovie
	  
		    	  };
		     
		      
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
		      
	
		      if (result == JOptionPane.OK_OPTION) 
		      {
					
					sql = "INSERT INTO Director(ID, MOVIEID)"+
							" SELECT D.ID, M.ID "
							+ "	 FROM PERSON D,"
							+ "			MOVIE M "
							+ "				WHERE D.NAME= '" + personArray[listPerson.getSelectedIndex()] + "' "
									+ " AND M.TITLE='" + movieArray[listMovie.getSelectedIndex()] + "'";

					st.execute(sql);
					JOptionPane.showMessageDialog(null, " Director Details Inserted");
					
	
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
	void AddWriter() throws Exception
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
				JComboBox<String> listMovie = new JComboBox<String>(movieArray);
				
			    
			      Object[] fields =
			    	  {
			    		  "Director Name", listPerson,
			    		  "Movie Directed", listMovie
		  
			    	  };
			     
			      
			      int result = JOptionPane.showConfirmDialog(null, fields, 
			               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
			      
	
		      if (result == JOptionPane.OK_OPTION) 
		      {					
					
					sql = "INSERT INTO Writers(ID, MOVIEID)"+
							" SELECT D.ID, M.ID "
							+ "	 FROM PERSON D,"
							+ "			MOVIE M "
							+ "				WHERE D.NAME= '" + personArray[listPerson.getSelectedIndex()] + "' "
									+ " AND M.TITLE='" + movieArray[listMovie.getSelectedIndex()] + "'";

					st.execute(sql);
					JOptionPane.showMessageDialog(null, " Writer Details Inserted");
					
	
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
	void AddActor() throws Exception
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
				JComboBox<String> listMovie = new JComboBox<String>(movieArray);
				JTextField role = new JTextField(20);
				
			    
			      Object[] fields =
			    	  {
			    		  "Director Name", listPerson,
			    		  "Movie Directed", listMovie,
			    		  "Role played", role
		  
			    	  };
			     
			      
			      int result = JOptionPane.showConfirmDialog(null, fields, 
			               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
			      
		      
	
		      if (result == JOptionPane.OK_OPTION) 
		      {					
					
					sql = "INSERT INTO Actor(ID, MOVIEID, ROLE)"+
	
					" SELECT D.ID, M.ID, '" + role.getText() + "'"
					+ "	 FROM PERSON D,"
					+ "			MOVIE M "
					+ "				WHERE D.NAME= '" + personArray[listPerson.getSelectedIndex()] + "' "
							+ " AND M.TITLE='" + movieArray[listMovie.getSelectedIndex()] + "'";

					
					st.execute(sql);
					JOptionPane.showMessageDialog(null, " Actor Details Inserted");
					
	
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
	void AddShowing() throws Exception
	{
		
		try
		{
		    JTextField available = new JTextField(20);
		    JTextField showtime = new JTextField(20);
		

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
			
			//screen
			sql = " SELECT COUNT(*) AS TOTCOUNT FROM SCREEN";

			rs=st.executeQuery(sql);
		
			rs.next(); //executing the query with this next command
			size = rs.getInt("TOTCOUNT");
			String[] screenArray = new String[size];
			
			sql = " SELECT SCREEN FROM SCREEN";


			rs=st.executeQuery(sql);
			
			count=0;
			
			while(rs.next()) //executing the query with this next command
			{
				screenArray[count] = rs.getString("SCREEN");
				count++;
			}
			
			JComboBox<String> listScreen = new JComboBox<String>(screenArray);
			
		    
		      Object[] fields =
		    	  {
		    		  "Movie", listMovie,
		    		  "Screen", listScreen,
		    		  "Show Time", showtime,
		    		  "Screen Capacity", available
		    	  };
		     
		      
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
		      
	
		      if (result == JOptionPane.OK_OPTION) 
		      {
					sql = "INSERT INTO SHOWING(ID, MOVIEID, SCREEN_ID, AVAILABLE, TIME)"+
								" SELECT NVL(max(ID),0)+ 1, " + listMovie.getSelectedIndex()+  "+ 1," + listScreen.getSelectedIndex() + " + 1, " + available.getText() + ",  '" + showtime.getText() + "' FROM SHOWING";
	
					//System.out.println(sql);
					st.execute(sql);

					JOptionPane.showMessageDialog(null, " Show Time Details Inserted");
					
	
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
