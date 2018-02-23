package com.bridgelabz.collableStatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteCollableStatement {

	public static void main(String[] args) {

		Connection connection = null;
		CallableStatement collableStatement = null;
		String procedure = "{call DeleteUser(?)}";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			collableStatement = connection.prepareCall(procedure);
			System.out.println("Platform Created.." + "\n");
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter Id to Delete the data:");
			int user_id = Integer.parseInt(bufferedReader.readLine());

			collableStatement.setInt(1, user_id);
			
			collableStatement.execute();
			System.out.println("Delete Successfully");
			
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

	}

}
