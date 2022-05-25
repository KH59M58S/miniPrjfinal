package com.kh.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.lecture.LectureOracle;
import com.kh.util.Util;
import com.swy.db.OracleDB;

public class Admin {

	public boolean signUp() {

		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();
		System.out.print("이름 : ");
		String name = Util.sc.nextLine();

		Connection conn = LectureOracle.getConnection();

		String sql = "INSERT INTO ADMIN (AD_NO, AD_ID, AD_PWD, AD_NAME)" 
						+ " VALUES(?,?,?,?)";

		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);

			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("생성 성공");
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
		}

		return false;
	}

	public boolean login() {

		System.out.print("아이디 : ");
		String id = Util.sc.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Util.sc.nextLine();

		Connection conn = LectureOracle.getConnection();

		String sql = "SELECT AD_PWD FROM ADMIN"
				+ " WHERE AD_ID = ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				String dbpwd = rs.getString(1);
				if (dbpwd.equalsIgnoreCase(pwd)) {
					System.out.println("로그인 성공");
					return true;
				} 
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleDB.close(conn);
			OracleDB.close(pstmt);
			OracleDB.close(rs);
		}

		System.out.println("로그인 실패");
		return false;
	}
}
