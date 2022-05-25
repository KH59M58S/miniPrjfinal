package com.kh.lecture;

import java.sql.Connection;

public class SignUp {
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##mini";
	String pwd = "1234";
	Connection conn;
	
	public void signUpConn() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}	
