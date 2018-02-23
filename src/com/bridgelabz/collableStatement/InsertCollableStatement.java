package com.bridgelabz.collableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCollableStatement {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		CallableStatement collableStatement = null;

		String procedure = "{call InsertUser(?,?,?,?)}";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			collableStatement = connection.prepareCall(procedure);
			System.out.println("Platform Created.." + "\n");

			System.out.println("Enter User Id:");
			int user_id = scanner.nextInt();
			
			System.out.println("Enter your First name:");
			String user_firstName=scanner.next();
			
			System.out.println("Enter your Last name:");
			String user_lastName=scanner.next();
			
			System.out.println("Enter your Address:");
			String user_address=scanner.next();

			collableStatement.setInt(1, user_id);
			collableStatement.setString(2, user_firstName);
			collableStatement.setString(3, user_lastName);
			collableStatement.setString(4, user_address);

			collableStatement.execute();
			System.out.println("Inserted Successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		finally {
			if (collableStatement != null) {
				try {
					collableStatement.close();
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
