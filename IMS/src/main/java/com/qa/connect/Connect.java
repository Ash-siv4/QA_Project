package com.qa.connect;

import java.sql.*;

public class Connect {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/inventory?useSSL=false";
	static final String USER = "root";
	static final String PASS = "root";
	
	Connection conn = null;
	Statement stmt = null;

	public Connect() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Connecting to the inventory database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection call() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);			
			System.out.println("Connected!\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
