package com.bridgelabz.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionStatement {

	public static void main(String[] args) {

		Connection connection = null;
		Statement statement = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered.." + "\n");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database.." + "\n");

			connection.setAutoCommit(false);

			statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO employee " + "VALUES (14, 'Abcde')");
			statement.execute("INSERT INTO employee " + "VALUES (13, 'abcdef')");
			System.out.println("Inserted Record..");
			
			connection.commit();

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
			}
		}

	}

}
