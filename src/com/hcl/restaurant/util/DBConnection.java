package com.hcl.restaurant.util;

import java.sql.*;


public class DBConnection {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/restaurant";
	static final String USER = "root";
	static final String PASS = "Hemanth@2602";

	public Connection conn = null;

	public Connection getConnection() {

		try {
			if (conn == null)
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public void closePreparedStatement(PreparedStatement ps) {
		try {
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
