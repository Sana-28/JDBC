package com.bridgelabz.preparedStatement;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestUpdatePreparedStatement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			preparedStatement = connection
					.prepareStatement("UPDATE student SET student_id = ? WHERE student_name = ? ");
			
						
			System.out.println("Platform Created..");

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			do {
				System.out.println("Enter ID to Update:");
				int student_id = Integer.parseInt(bufferedReader.readLine());
				System.out.println("Enter name Where to Update:");
				String student_name = bufferedReader.readLine();
				
				preparedStatement.setInt(1, student_id);
				preparedStatement.setString(2, student_name);

				int update=	preparedStatement.executeUpdate();
				System.out.println("result:"+update);
				
			} while (true);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
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
