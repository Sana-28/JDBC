package com.bridgelabz.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection connection=null;
		Statement statement=null;
		
		String query="UPDATE employee SET emlpoyee_name = 'Alfred' WHERE employee_id = '103'  ";
		       
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db","root","root");
			System.out.println("Connection established with database..");
			
			statement=connection.createStatement();
			System.out.println("Platform or statement Created..");
			
			statement.executeUpdate(query);
			System.out.println("Data Updated Successfully..");
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		finally
		{
			if(statement!=null)
			{
				try
				{
					statement.close();
				}catch(SQLException e)
				{
				 System.out.println(e);
				}
				if(connection!=null)
				{
					try{
						connection.close();
					}catch (Exception e) {
						 System.out.println(e);
					}
				}
			}
		}
	}

}
