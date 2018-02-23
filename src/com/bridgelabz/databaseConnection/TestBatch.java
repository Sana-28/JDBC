package com.bridgelabz.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestBatch {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String insertQuery = "INSERT INTO employee " + "VALUES (11, 'Anas')";
		String updateQuery = "UPDATE employee SET emlpoyee_name = 'Alex' WHERE employee_id = '103'";
		String deleteQuery = "DELETE FROM employee " + "WHERE employee_id = 102";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");
			
			statement=connection.createStatement();
			statement.addBatch(insertQuery);
			statement.addBatch(updateQuery);
			statement.addBatch(deleteQuery);
			
			int[] arrayBatch=statement.executeBatch();
			for(int i: arrayBatch)
			{
				System.out.println("Affected rows:"+i);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					System.out.println(e);
				}
				if (connection != null) {
					try {
						connection.close();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
		}
	}
}
