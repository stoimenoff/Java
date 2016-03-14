package test.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
		
		String connUrl = "jdbc:postgresql://192.168.0.100:5432/TestDB";
		String user = "administrator";
		String pass = "admin";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found!");
			return;
		}
		System.out.println("Driver registred!");
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(connUrl, user, pass);
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
			return;
		}
		if (conn != null) {
			System.out.println("Connection established!");
		} else {
			System.out.println("Something went wrong with the connection!");
		}
	}

}
