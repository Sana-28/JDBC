package com.bridgelabz.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**Purpose: This program is to establish a connection with DataBase
 * and fetch all the records of DataBase
 * @author SANA SHAIKH
 *
 */
public class MySqlConnection {

	public static void main(String[] args) {
		
		System.out.println("-- MySQL JDBC Connection Testing --");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC driver Registered..");
			
			Connection connection = DriverManager.getConnection
					("jdbc:mysql://localhost:3306/bidgelabz_db","root","root");

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from employee");

			while (resultSet.next())
				System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2));
			connection.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
