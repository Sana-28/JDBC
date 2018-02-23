package com.bridgelabz.preparedStatement;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class TestInsertPreparedStatement {
	public static void main(String args[]) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			preparedStatement = connection.prepareStatement("insert into student values(?,?,?)");
			System.out.println("Platform Created..");

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			do {
				System.out.println("enter id:");
				int student_id = Integer.parseInt(bufferedReader.readLine());
				System.out.println("enter name:");
				String student_name = bufferedReader.readLine();
				System.out.println("enter age:");
				int student_age = Integer.parseInt(bufferedReader.readLine());

				preparedStatement.setInt(1, student_id);
				preparedStatement.setString(2, student_name);
				preparedStatement.setInt(3, student_age);

				int insert = preparedStatement.executeUpdate();
				System.out.println(insert + " records affected" + "\n");

				System.out.println("Do you want to continue: y/n");
				String addData = bufferedReader.readLine();
				if (addData.startsWith("n")) {
					break;
				}
			} while (true);

		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
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
			}
		}
	}
}