package com.bridgelabz.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TransactionPrepared {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Scanner scanner = new Scanner(System.in);

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement("insert into student values(?,?,?)");
			System.out.println("Platform Created..");

			while (true) {
				System.out.println("enter id:");
				int student_id = scanner.nextInt();

				System.out.println("enter name:");
				String student_name = scanner.next();

				System.out.println("enter age:");
				int student_age = scanner.nextInt();

				preparedStatement.setInt(1, student_id);
				preparedStatement.setString(2, student_name);
				preparedStatement.setInt(3, student_age);

				preparedStatement.executeUpdate();

				System.out.println("Commit/Rollback");
				String input = scanner.next();

				if (input.equals("Commit")) {
					connection.commit();
				}

				if (input.equals("Rollback")) {
					connection.rollback();
				}
				System.out.println("Record successfully saved...");

			}

		} catch (Exception e) {
			{
				try {
					connection.rollback();
					connection.commit();
					System.out.println("Rolled BAck..");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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
		scanner.close();

	}

}
