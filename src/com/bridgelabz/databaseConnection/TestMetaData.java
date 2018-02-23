package com.bridgelabz.databaseConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMetaData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		String query = "SELECT * from student";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database.." + "\n");

			DatabaseMetaData databaseMetaData = connection.getMetaData();
			System.out.println("Driver Name:" + databaseMetaData.getDriverName());
			System.out.println("Deriver Version:" + databaseMetaData.getDriverVersion());
			System.out.println("UserName: " + databaseMetaData.getUserName());
			System.out.println("Database Product Name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Database Product Version: " + databaseMetaData.getDatabaseProductVersion());

			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);

			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			System.out.println("Total Number of Columns:" + resultSetMetaData.getColumnCount());
			// System.out.println("Column Display Size:"+resultSetMetaData.getColumnDisplaySize(3));
			// System.out.println(resultSet);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
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
