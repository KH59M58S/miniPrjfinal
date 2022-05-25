package com.swy.db;

import java.sql.Connection;

public class OracleDB {
	
	public static Connection getConnection() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		String id = "c##mini";
		String pwd = "1234";
		
		
		return null;
	}
}
