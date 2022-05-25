package com.kh.lecture;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.swy.db.OracleDB;

public class SignUp {

	// 전체 신청 리스트 조회
	public static void showAllSignupList() {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT STU.STU_NAME, C.C_NAME , S.S_TIME, S.S_QUIT_YN" 
				+ " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU" 
				+ " ON S.STU_NO = STU.STU_NO" 
				+ " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" 
				+ " WHERE S.S_QUIT_YN = 'N'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(
						"이름:" + rs.getString(1) + "  " + "강의명:" + rs.getString(2) + "  " + "신청일자:" + rs.getDate(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);

		}
	}

	// 수강신청
	public static boolean insertSignUp(int s_no, int c_no, int stu_no) {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO SIGNUP (S_NO, C_NO, STU_NO)" 
				+ "VALUES(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, s_no);
			pstmt.setInt(2, c_no);
			pstmt.setInt(3, stu_no);

			int resualt = pstmt.executeUpdate();
			if (resualt == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
		}

		return false;
	}

	// 학생별 신청내역 검색
	public static void showStdSignUp(String std_name) {
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT STU.STU_NAME, C.C_NAME , S.S_TIME, S.S_QUIT_YN"
				+ " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU"
				+ " ON S.STU_NO = STU.STU_NO" 
				+ " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" 
				+ " WHERE S.S_QUIT_YN = 'N' AND STU.STU_NAME = '" + std_name + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			
			while (rs.next()) {
				System.out.println(
						"이름:" + rs.getString(1) + "  " + "강의명:" + rs.getString(2) + "  " + "신청일자:" + rs.getDate(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);
		}
	}
	
	//강의별 신청내역 검색
	public static void showLectureSingUp(String c_name) {
		
		Connection conn = OracleDB.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT C.C_NAME, STU.STU_NAME, S.S_TIME, S.S_QUIT_YN"
				+ " FROM SIGNUP S"
				+ " INNER JOIN STUDENT STU"
				+ " ON S.STU_NO = STU.STU_NO" 
				+ " INNER JOIN CLASS C"
				+ " ON S.C_NO = c.c_no" 
				+ " WHERE S.S_QUIT_YN = 'N' AND C.C_NAME = '" + c_name + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);

			while (rs.next()) {
				System.out.println(
						"강의명:" + rs.getString(1) + "  " + "이름:" + rs.getString(2) + "  " +  "신청일자:" + rs.getDate(3));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null)
				OracleDB.close(conn);
			if (pstmt == null)
				OracleDB.close(pstmt);
			if (rs == null)
				OracleDB.close(rs);
		}
	}

//	public static void main(String[] args) {
//		System.out.println("start");
//		showAllSignupList();
//		System.out.println(insertSignUp(1, 1, 1));
//		showStdSignUp("박주은");
//		showLectureSingUp("A");
//	}

}
