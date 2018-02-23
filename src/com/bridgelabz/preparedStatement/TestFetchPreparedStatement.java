package com.bridgelabz.preparedStatement;

import java.sql.*;

public class TestFetchPreparedStatement {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		// String query="SELECT * from student";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			preparedStatement = connection.prepareStatement("SELECT * from student");
			System.out.println("Platform or statement Created..");

			resultSet = preparedStatement.executeQuery("SELECT * from student");
			System.out.println("\n"+"***Student Details***" + "\n");
			
			//Use while to fetch all Records
			if (resultSet.next())
				System.out.println(resultSet.getInt(1) + " \t" + resultSet.getString(2) + "\t" + resultSet.getString(3));

			connection.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
