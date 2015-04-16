import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String; 
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Random;


public class SearchMovie
{

	int flag=0, rowsfound=1;
	String selectedMovieName="", selectedDate="";
	
	SearchMovie()
	{
		try 
		{
			JCheckBox C1 = new JCheckBox("Movie");
			JCheckBox C2 = new JCheckBox("Director");
			JCheckBox C3 = new JCheckBox("Actor");
			JCheckBox C4 = new JCheckBox("Writer");
			JCheckBox C5 = new JCheckBox("Display All Movies");
			JTextField searchField = new JTextField(20);
			
			 
			
			JPanel jp = new JPanel();
			jp.add(C1);
			jp.add(C2);
			jp.add(C3);
			jp.add(C4);
			jp.add(C5);
			
			int size=0, count=1;
			String sql="";
	
			Class.forName(TicketSystem.driver).newInstance();
			Connection conn = DriverManager.getConnection(TicketSystem.url,TicketSystem.userName,TicketSystem.password); //creating connection and connecting to the database
			Statement st=conn.createStatement(); //object for statement
			
			
			//////Movies List
			sql = " SELECT COUNT(*) AS TOTCOUNT FROM MOVIE";

			ResultSet rs=st.executeQuery(sql);
		
			rs.next(); //executing the query with this next command
			size = rs.getInt("TOTCOUNT");
			
			String[] movieArray = new String[size+1];
			
			sql = " SELECT TITLE FROM MOVIE";


			rs=st.executeQuery(sql);
			
			movieArray[0]="";
			
			while(rs.next()) //executing the query with this next command
			{
				movieArray[count] = rs.getString("TITLE");
				count++;
			}
			count=1;
			JComboBox<String> listMovie = new JComboBox<String>(movieArray);
			
			///date
			String[] arrayDate = new String[3];
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		    arrayDate[0] = formatter.format(date);
		    
		    date.setDate(date.getDay()+1);
		    arrayDate[1] = formatter.format(date);

		    date.setDate(date.getDay()+1);
		    arrayDate[2] = formatter.format(date);
		    
		    JComboBox<String> movieDay = new JComboBox<String>(arrayDate);

	
			Object[] fields = 
				{
					"Select the Search Criteria", jp,
//					"Select the Director", listDirector,
					"Select the Movie", listMovie,
					"Select Day", movieDay,
					"Enter the search text", searchField
				};
			
		      int result = JOptionPane.showConfirmDialog(null, fields, 
		               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
		      
      
		      ///Final movie names
		      if(listMovie.getSelectedIndex()==0 && listMovie.getSelectedIndex()!=1)
		      {
			  			sql = " TRUNCATE TABLE SEARCHMOVIE" ;
			  			rs=st.executeQuery(sql);
			  			
			    	  if(C1.isSelected()==true)
			    	  {
			  			sql = " INSERT INTO SEARCHMOVIE(ID, NAME, SEARCHTEXT)"
			  					+ " SELECT DISTINCT ID, TITLE, '" + searchField.getText() + "'  FROM MOVIE WHERE ID NOT IN (SELECT ID FROM SEARCHMOVIE)"
			  							+ " AND UPPER(TITLE) LIKE '%" + searchField.getText().toUpperCase() + "%'" ;
			  			
						rs=st.executeQuery(sql);
					
			    	  }
			    	  if(C2.isSelected()==true)
			    	  {
			  			sql = " INSERT INTO SEARCHMOVIE(ID, NAME, SEARCHTEXT)"
			  					+ " SELECT DISTINCT M.ID, M.TITLE, '" + searchField.getText() + "'  FROM MOVIE M"
			  							+ "	INNER JOIN DIRECTOR D ON (D.MovieID=M.ID)"
			  							+ " INNER JOIN PERSON P ON (D.ID=P.ID) WHERE M.ID NOT IN (SELECT ID FROM SEARCHMOVIE) "
			  							+ " AND UPPER(P.NAME) LIKE '%" + searchField.getText().toUpperCase() + "%'" ;
						rs=st.executeQuery(sql);
						
			    	  }
			    	  if(C3.isSelected()==true)
			    	  {
			  			sql = " INSERT INTO SEARCHMOVIE(ID, NAME, SEARCHTEXT)"
			  					+ " SELECT DISTINCT M.ID, M.TITLE, '" + searchField.getText() + "'  FROM MOVIE M"
			  							+ "	INNER JOIN ACTOR D ON (D.MovieID=M.ID)"
			  							+ " INNER JOIN PERSON P ON (D.ID=P.ID) WHERE M.ID NOT IN (SELECT ID FROM SEARCHMOVIE) "
			  							+ " AND UPPER(P.NAME) LIKE '%" + searchField.getText().toUpperCase() + "%'" ;
			  			
			  			rs=st.executeQuery(sql);
					
			    	  }
			    	  if(C4.isSelected()==true)
			    	  {
			  			sql = " INSERT INTO SEARCHMOVIE(ID, NAME, SEARCHTEXT)"
			  					+ " SELECT DISTINCT M.ID, M.TITLE, '" + searchField.getText() + "'  FROM MOVIE M"
			  							+ "	INNER JOIN WRITERS D ON (D.MovieID=M.ID)"
			  							+ " INNER JOIN PERSON P ON (D.ID=P.ID) WHERE M.ID NOT IN (SELECT ID FROM SEARCHMOVIE) "
			  							+ " AND UPPER(P.NAME) LIKE '%" + searchField.getText().toUpperCase() + "%'" ;
			  			rs=st.executeQuery(sql);
					
			    	  }
			    	  
		      }
		      else
		      {
		    	  	sql = " TRUNCATE TABLE SEARCHMOVIE" ;
		  			rs=st.executeQuery(sql);
		  			
		  			sql = " INSERT INTO SEARCHMOVIE(ID, NAME)"
		  					+ " SELECT ID, TITLE FROM MOVIE WHERE TITLE ='"+ movieArray[listMovie.getSelectedIndex()] + "'" ;
		  			
					rs=st.executeQuery(sql);
					flag=1;
					selectedMovieName =  movieArray[listMovie.getSelectedIndex()];
					selectedDate =  arrayDate[movieDay.getSelectedIndex()];
		      }
		      
		      if(flag==0)
		      {
						sql = " SELECT COUNT(*) AS TOTCOUNT FROM SEARCHMOVIE";
		
						rs=st.executeQuery(sql);
					
						rs.next(); //executing the query with this next command
						size = rs.getInt("TOTCOUNT");

										
						System.out.println(size);
						if(size==0)
						{
							JOptionPane.showMessageDialog(null, " No Mathces found ");
							rowsfound=0;
						}
						else if(size>=1)
						{
					    	
					    	  count=0;
					    		 
					  			//////Movies List
					  			sql = " SELECT COUNT(*) AS TOTCOUNT FROM SEARCHMOVIE";
			
					  			rs=st.executeQuery(sql);
					  		
					  			rs.next(); //executing the query with this next command
					  			size = rs.getInt("TOTCOUNT");
					  			
					  			String[] searchList = new String[size];
					  			
					  			sql = " SELECT NAME FROM SEARCHMOVIE";
			
					  			rs=st.executeQuery(sql);
					  					  			
					  			while(rs.next()) //executing the query with this next command
					  			{
					  				searchList[count] = rs.getString("NAME");
					  				count++;
					  			}
					  					  			
					  			JComboBox<String> searchResult = new JComboBox<String>(searchList);
					  			
							      Object[] finalfields =
							    	  {
							    		  "Search Resultset Movies list", searchResult,
							    		  "Select Day", movieDay
						  
							    	  };
							     
							    result = JOptionPane.showConfirmDialog(null, finalfields, 
							               "Please Enter the required fileds", JOptionPane.PLAIN_MESSAGE);
							    if(result == JOptionPane.OK_OPTION)
							      {
							    	 selectedMovieName = searchList[searchResult.getSelectedIndex()];
							    	 selectedDate =  arrayDate[movieDay.getSelectedIndex()];
							      }
							    
							    
						}
		      }

		      if(rowsfound==1)
		      {
			  			//////Movies List
			  			count=0;
			  			sql = " SELECT COUNT(*) AS TOTCOUNT FROM SHOWING S"
			  					+ "	INNER JOIN MOVIE M ON (M.ID=S.MOVIEID)"
			  					+ " WHERE M.TITLE ='"+ selectedMovieName + "'";
	
			  			rs=st.executeQuery(sql);
			  		
			  			rs.next(); //executing the query with this next command
			  			size = rs.getInt("TOTCOUNT");
			  			
			  			String[] timeArray = new String[size];
			  			
			  			sql = " SELECT S.TIME AS TIMING FROM SHOWING S"
			  					+ "	INNER JOIN MOVIE M ON (M.ID=S.MOVIEID)"
			  					+ " WHERE M.TITLE ='"+ selectedMovieName + "'";
	
			  			rs=st.executeQuery(sql);
			  				  			
			  			while(rs.next()) //executing the query with this next command
			  			{
			  				timeArray[count] = rs.getString("TIMING");
			  				count++;
			  			}
			  						  					  			
			  			JComboBox<String> listTime = new JComboBox<String>(timeArray);
			  			
			  			 Object[] finaltime =
					    	  {
					    	
					    		  "Select Time", listTime
				  
					    	  };
					     

					      result = JOptionPane.showConfirmDialog(null, finaltime, 
					               "Time Selection", JOptionPane.PLAIN_MESSAGE);
					     
					      if(result==JOptionPane.OK_OPTION)
					      {
					    	  String[] nooftickets = {"1","2","3","4","5"};
					    	  JComboBox<String> tickets = new JComboBox<String>(nooftickets);
					    	  
					    	  
					    	  int available;
					    	
					    	  sql="SELECT AVAILABLE FROM SHOWING S "
					    	  		+ "	WHERE S.MOVIEID=(SELECT ID FROM MOVIE M WHERE M.TITLE='"+selectedMovieName+"' AND TIME ='"+timeArray[listTime.getSelectedIndex()] +"')";
					    	
							  JTextField TAvailable = new JTextField(20);
								
					    	  rs=st.executeQuery(sql);
					    	  
					    	  rs.next(); //executing the query with this next command
					    	  available = rs.getInt("AVAILABLE");

							  TAvailable.setText(Integer.toString(available));
							  TAvailable.setEditable(false);
							  
					  			
							      Object[] finalfields =
							    	  {
							    		  "No Of Tickets", tickets,
							    		  "Available Tickets", TAvailable,
						  
							    	  };
							     
							    result = JOptionPane.showConfirmDialog(null, finalfields, 
							               "Please Select No of tickets", JOptionPane.PLAIN_MESSAGE);
							
							    if(result==JOptionPane.OK_OPTION)
							      {
							    	String[] cardTypes = {"VISA", "Master", "AmericanExpress"};
							    	String[] payMethod = {"Credit", "Debit"};
							    	
							    	JTextField TPrice = new JTextField(20);
							    	int price = Integer.parseInt(nooftickets[tickets.getSelectedIndex()])*20;
							    	TPrice.setText(Integer.toString(price)+"$");
							    	TPrice.setEditable(false);
							    	
							    	JComboBox<String> cardArray = new JComboBox<String>(cardTypes);
							    	JComboBox<String> payArray = new JComboBox<String>(payMethod);
							    	
							    	JTextField TCardNO = new JTextField(20);
							    	JTextField TCardName = new JTextField(20);
							    	JTextField TCardExpDate = new JTextField(20);
							    	JTextField TCardCVV = new JTextField(20);
							    	
							    	Object[] finalbuy =
								    	  {
							    			  "Total Price", TPrice,
								    		  "Card Type", cardArray,
								    		  "Payement Method", payArray,
								    		  "Card Number", TCardNO,
								    		  "Name On Card", TCardName,
								    		  "Expiry Date", TCardExpDate,
								    		  "CVV ", TCardCVV		  
								    	  };

							    	result = JOptionPane.showConfirmDialog(null, finalbuy, 
								               "Please Select Billing Fields", JOptionPane.PLAIN_MESSAGE);
								
								    if(result==JOptionPane.OK_OPTION)
								      {
								    	sql ="INSERT INTO CARD(ID, CARDNUMBER, PAYMENTMETHOD, CARDTYPE, NAMEONCARD, EXPIRYDATE, CVV, USERID)"
								    			+ "	SELECT NVL(MAX(ID),0)+1, '" + TCardNO.getText() + "', '" + payMethod[payArray.getSelectedIndex()] + "', '" + cardTypes[cardArray.getSelectedIndex()] + "', '"+
								    						TCardName.getText() + "', DATE '" + TCardExpDate.getText() + "', " + TCardCVV.getText() + ", " + Login.loginUserID + " FROM CARD";
								    	rs=st.executeQuery(sql);
								    	//System.out.println(sql);
								    	
								    	String message= "\t\t***********Payment Successful***********\n\n" +
								    					"\t\t*****Thank you*****  " + Login.enteredUserName + "\n" + 
								    					"Movie Name: " + selectedMovieName + "\n" +
								    					"Date: " + selectedDate  + "\n" +
								    					"Time: " + timeArray[listTime.getSelectedIndex()]  + "\n" +
								    					"No of Persons: " + nooftickets[tickets.getSelectedIndex()]+ "\n" +
								    					"Total Price: " + TPrice.getText() + "\n";
								    	
								    	
								    	sql = "UPDATE SHOWING SET AVAILABLE = AVAILABLE - " + nooftickets[tickets.getSelectedIndex()]  + " WHERE MOVIEID= (SELECT ID FROM MOVIE WHERE TITLE='" + selectedMovieName +"') "
								    			+ " AND TIME = '" + timeArray[listTime.getSelectedIndex()] + "'";
								    	rs=st.executeQuery(sql);
								    	result = JOptionPane.showConfirmDialog(null, message);
								    	
								      }
							    	
							    	
							      }
							    
					      }
		      }

		      
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
	}
	



}

