package com.bridgelabz.collableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CallCollableStatement {
	public static void main(String args[]) {
		Connection connection = null;
		CallableStatement collableStatement=null;
		
		String procedure="{call bidgelabz_db.GetUser(?)}";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered.."+"\n");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println(connection+"Connection established with database.."+"\n");

			collableStatement = connection.prepareCall(procedure);
			System.out.println("Platform Created..");
		}
		catch (Exception e) {
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