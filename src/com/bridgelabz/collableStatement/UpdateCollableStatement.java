package com.bridgelabz.collableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCollableStatement {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		CallableStatement collableStatement = null;
		
		String procedure="{call UpdateUser(?,?)}";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			collableStatement = connection.prepareCall(procedure);
			System.out.println("Platform Created.." + "\n");
			
			System.out.println("Enter ID where to update:");
			int user_id =scanner.nextInt();
			System.out.println("Enter Name to Set:");
			String user_name = scanner.next();
			
			collableStatement.setString(1,user_name );
			collableStatement.setInt(2, user_id);
			
			int update=collableStatement.executeUpdate();
			System.out.println("Updated"+update);

			/*collableStatement.execute();
			System.out.println("Updated Successfully:"+"\n"+collableStatement);*/
			
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
