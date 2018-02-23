package com.bridgelabz.preparedStatement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestDeletePreparedStatement {
	public static void main(String args[]) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Loaded and Registered..");

			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bidgelabz_db", "root", "root");
			System.out.println("Connection established with database..");

			preparedStatement = connection.prepareStatement("DELETE from student WHERE student_id=?");
			System.out.println("Platform Created..");

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

			System.out.println("Enter Id to Delete the data:");
			int student_id = Integer.parseInt(bufferedReader.readLine());

			preparedStatement.setInt(1, student_id);

			int delete = preparedStatement.executeUpdate();
			System.out.println(delete + " records affected" + "\n");

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
